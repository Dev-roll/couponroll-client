package app.web.couponroll.ui.store_list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.web.couponroll.R
import app.web.couponroll.model.Task
import app.web.couponroll.ui.AppViewModelProvider
import app.web.couponroll.ui.components.CouponRollTopAppBar
import app.web.couponroll.ui.home.HomeDestination
import app.web.couponroll.ui.home.HomeViewModel
import app.web.couponroll.ui.navigation.NavigationDestination
import app.web.couponroll.ui.theme.OffColor
import app.web.couponroll.ui.theme.StarOnColor
import coil.compose.rememberAsyncImagePainter

object StoreListDestination : NavigationDestination {
    override val route = "store_list"
    override val titleRes = R.string.store_list_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreListScreen(
    navigateToAddStore: () -> Unit,
//    navigateToTaskUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val storeListUiState by viewModel.homeUiState.collectAsState()
    Scaffold(
        topBar = {
            CouponRollTopAppBar(
                title = stringResource(StoreListDestination.titleRes),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddStore,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.task_entry_title),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            itemList = storeListUiState.itemList,
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
        Column {
//            お気に入り
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = stringResource(R.string.task_entry_title),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(text = "お気に入り")
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 0.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 0.dp, top = 64.dp, end = 0.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (sweets in itemList) {
                    TaskItem(
                        task = sweets,
                        //                    onTaskClick = onTaskClick,
                        onCompletedChange = { onTaskCheckedChange(sweets, it) },
                        onStarredChange = { onTaskStarredChange(sweets, it) },
                        width = screenWidth.toDouble()
                    )
                }
            }
//            おすすめ
            Icon(
                imageVector = Icons.Rounded.Redeem,
                contentDescription = stringResource(R.string.task_entry_title),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(text = "おすすめ")
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 0.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 0.dp, top = 64.dp, end = 0.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (sweets in itemList) {
                    TaskItem(
                        task = sweets,
                        //                    onTaskClick = onTaskClick,
                        onCompletedChange = { onTaskCheckedChange(sweets, it) },
                        onStarredChange = { onTaskStarredChange(sweets, it) },
                        width = screenWidth.toDouble()
                    )
                }
            }
//            人気
            Icon(
                imageVector = Icons.Rounded.ThumbUp,
                contentDescription = stringResource(R.string.task_entry_title),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(text = "人気")
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 0.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 0.dp, top = 64.dp, end = 0.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (sweets in itemList) {
                    TaskItem(
                        task = sweets,
                        //                    onTaskClick = onTaskClick,
                        onCompletedChange = { onTaskCheckedChange(sweets, it) },
                        onStarredChange = { onTaskStarredChange(sweets, it) },
                        width = screenWidth.toDouble()
                    )
                }
            }

//            急上昇
            Icon(
                imageVector = Icons.Rounded.TrendingUp,
                contentDescription = stringResource(R.string.task_entry_title),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(text = "急上昇")
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 0.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 0.dp, top = 64.dp, end = 0.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (sweets in itemList) {
                    TaskItem(
                        task = sweets,
                        //                    onTaskClick = onTaskClick,
                        onCompletedChange = { onTaskCheckedChange(sweets, it) },
                        onStarredChange = { onTaskStarredChange(sweets, it) },
                        width = screenWidth.toDouble()
                    )
                }
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

@Composable
private fun TaskItem(
    task: Task,
//    onTaskClick: (Task) -> Unit,
    onCompletedChange: (Boolean) -> Unit,
    onStarredChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Double
) {
    Box(modifier = modifier
        .clickable {
//            onTaskClick(task)
        }
        .background(color = MaterialTheme.colorScheme.secondaryContainer)
        .padding(8.dp)
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(task.filePath),
                contentDescription = "captured image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size((width / 8).dp)
            )
            Row(
                modifier = modifier
                    .padding(vertical = 16.dp),
            ) {
//                    IconToggleButton(
//                        checked = task.isCompleted,
//                        onCheckedChange = onCompletedChange,
//                    ) {
//                        Icon(
//                            imageVector = if (task.isCompleted) Icons.Rounded.TaskAlt else Icons.Rounded.RadioButtonUnchecked,
//                            contentDescription = if (task.isCompleted) "check on" else "check off",
//                            tint = if (task.isCompleted) DoneColor else OffColor
//                        )
//                    }
                Column(
                    modifier = Modifier
                        .size((width / 5).dp)
                ) {
                    Text(
                        text = task.title,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = task.description,
                        fontSize = 14.sp,
                    )
                }
                IconToggleButton(
                    checked = task.isStarred,
                    onCheckedChange = onStarredChange,
                ) {
                    Icon(
                        imageVector = if (task.isStarred) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = if (task.isStarred) "check on" else "check off",
                        tint = if (task.isStarred) StarOnColor else OffColor
                    )
                }
            }
        }
    }
}
