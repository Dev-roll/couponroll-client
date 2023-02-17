package app.web.couponroll.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.web.couponroll.ui.capture.CaptureDestination
import app.web.couponroll.ui.capture.CaptureScreen
import app.web.couponroll.ui.entry.TaskEntryDestination
import app.web.couponroll.ui.entry.TaskEntryScreen
import app.web.couponroll.ui.home.HomeDestination
import app.web.couponroll.ui.home.HomeScreen

@Composable
fun CttNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCapture = { navController.navigate(CaptureDestination.route) },
                navigateToTaskUpdate = { navController.navigateUp() }
            )
        }
        composable(route = CaptureDestination.route) {
            CaptureScreen(
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