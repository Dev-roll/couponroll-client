package app.web.couponroll.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.web.couponroll.R
import app.web.couponroll.ui.AppViewModelProvider
import app.web.couponroll.ui.my_coupons.MyCouponsScreen
import app.web.couponroll.ui.navigation.NavigationDestination
import app.web.couponroll.ui.navigation.Screen
import app.web.couponroll.ui.navigation.items
import app.web.couponroll.ui.store_list.StoreListScreen

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToCapture: () -> Unit,
    navigateToTaskUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { item ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (selected) item.selectedIcon else item.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(id = item.resourceId)) },
                        selected = selected,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(bottomNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = navigateToCapture,
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary,
//                modifier = Modifier.navigationBarsPadding()
//            ) {
//                Icon(
//                    imageVector = Icons.Rounded.Add,
//                    contentDescription = stringResource(R.string.task_entry_title),
//                    tint = MaterialTheme.colorScheme.onPrimary
//                )
//            }
//        },
    ) { innerPadding ->
//        if (selectedItem == 0) MyCouponsScreen(modifier = Modifier.padding(innerPadding)) else StoreListScreen(
//            navigateToCapture = { Unit })
        NavHost(
            bottomNavController,
            startDestination = Screen.MyCoupons.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.MyCoupons.route) { MyCouponsScreen() }
            composable(Screen.StoreList.route) {
                StoreListScreen(
                    navigateToAddStore = { Unit })
            }
        }
    }
}