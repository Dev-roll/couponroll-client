package app.web.couponroll.ui.store_list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.web.couponroll.R
import app.web.couponroll.ui.components.CouponRollTopAppBar
import app.web.couponroll.ui.navigation.NavigationDestination
import coil.compose.AsyncImage

object StoreListDestination : NavigationDestination {
    override val route = "store_list"
    override val titleRes = R.string.store_list_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreListScreen(
    navController: NavController,
    navigateToAddStore: () -> Unit,
    modifier: Modifier = Modifier,
    //  viewModel: StoreListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    //  val storeListUiState by viewModel.storeListUiState.collectAsState()
    val storesList = listOf(
        listOf(
            Store(
                id = "1",
                name = "Devrollストア1",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 1000,
                maxDiscount = 5,
            ),
            Store(
                id = "2",
                name = "Devrollストア2",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 2000,
                maxDiscount = 10,
            ),
            Store(
                id = "3",
                name = "Devrollストア3",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 2000,
                maxDiscount = 10,
            ),
        ),
        listOf(
            Store(
                id = "1",
                name = "Devrollストア1",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 1000,
                maxDiscount = 5,
            ),
            Store(
                id = "2",
                name = "Devrollストア2",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 2000,
                maxDiscount = 10,
            ),
            Store(
                id = "3",
                name = "Devrollストア3",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 2000,
                maxDiscount = 10,
            ),
        ),
        listOf(
            Store(
                id = "1",
                name = "Devrollストア1",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 1000,
                maxDiscount = 5,
            ),
            Store(
                id = "2",
                name = "Devrollストア2",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 2000,
                maxDiscount = 10,
            ),
            Store(
                id = "3",
                name = "Devrollストア3",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 2000,
                maxDiscount = 10,
            ),
        ),
        listOf(
            Store(
                id = "1",
                name = "Devrollストア1",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 1000,
                maxDiscount = 5,
            ),
            Store(
                id = "2",
                name = "Devrollストア2",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 2000,
                maxDiscount = 10,
            ),
            Store(
                id = "3",
                name = "Devrollストア3",
                description = "スターバックスコーヒーは、アメリ",
                isPublic = "1",
                iconUrl = "https://cdn.amebaowndme.com/madrid-prd/madrid-web/images/sites/45/68860618a1f13a123d2e10df59baf9e9_f18c9521ada1f2ae75a6866dab1d7409.png",
                imgUrl = "https://pbs.twimg.com/profile_banners/219867859/1676421470/1500x500",
                createdAt = "2021-09-01 00:00:00",
                updatedAt = "2021-09-01 00:00:00",
                followersCount = 2000,
                maxDiscount = 10,
            ),
        ),
    )


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
                contentColor = MaterialTheme.colorScheme.primary,
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
        StoreListBody(
            storesList = storesList,
            navController = navController,
            modifier = modifier.padding(innerPadding)
        )
    }
}


data class Store(
    val id: String,
    val name: String,
    val description: String,
    val isPublic: String,
    val iconUrl: String,
    val imgUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val followersCount: Int,
    val maxDiscount: Int,
)

@Composable
private fun StoreListBody(
    storesList: List<List<Store>>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val titles = listOf<String>(
        "お気に入り",
        "おすすめ",
        "人気",
        "急上昇"
    )

    val icons = listOf<ImageVector>(
        Icons.Rounded.Favorite,
        Icons.Rounded.Redeem,
        Icons.Rounded.ThumbUp,
        Icons.Rounded.TrendingUp,
    )

    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            if (storesList.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_item_description),
                    style = MaterialTheme.typography.headlineSmall
                )
            } else {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    storesList.forEachIndexed { index, stores ->
                        StoreListRow(
                            title = titles[index],
                            icon = icons[index],
                            stores = stores,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreItem(store: Store, navController: NavController, modifier: Modifier) {
    OutlinedCard(
        onClick = { navController.navigate("store_top?storeName=${store.name}&storeDescription=${store.description}&followersCount=${store.followersCount.toString()}") },
        modifier = modifier
    ) {
        AsyncImage(model = store.imgUrl, contentDescription = null)
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = store.iconUrl,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp, 32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = store.name)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.People,
                        contentDescription = stringResource(R.string.task_entry_title),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = store.followersCount.toString())
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(verticalAlignment = Alignment.Bottom) {
                    val baseModifier = remember {
                        Modifier.alignByBaseline()
                    }

                    Text(text = "最大", fontSize = 12.sp, modifier = baseModifier)
                    Text(text = store.maxDiscount.toString(), modifier = baseModifier)
                    Text(text = "%引", fontSize = 12.sp, modifier = baseModifier)
                }
            }
        }
    }
}

@Composable
private fun StoreListRow(
    title: String,
    icon: ImageVector,
    stores: List<Store>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val lastIndex = stores.size - 1

    Row {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.task_entry_title),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title)
    }
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 0.dp, top = 8.dp, end = 0.dp, bottom = 24.dp)
    ) {
        itemsIndexed(items = stores) { index: Int, store: Store ->
            StoreItem(
                store = store,
                navController = navController,
                modifier = Modifier.size(180.dp, 140.dp)
            )

            if (index < lastIndex) Spacer(Modifier.width(24.dp))
        }
    }
}
