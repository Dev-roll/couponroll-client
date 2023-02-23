package app.web.couponroll.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.web.couponroll.R
import app.web.couponroll.model.Task
import app.web.couponroll.ui.theme.OffColor
import app.web.couponroll.ui.theme.StarOnColor
import coil.compose.rememberAsyncImagePainter
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import java.util.*

@Composable
fun CouponItem(
    task: Task,
//    onTaskClick: (Task) -> Unit,
    onCompletedChange: (Boolean) -> Unit,
    onStarredChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Double
) {
    var isModalOpen by remember { mutableStateOf(false) }
    Box(modifier = modifier
        .clickable {
//            onTaskClick(task)
        }
        .background(color = MaterialTheme.colorScheme.secondaryContainer)
        .padding(8.dp)
    ) {
        Column {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(task.filePath),
                    contentDescription = "captured image",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.size((width / 8).dp)
                )
                Row(
                    modifier = modifier
                        .padding(vertical = 16.dp),
                ) {
                    //                    IconToggleButton(
                    //                        checked = task.isCompleted,
                    //                        onCheckedChange = onCompletedChange,
                    //                    ) {
                    //                        Icon(
                    //                            imageVector = if (task.isCompleted) Icons.Rounded.TaskAlt else Icons.Rounded.RadioButtonUnchecked,
                    //                            contentDescription = if (task.isCompleted) "check on" else "check off",
                    //                            tint = if (task.isCompleted) DoneColor else OffColor
                    //                        )
                    //                    }
                    Column(
                        modifier = Modifier
                            .size((width / 5).dp)
                    ) {
                        Text(
                            text = task.title,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = task.description,
                            fontSize = 14.sp,
                        )
                    }
                    IconToggleButton(
                        checked = task.isStarred,
                        onCheckedChange = onStarredChange,
                    ) {
                        Icon(
                            imageVector = if (task.isStarred) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                            contentDescription = if (task.isStarred) "check on" else "check off",
                            tint = if (task.isStarred) StarOnColor else OffColor
                        )
                    }
                }
            }
            Button(onClick = { isModalOpen = true }) {
                Text(text = "QRコードを表示")
            }
            if (isModalOpen) {
                AlertDialog(
                    onDismissRequest = { isModalOpen = false },
                    title = { Text(text = "クーポン名") },
                    text = {
                        Column {
                            Text(text = "Devroll Store")
                            QRCode(text = "https://example.com", modifier = Modifier.size(320.dp))
                        }
                    },
                    confirmButton = {},
                    dismissButton = {
                        TextButton(
                            onClick = {
                                isModalOpen = false
                            },
                        ) { Text(text = stringResource(id = R.string.close_button)) }
                    }
                )
            }
        }
    }
}

@Composable
fun QRCode(text: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bitmap = generateQRCode(text, context)
    val painter: Painter = BitmapPainter(bitmap.asImageBitmap())
    Image(
        painter = painter,
        contentDescription = "QR code for $text",
        contentScale = ContentScale.Fit,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun generateQRCode(text: String, context: Context): android.graphics.Bitmap {
    val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
    hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 512, 512, hints)
    val width = bitMatrix.width
    val height = bitMatrix.height
    val bmp =
        android.graphics.Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.RGB_565)
    for (x in 0 until width) {
        for (y in 0 until height) {
            bmp.setPixel(
                x,
                y,
                if (bitMatrix[x, y]) {
                    MaterialTheme.colorScheme.onSecondaryContainer.toArgb()
                } else {
                    MaterialTheme.colorScheme.onSecondary.toArgb()
                }
            )
        }
    }
    return bmp
}
