package app.web.couponroll.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.rounded.Storefront
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.web.couponroll.R
import app.web.couponroll.ui.add_store.AddStoreDestination
import app.web.couponroll.ui.add_store.AddStoreScreen
import app.web.couponroll.ui.entry.TaskEntryDestination
import app.web.couponroll.ui.entry.TaskEntryScreen
import app.web.couponroll.ui.home.HomeDestination
import app.web.couponroll.ui.home.HomeScreen
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
        startDestination = StoreTopDestination.route,
        // startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCapture = { navController.navigate(AddStoreDestination.route) },
                navigateToTaskUpdate = { navController.navigateUp() }
            )
        }
        composable(route = MyCouponsDestination.route) {
            MyCouponsScreen()
        }
        composable(route = StoreListDestination.route) {
            StoreListScreen(
                navigateToAddStore = { navController.navigate(AddStoreDestination.route) },
            )
        }
        composable(route = StoreTopDestination.route) {
            StoreTopScreen()
        }
//        composable(route = HomeDestination.route) {
//            HomeScreen(
//                navigateToCapture = { navController.navigate(CaptureDestination.route) },
//                navigateToTaskUpdate = { navController.navigateUp() }
//            )
//        }
        composable(route = AddStoreDestination.route) {
            AddStoreScreen(
                navController = navController,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                navigateToTaskEntry = { navController.navigate(TaskEntryDestination.route) },
            )
        }
        composable(route = TaskEntryDestination.route) {
            val uri = it.arguments?.getString("uri") ?: ""
            TaskEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                navigateToHome = {
                    navController.popBackStack()
                    navController.popBackStack()
                },
                uri = uri
            )
        }
    }
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    object MyCoupons : Screen(
        HomeDestination.route,
        R.string.my_coupons_title,
        Icons.Outlined.ConfirmationNumber,
        Icons.Filled.ConfirmationNumber
    )

    object StoreList : Screen(
        AddStoreDestination.route,
        R.string.store_list_title,
        Icons.Outlined.Storefront,
        Icons.Rounded.Storefront
    )
}

val items = listOf(
    Screen.MyCoupons,
    Screen.StoreList,
)
