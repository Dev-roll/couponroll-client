package app.web.couponroll.ui.store_top

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.web.couponroll.R
import app.web.couponroll.model.Task
import app.web.couponroll.ui.AppViewModelProvider
import app.web.couponroll.ui.components.CouponRollTopAppBar
import app.web.couponroll.ui.home.HomeViewModel
import app.web.couponroll.ui.navigation.NavigationDestination
import app.web.couponroll.ui.store_coupons.StoreCouponsScreen
import app.web.couponroll.ui.store_details.StoreDetailsScreen

object StoreTopDestination : NavigationDestination {
    override val route = "store_top"
    override val titleRes = R.string.store_top_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreTopScreen(
//    navigateToTaskUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val storeTopUiState by viewModel.homeUiState.collectAsState()
    Scaffold(
        topBar = {
            CouponRollTopAppBar(
                title = stringResource(StoreTopDestination.titleRes),
                canNavigateBack = false
            )
        },
    ) { innerPadding ->
        HomeBody(
            itemList = storeTopUiState.itemList,
//            onTaskClick = navigateToTaskUpdate,
            onTaskCheckedChange = viewModel::completeTask,
            onTaskStarredChange = viewModel::starTask,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    itemList: List<Task>,
//    onTaskClick: (String) -> Unit,
    onTaskCheckedChange: (Task, Boolean) -> Unit,
    onTaskStarredChange: (Task, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    TabLayout()
}

@Composable
fun TabLayout() {
//    var selectedTabIndex by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(0) }
    val titles = listOf(
        stringResource(R.string.store_coupons_title),
        stringResource(R.string.store_details_title)
    )

    // Reuse the default offset animation modifier, but use our own indicator
    Column {
        TabRow(
            selectedTabIndex = state,
            modifier = Modifier
                .padding(top = 64.dp, bottom = 20.dp)
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        Column {
            when (state) {
                0 -> StoreCouponsScreen()
                1 -> StoreDetailsScreen()
            }
        }
    }
}
