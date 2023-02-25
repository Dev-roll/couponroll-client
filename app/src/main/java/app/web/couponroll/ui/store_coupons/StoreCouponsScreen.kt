package app.web.couponroll.ui.store_coupons

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import app.web.couponroll.ui.my_coupons.MyCouponsScreen

@Composable
fun StoreCouponsScreen(storeId: String="1") {
    Column {
        MyCouponsScreen(storeId)
    }
}
