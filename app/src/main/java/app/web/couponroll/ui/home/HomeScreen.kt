package app.web.couponroll.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
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
import coil.compose.rememberAsyncImagePainter
import app.web.couponroll.R
import app.web.couponroll.model.Task
import app.web.couponroll.ui.AppViewModelProvider
import app.web.couponroll.ui.components.CttTopAppBar
import app.web.couponroll.ui.navigation.NavigationDestination
import app.web.couponroll.ui.theme.DoneColor
import app.web.couponroll.ui.theme.OffColor
import app.web.couponroll.ui.theme.StarOnColor

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home_title
}

@Composable
fun HomeScreen(
    navigateToCapture: () -> Unit,
    navigateToTaskUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    Scaffold(
        topBar = {
            CttTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToCapture,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.task_entry_title),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            itemList = homeUiState.itemList,
            onTaskClick = navigateToTaskUpdate,
            onTaskCheckedChange = viewModel::completeTask,
            onTaskStarredChange = viewModel::starTask,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    itemList: List<Task>,
    onTaskClick: (String) -> Unit,
    onTaskCheckedChange: (Task, Boolean) -> Unit,
    onTaskStarredChange: (Task, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    if (itemList.isEmpty()) {
        Text(
            text = stringResource(R.string.no_item_description),
            style = MaterialTheme.typography.subtitle2
        )
    } else {
        TaskList(
            itemList = itemList,
            onTaskClick = { onTaskClick(it.id) },
            onTaskCheckedChange = onTaskCheckedChange,
            onTaskStarredChange = onTaskStarredChange
        )
    }
}

@Composable
private fun TaskList(
    itemList: List<Task>,
    onTaskClick: (Task) -> Unit,
    onTaskCheckedChange: (Task, Boolean) -> Unit,
    onTaskStarredChange: (Task, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints {
        val screenWidth = with(LocalDensity.current) { constraints.maxWidth }
        Column (
            modifier = Modifier
                .padding(16.dp)
                .background(color = Color.LightGray),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (sweets in itemList) {
                TaskItem(
                    task = sweets,
                    onTaskClick = onTaskClick,
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

@Composable
private fun TaskItem(
    task: Task,
    onTaskClick: (Task) -> Unit,
    onCompletedChange: (Boolean) -> Unit,
    onStarredChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    width: Double
) {
    Box( modifier = modifier
        .clickable { onTaskClick(task) }
    ) {
        Row{
            Image(
                painter = rememberAsyncImagePainter(task.filePath),
                contentDescription = "captured image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size((width / 8).dp)
            )
            Row( modifier = modifier
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
                Column (
                    modifier = Modifier.size((width / 5).dp)
                ) {
                    Text(
                        text = task.title,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = task.description,
                        color = Color(120,120,120),
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
