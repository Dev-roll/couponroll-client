package app.web.couponroll.ui.my_coupons

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.web.couponroll.R
import app.web.couponroll.model.Task
import app.web.couponroll.ui.AppViewModelProvider
import app.web.couponroll.ui.components.CouponItem
import app.web.couponroll.ui.components.CouponRollTopAppBar
import app.web.couponroll.ui.navigation.NavigationDestination

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
    val myCoupons = listOf(
        Coupon(
            id = "1",
            storeId = "1",
            storeName = "Devroll Store1",
            name = "5% off",
            description = "5% off",
            creatorId = "1",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
            discount = 5,
            isStarred = true,
            isUsed = false,
        ),
        Coupon(
            id = "2",
            storeId = "2",
            storeName = "Devroll Store2",
            name = "クーポン",
            description = "10% off",
            creatorId = "2",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
            discount = 10,
            isStarred = false,
            isUsed = true,
        ),
        Coupon(
            id = "1",
            storeId = "1",
            storeName = "Devroll Store1",
            name = "8% off クーポン",
            description = "8% off",
            creatorId = "1",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
            discount = 8,
            isStarred = false,
            isUsed = true,
        ),
        Coupon(
            id = "2",
            storeId = "2",
            storeName = "Devroll Store2",
            name = "20% off",
            description = "20% off",
            creatorId = "2",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
            discount = 20,
            isStarred = true,
            isUsed = false,
        ),
        Coupon(
            id = "1",
            storeId = "1",
            storeName = "Devroll Store1",
            name = "クーポン",
            description = "5% off",
            creatorId = "1",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
            discount = 5,
            isStarred = true,
            isUsed = true,
        ),
        Coupon(
            id = "2",
            storeId = "2",
            storeName = "Devroll Store2",
            name = "10% off",
            description = "10% off",
            creatorId = "2",
            createdAt = "2021-09-01 00:00:00",
            updatedAt = "2021-09-01 00:00:00",
            deletedAt = "2021-09-01 00:00:00",
            expiresAt = "2021-09-01 00:00:00",
            iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject",
            imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
            discount = 10,
            isStarred = false,
            isUsed = true,
        ),
    )

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
            itemList = myCoupons,
//            onTaskClick = navigateToTaskUpdate,
            onTaskCheckedChange = viewModel::completeTask,
            onTaskStarredChange = viewModel::starTask,
            modifier = modifier.padding(innerPadding)
        )
    }
}

data class  Coupon(
    val id: String,
    val storeId: String,
    val storeName: String,
    val name: String,
    val description: String,
    val creatorId: String,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String,
    val expiresAt: String,
    val iconUrl: String,
    val imgUrl: String,
    val discount: Int,
    val isUsed: Boolean = false,
    val isStarred: Boolean = false,
)

@Composable
private fun HomeBody(
    itemList: List<Coupon>,
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
    itemList: List<Coupon>,
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
            for (couponData in itemList) {
                CouponItem(
                    coupon = couponData,
//                    onTaskClick = onTaskClick,
//                    onCompletedChange = { onTaskCheckedChange(couponData, it) },
//                    onStarredChange = { onTaskStarredChange(couponData, it) },
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
