package app.web.couponroll

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import app.web.couponroll.ui.my_coupons.MyCouponsDestination
import app.web.couponroll.ui.navigation.TopLevelDestination
import app.web.couponroll.ui.store_list.StoreListDestination

@Composable
fun rememberCouponRollStudioAppState(
    navController: NavHostController = rememberNavController(),
): CouponRollStudioAppState {
    return remember(navController) {
        CouponRollStudioAppState(navController)
    }
}

@Stable
class CouponRollStudioAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            MyCouponsDestination.route -> TopLevelDestination.MY_COUPONS
            StoreListDestination.route -> TopLevelDestination.STORE_LIST
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.MY_COUPONS -> navController.navigate(MyCouponsDestination.route)
            TopLevelDestination.STORE_LIST -> navController.navigate(StoreListDestination.route)
        }
    }
}