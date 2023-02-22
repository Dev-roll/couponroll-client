package app.web.couponroll.ui.add_store

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.web.couponroll.R
import app.web.couponroll.ui.AppViewModelProvider
import app.web.couponroll.ui.components.CouponRollTopAppBar
import app.web.couponroll.ui.home.HomeViewModel
import app.web.couponroll.ui.navigation.NavigationDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object AddStoreDestination : NavigationDestination {
    override val route = "add_store"
    override val titleRes = R.string.add_store_title
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddStoreScreen(
    navController: NavController,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    navigateToTaskEntry: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onImageFile: (File) -> Unit = { },
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    val lifecycleOwner = LocalLifecycleOwner.current

    var previewUseCase by remember {
        mutableStateOf<UseCase>(Preview.Builder().build())
    }
    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder().setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY).build()
        )
    }

    Scaffold(
        topBar = {
            CouponRollTopAppBar(
                title = stringResource(AddStoreDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        val savedFile = imageCaptureUseCase.takePicture(context.executor)
                        onImageFile(savedFile)
                        val uri = savedFile.toUri()
                        if (uri != Uri.EMPTY) {
                            val uriEncoded =
                                withContext(Dispatchers.IO) {
                                    URLEncoder.encode(
                                        uri.toString(),
                                        StandardCharsets.UTF_8.toString()
                                    )
                                }
                            navController.navigate("task_entry/${uriEncoded}")
                        }
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Rounded.PhotoCamera,
                    contentDescription = stringResource(R.string.task_entry_title),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        if (cameraPermissionState.status.isGranted) {
            Text(text = "", modifier = modifier.padding(innerPadding))

            Box {
                val detectedQrCode = remember { mutableStateOf("no data") }

                CameraPreview(
                    modifier = Modifier.fillMaxSize(),
//                    onUseCase = { previewUseCase = it }
                ) {
                    detectedQrCode.value = it
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = detectedQrCode.value,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .padding(top = 20.dp, bottom = 120.dp)
                    )
                }
            }
//            LaunchedEffect(previewUseCase) {
//                val cameraProvider = context.getCameraProvider()
//                try {
//                    cameraProvider.unbindAll()
//                    cameraProvider.bindToLifecycle(
//                        lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
//                    )
//                } catch (ex: Exception) {
//                    Log.e("CameraCapture", "Failed to bind camera use cases", ex)
//                }
//            }
        } else {
            cameraPermissionState.launchPermissionRequest()
        }
    }
}

val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)


@Suppress("BlockingMethodInNonBlockingContext")
suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { future ->
        future.addListener(
            {
                continuation.resume(future.get())
            },
            executor
        )
    }
}

suspend fun ImageCapture.takePicture(executor: Executor): File {
    val photoFile = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            File.createTempFile("image", "jpg")
        }.getOrElse { exception ->
            Log.e(
                "TakePicture",
                "Failed to create temporary file",
                exception
            )
            File("/dev/null")
        }
    }

    return suspendCoroutine { continuation ->
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        takePicture(
            outputOptions, executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    continuation.resume(photoFile)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("TakePicture", "Image capture failed", exception)
                    continuation.resumeWithException(exception)
                }
            }
        )
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    onUseCase: (UseCase) -> Unit = { },
    onQrDetected: (code: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(factory = { context ->
        val previewView = PreviewView(context).apply {
            this.scaleType = scaleType
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
//        onUseCase(
//            Preview.Builder().build().also { it.setSurfaceProvider(previewView.surfaceProvider) }
//        )
        // CameraX Preview UseCase
        val previewUseCase = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

        // QRコード解析UseCase
        val qrCodeAnalysisUseCase = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(
                    Executors.newSingleThreadExecutor(),
                    QRCodeAnalyzer(onQrDetected)
                )
            }

        coroutineScope.launch {
            val cameraProvider = context.getCameraProvider()
            try {
                // use caseをライフサイクルにバインドする前にアンバインドを行う必要がある
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, previewUseCase, qrCodeAnalysisUseCase
                )
            } catch (ex: Exception) {
                Log.e("CameraPreview", "Use case binding failed", ex)
            }
        }
        previewView
    })
}

class QRCodeAnalyzer(private val onQrDetected: (code: String) -> Unit) : ImageAnalysis.Analyzer {
    private val qrScannerOptions: BarcodeScannerOptions
        get() {
            return BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .build()
        }
    private val qrScanner = BarcodeScanning.getClient(qrScannerOptions)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        // カメラから上手く画像を取得することができているとき
        if (mediaImage != null) {
            // CameraXで取得した画像をInputImage形式に変換する
            val adjustedImage =
                InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)

            qrScanner.process(adjustedImage)
                .addOnSuccessListener {
                    if (it.isNotEmpty()) {
                        Log.d("Success", "Detected code is ${it[0].rawValue}")
                        onQrDetected(it[0].rawValue.toString())
                    }
                }
                .addOnCompleteListener { image.close() }
        }
    }
}