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
import app.web.couponroll.ui.store_top.StoreTopDestination
import app.web.couponroll.ui.store_top.StoreTopScreen

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
                navController = navController,
                navigateToAddStore = { navController.navigate(AddStoreDestination.route) },
            )
        }
        composable(route = StoreTopDestination.route) {
            val storeName = it.arguments?.getString("storeName") ?: ""
            val storeDescription = it.arguments?.getString("storeDescription") ?: ""
            val followersCount = it.arguments?.getString("followersCount") ?: ""
            StoreTopScreen(
                storeName = storeName,
                storeDescription = storeDescription,
                followersCount = followersCount,
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
