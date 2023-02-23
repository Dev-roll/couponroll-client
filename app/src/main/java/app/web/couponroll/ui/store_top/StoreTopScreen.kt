package app.web.couponroll.ui.store_top

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.HowToReg
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.web.couponroll.R
import app.web.couponroll.ui.components.customTabIndicatorOffset
import app.web.couponroll.ui.navigation.NavigationDestination
import app.web.couponroll.ui.store_coupons.StoreCouponsScreen
import app.web.couponroll.ui.store_details.StoreDetailsScreen

object StoreTopDestination : NavigationDestination {
    override val route = "store_top"
    override val titleRes = R.string.store_top_title
}

@Composable
fun StoreTopScreen() {
    Column {
        TabLayout()
    }
}

@Composable
fun TabLayout() {
//    var selectedTabIndex by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(0) }
    var isFollowing by remember { mutableStateOf(false) }
    val titles = listOf(
        stringResource(R.string.store_coupons_title),
        stringResource(R.string.store_details_title)
    )

    // Reuse the default offset animation modifier, but use our own indicator
    Column() {
        Row(Modifier.padding(all = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Rounded.Code, contentDescription = null)
            Text(text = "Devroll", fontSize = 20.sp)
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                text = "1,000",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { isFollowing = !isFollowing },
                colors = if (isFollowing) {
                    ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                } else {
                    ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                },
                modifier = Modifier.padding()
            ) {
                Row {
                    Icon(
                        imageVector = if (isFollowing) Icons.Rounded.HowToReg else Icons.Rounded.PersonAdd,
                        contentDescription = null,
                        tint = if (isFollowing) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = if (isFollowing) {
                            stringResource(R.string.is_following)
                        } else stringResource(
                            R.string.is_not_following
                        )
                    )
                }
            }
        }
        CustomTabRow(
            tabs = titles,
            selectedTabIndex = state,
            onTabClick = { state = it }
        )
        Column {
            when (state) {
                0 -> StoreCouponsScreen()
                1 -> StoreDetailsScreen()
            }
        }
    }
}

@Composable
fun CustomTabRow(
    tabs: List<String>, selectedTabIndex: Int, onTabClick: (Int) -> Unit
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabClick(index) },
                text = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        onTextLayout = { textLayoutResult ->
                            tabWidths[index] =
                                with(density) { (textLayoutResult.size.width + 100).toDp() }
                        },
                    )
                }
            )
        }
    }
}