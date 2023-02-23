package app.web.couponroll.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.web.couponroll.ui.add_store.AddStoreDestination
import app.web.couponroll.ui.add_store.AddStoreScreen
import app.web.couponroll.ui.my_coupons.MyCouponsDestination
import app.web.couponroll.ui.my_coupons.MyCouponsScreen
import app.web.couponroll.ui.store_list.StoreListDestination
import app.web.couponroll.ui.store_list.StoreListScreen

@Composable
fun CouponRollNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MyCouponsDestination.route,
        modifier = modifier
    ) {
        composable(route = MyCouponsDestination.route) {
            MyCouponsScreen()
        }
        composable(route = StoreListDestination.route) {
            StoreListScreen(
                navigateToAddStore = { navController.navigate(AddStoreDestination.route) },
            )
        }
        composable(route = AddStoreDestination.route) {
            AddStoreScreen(
                navController = navController,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
