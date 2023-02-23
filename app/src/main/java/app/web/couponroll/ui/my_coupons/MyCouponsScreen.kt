package app.web.couponroll.ui.my_coupons

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.web.couponroll.R
import app.web.couponroll.model.Task
import app.web.couponroll.ui.AppViewModelProvider
import app.web.couponroll.ui.components.CouponItem
import app.web.couponroll.ui.components.CouponRollTopAppBar
import app.web.couponroll.ui.navigation.NavigationDestination
import app.web.couponroll.ui.theme.OffColor
import app.web.couponroll.ui.theme.StarOnColor
import coil.compose.rememberAsyncImagePainter

object MyCouponsDestination : NavigationDestination {
    override val route = "my_coupons"
    override val titleRes = R.string.my_coupons_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCouponsScreen(
    modifier: Modifier = Modifier,
    viewModel: MyCouponsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val myCouponsUiState by viewModel.myCouponsUiState.collectAsState()
    Scaffold(
        topBar = {
            CouponRollTopAppBar(
                title = stringResource(MyCouponsDestination.titleRes),
                canNavigateBack = false
            )
        },
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
        HomeBody(
            itemList = myCouponsUiState.itemList,
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
    if (itemList.isEmpty()) {
        Text(
            text = stringResource(R.string.no_item_description),
            style = MaterialTheme.typography.headlineSmall
        )
    } else {
        TaskList(
            itemList = itemList,
//            onTaskClick = { onTaskClick(it.id) },
            onTaskCheckedChange = onTaskCheckedChange,
            onTaskStarredChange = onTaskStarredChange
        )
    }
}

@Composable
private fun TaskList(
    itemList: List<Task>,
//    onTaskClick: (Task) -> Unit,
    onTaskCheckedChange: (Task, Boolean) -> Unit,
    onTaskStarredChange: (Task, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints {
        val screenWidth = with(LocalDensity.current) { constraints.maxWidth }
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 0.dp)
                .verticalScroll(rememberScrollState())
                .padding(start = 0.dp, top = 64.dp, end = 0.dp, bottom = 0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (sweets in itemList) {
                CouponItem(
                    task = sweets,
//                    onTaskClick = onTaskClick,
                    onCompletedChange = { onTaskCheckedChange(sweets, it) },
                    onStarredChange = { onTaskStarredChange(sweets, it) },
                    width = screenWidth.toDouble()
                )
            }
        }
    }

//    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
//        items(items = itemList, key = { it.id }) { item ->
//            TaskItem(
//                task = item,
//                onTaskClick = onTaskClick,
//                onCompletedChange = { onTaskCheckedChange(item, it) },
//                onStarredChange = { onTaskStarredChange(item, it) }
//            )
//            Divider()
//        }
//    }
}
