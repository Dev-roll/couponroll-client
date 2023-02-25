package app.web.couponroll.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.web.couponroll.R
import app.web.couponroll.ui.my_coupons.Coupon
import app.web.couponroll.ui.theme.OffColor
import app.web.couponroll.ui.theme.StarOnColor
import coil.compose.AsyncImage
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import java.util.*

@Composable
fun CouponItem(
    coupon: Coupon,
//    onTaskClick: (Task) -> Unit,
//    onCompletedChange: (Boolean) -> Unit,
//    onStarredChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Double
) {
    var isModalOpen by remember { mutableStateOf(false) }
    Box(modifier = modifier
        .clickable {
            isModalOpen = true
        }
        .background(
            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
            shape = RoundedCornerShape(8.dp)
        ),
        contentAlignment = Alignment.CenterStart
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    var isUsed by remember {
                        mutableStateOf(coupon.isUsed)
                    }
                    IconToggleButton(
                        checked = coupon.isUsed,
                        onCheckedChange = { isUsed = !isUsed },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(56.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "-",
                                color = if (coupon.isUsed) OffColor else MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Column {
                                Text(
                                    text = "2023",
                                    color = if (coupon.isUsed) OffColor else MaterialTheme.colorScheme.onBackground,
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = "2.28",
                                    color = if (coupon.isUsed) OffColor else MaterialTheme.colorScheme.onBackground,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(32.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                    }
                    Icon(
                        imageVector = Icons.Rounded.QrCode,
                        contentDescription = null,
                        tint = if (coupon.isUsed) OffColor else MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(20.dp)
                            .size(32.dp)
                    )
                    if (isModalOpen) {
                        AlertDialog(
                            onDismissRequest = { isModalOpen = false },
                            title = { Text(text = "クーポン名") },
                            text = {
                                Column {
                                    Text(text = coupon.storeName)
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
            Box {
                if (coupon.isUsed) {
                    Box(
                        modifier = Modifier
                            .padding(start = (0.5).dp)
                            .height(144.dp)
                            .width(3.dp)
                            .background(
                                color = MaterialTheme.colorScheme.background
                            )
                    ) {
                    }
                } else {
                    Column {
                        for (i in 0..12) {
                            Spacer(modifier = Modifier.height(3.dp))
                            Box(
                                modifier = Modifier
                                    .height(4.dp)
                                    .width(4.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.background,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            ) {
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(132.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .height(40.dp)
                            .width(40.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(40.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Storefront,
                            contentDescription = null,
                            tint = OffColor,
                            modifier = Modifier.size(28.dp)
                        )
                        AsyncImage(
                            model = coupon.iconUrl,
                            contentDescription = null,
                            modifier = Modifier.clip(CircleShape),
                            colorFilter = ColorFilter.tint(
                                if (coupon.isUsed) MaterialTheme.colorScheme.secondaryContainer.copy(
                                    alpha = 0.5f
                                ) else MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0f),
                                BlendMode.Darken
                            ),
                        )
                    }
                    Column {
                        Text(
                            text = coupon.name,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = coupon.storeName,
                            fontSize = 11.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val baseModifier = remember {
                        Modifier.alignByBaseline()
                    }
                    Text(
                        text = coupon.discount.toString(),
                        fontWeight = if (coupon.isUsed) FontWeight.Medium else FontWeight.Bold,
                        fontSize = 50.sp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        modifier = baseModifier
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "%",
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        modifier = baseModifier
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "OFF",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = if (coupon.isUsed) 0.5f else 1f),
                        modifier = baseModifier
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier
                        .width(148.dp)
                        .height(148.dp),
                ) {
                    AsyncImage(
                        model = coupon.imgUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                        alpha = if (coupon.isUsed) 0.5f else 1f,
                    )
                }
                var isStarred by remember {
                    mutableStateOf(coupon.isStarred)
                }
                IconToggleButton(
                    checked = coupon.isStarred,
                    onCheckedChange = { isStarred = !isStarred },
                ) {
                    Icon(
                        imageVector = if (coupon.isStarred) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = if (coupon.isStarred) "check on" else "check off",
                        tint = if (coupon.isStarred) StarOnColor else OffColor
                    )
                }
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                        .absoluteOffset(x = 16.dp, y = 58.dp)
                        .background(
                            color = if (coupon.isUsed)
                                alphaBlend(
                                    MaterialTheme.colorScheme.secondaryContainer,
                                    MaterialTheme.colorScheme.background,
                                    0.5f
                                ) else MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(32.dp)
                        )
                ) {
                }
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                        .absoluteOffset(x = 32.dp, y = 58.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                        )
                ) {
                }
            }
        }
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .absoluteOffset(x = (-12).dp, y = 0.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
        }
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .absoluteOffset(x = (screenWidth - 24).dp, y = 0.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
        }
        Box(
            modifier = Modifier
                .height(8.dp)
                .width(8.dp)
                .absoluteOffset(x = 70.dp, y = (-74).dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
        }
        Box(
            modifier = Modifier
                .height(8.dp)
                .width(8.dp)
                .absoluteOffset(x = 70.dp, y = 74.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
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

fun alphaBlend(color1: Color, color2: Color, alpha: Float): Color {
    val r = color1.red * (1 - alpha) + color2.red * alpha
    val g = color1.green * (1 - alpha) + color2.green * alpha
    val b = color1.blue * (1 - alpha) + color2.blue * alpha
    val a = color1.alpha * (1 - alpha) + color2.alpha * alpha
    return Color(r, g, b, a)
}