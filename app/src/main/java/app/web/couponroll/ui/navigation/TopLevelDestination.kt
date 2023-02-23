package app.web.couponroll.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.SpaceDashboard
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.SpaceDashboard
import androidx.compose.ui.graphics.vector.ImageVector
import app.web.couponroll.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    MY_COUPONS(
        selectedIcon = Icons.Filled.SpaceDashboard,
        unselectedIcon = Icons.Outlined.SpaceDashboard,
        iconTextId = R.string.my_coupons_icon_text,
        titleTextId = R.string.my_coupons_title,
    ),
    STORE_LIST(
        selectedIcon = Icons.Filled.QrCodeScanner,
        unselectedIcon = Icons.Outlined.QrCodeScanner,
        iconTextId = R.string.qr_code_scan_icon_text,
        titleTextId = R.string.store_list_title,
    ),
}