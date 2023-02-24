package app.web.couponroll.ui.store_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StoreDetailsScreen(
    storeDescription: String
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = storeDescription)
    }
}
