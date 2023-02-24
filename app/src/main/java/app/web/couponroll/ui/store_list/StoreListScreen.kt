package app.web.couponroll.ui.store_list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8c1bb77-3e47-4263-b040-8adde060e752/Group_2608810.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150816Z&X-Amz-Expires=86400&X-Amz-Signature=8230d239f6d13b47dbe0019a4f9c4d8d44612b1f8056ceaa28e40a1a87da2239&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608810.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4fb77526-ffa8-4d17-92e2-6106433b82d3/Group_2608815.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150812Z&X-Amz-Expires=86400&X-Amz-Signature=1d883c9ce55397e6eb0d05908073f61b70839a7bca66df930347aebbfb5a3f87&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608815.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/acdee1a6-ab6b-43a8-8d31-4f5c5b3ea2f5/Group_2608818.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150851Z&X-Amz-Expires=86400&X-Amz-Signature=d0f161c56f8da0c48b1005f07bfc8438f9bdd0f34f27c6462a0da216b41fc342&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608818.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/c96cedbb-8a91-41c1-b705-9a107af390d8/Group_2608811.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150845Z&X-Amz-Expires=86400&X-Amz-Signature=def13f5f9dfec47f9a7ff80d5fcaa73764c272882267e82b5dcb851882e58ae5&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608811.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/3f2266ed-c1b2-4677-8e39-29b4a0a598d4/Group_2608812.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150828Z&X-Amz-Expires=86400&X-Amz-Signature=2256a01696b3a7dde8e27ef979e77b9ffd05422a433ae33885df59105f32e128&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608812.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/03e64c3a-4ef5-493e-9a41-5d0c2afcf110/Group_2608816.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150823Z&X-Amz-Expires=86400&X-Amz-Signature=68b5056a63ba78ad249de63d0d0c58d70c27fe38e61384357a10646f72da349d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608816.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/b945a0f3-2a94-4447-9a37-b1dfc73706c9/Group_2608817.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150839Z&X-Amz-Expires=86400&X-Amz-Signature=341659970e28233e02d0e09eeb9fa65af9f02f38325537e5bc9ee2d54ba0ceb8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608817.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f8a76a21-9e0e-408c-99d0-dcaa216afead/Group_2608809.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150834Z&X-Amz-Expires=86400&X-Amz-Signature=49810e8f969b04c37f06010d66089ce2989a20b7c1d5c5855f81a492f83ed0de&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608809.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0568de6a-ebf9-4173-b606-97001fdbc818/Group_2608808.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150807Z&X-Amz-Expires=86400&X-Amz-Signature=7ae76fa59f8289f48f7699f0daf02bb4487a0ee072dec80aa561014aaa94dcc8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608808.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/16320716-01fd-4c30-a3c9-ff2328e5f3d2/Group_2608814.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150802Z&X-Amz-Expires=86400&X-Amz-Signature=d909a5d9535b921fbfcac1b1034e30c8db4461339deac72b3db6d5ea851f864c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608814.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a300c291-9d74-4e7e-b9d8-80684f9e6462/Group_2608819.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150900Z&X-Amz-Expires=86400&X-Amz-Signature=b41ee48ac2bdf99e55e5c66b3236fb1f46cc9b83b4fc7371e5d749e2957f346d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608819.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/91d27dfb-5f8d-4b9b-8368-747764a7af4c/Group_2608813.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150855Z&X-Amz-Expires=86400&X-Amz-Signature=9238b824ab43139ba98345ad73827db8f659a37db2d5f613e3de63f2cfb9c825&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608813.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e8c1bb77-3e47-4263-b040-8adde060e752/Group_2608810.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150816Z&X-Amz-Expires=86400&X-Amz-Signature=8230d239f6d13b47dbe0019a4f9c4d8d44612b1f8056ceaa28e40a1a87da2239&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608810.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4fb77526-ffa8-4d17-92e2-6106433b82d3/Group_2608815.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150812Z&X-Amz-Expires=86400&X-Amz-Signature=1d883c9ce55397e6eb0d05908073f61b70839a7bca66df930347aebbfb5a3f87&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608815.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/acdee1a6-ab6b-43a8-8d31-4f5c5b3ea2f5/Group_2608818.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150851Z&X-Amz-Expires=86400&X-Amz-Signature=d0f161c56f8da0c48b1005f07bfc8438f9bdd0f34f27c6462a0da216b41fc342&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608818.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/c96cedbb-8a91-41c1-b705-9a107af390d8/Group_2608811.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150845Z&X-Amz-Expires=86400&X-Amz-Signature=def13f5f9dfec47f9a7ff80d5fcaa73764c272882267e82b5dcb851882e58ae5&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608811.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/3f2266ed-c1b2-4677-8e39-29b4a0a598d4/Group_2608812.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150828Z&X-Amz-Expires=86400&X-Amz-Signature=2256a01696b3a7dde8e27ef979e77b9ffd05422a433ae33885df59105f32e128&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608812.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/03e64c3a-4ef5-493e-9a41-5d0c2afcf110/Group_2608816.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150823Z&X-Amz-Expires=86400&X-Amz-Signature=68b5056a63ba78ad249de63d0d0c58d70c27fe38e61384357a10646f72da349d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608816.png%22&x-id=GetObject",
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
                iconUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/b945a0f3-2a94-4447-9a37-b1dfc73706c9/Group_2608817.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150839Z&X-Amz-Expires=86400&X-Amz-Signature=341659970e28233e02d0e09eeb9fa65af9f02f38325537e5bc9ee2d54ba0ceb8&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608817.png%22&x-id=GetObject",
                imgUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f8a76a21-9e0e-408c-99d0-dcaa216afead/Group_2608809.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230224%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230224T150834Z&X-Amz-Expires=86400&X-Amz-Signature=49810e8f969b04c37f06010d66089ce2989a20b7c1d5c5855f81a492f83ed0de&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%25202608809.png%22&x-id=GetObject",
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
        "フォロー中",
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
                    modifier = Modifier
                        .size(32.dp, 32.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = store.name, maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
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
