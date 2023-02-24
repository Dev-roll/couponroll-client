package app.web.couponroll.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
            isModalOpen = true
//            onTaskClick(task)
        }
        .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconToggleButton(
                        checked = task.isCompleted,
                        onCheckedChange = onCompletedChange,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(60.dp)
                    ) {
                        if (task.isCompleted) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "-", color = OffColor)
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(text = "2023", color = OffColor)
                                    Text(text = "2.28", color = OffColor)
                                }
                            }
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(text = "-")
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(text = "2023")
                                    Text(text = "2.28")
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(40.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                    }
                    Icon(
                        imageVector = Icons.Rounded.QrCode,
                        contentDescription = null,
                        tint = if (task.isCompleted) OffColor else MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(all = 18.dp)
                            .size(40.dp)
                    )
                    if (isModalOpen) {
                        AlertDialog(
                            onDismissRequest = { isModalOpen = false },
                            title = { Text(text = "クーポン名") },
                            text = {
                                Column {
                                    Text(text = "Devroll Store")
                                    QRCode(
                                        text = "https://example.com",
                                        modifier = Modifier.size(320.dp)
                                    )
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
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
            }
            Column {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = task.description,
                    fontSize = 14.sp,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = rememberAsyncImagePainter(task.filePath),
                    contentDescription = "captured image",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .width(100.dp)
                        .height(200.dp)
                )
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
