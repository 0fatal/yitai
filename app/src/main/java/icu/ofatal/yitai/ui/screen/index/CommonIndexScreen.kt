package icu.ofatal.yitai.ui.screen.index

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import icu.ofatal.yitai.R
import icu.ofatal.yitai.ui.screen.playground.PlaygroundScreen
import icu.ofatal.yitai.ui.theme.YitaiGray
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun CommonIndexScreen() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            buildBottomBar(
                currentPage = pagerState.currentPage,
                scrollToPage = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(it)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                count = 4,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> {
                        PlaygroundScreen()
                    }
                    1 -> {
                        PlaygroundScreen()
                    }
                    2 -> {
                        PlaygroundScreen()
                    }
                    3 -> {
                        PlaygroundScreen()
                    }
                }
            }
        }
    }
}

@Composable
private fun buildBottomBar(currentPage: Int, scrollToPage: (Int) -> Unit) {
    val items = listOf(
        mapOf(
            "icon" to R.drawable.icon_playground,
            "icon_active" to R.drawable.icon_playground_active,
            "label" to "广场",
            "page" to 0
        ),
        mapOf(
            "icon" to R.drawable.icon_shopping,
            "icon_active" to R.drawable.icon_shopping_active,
            "label" to "购物车",
            "page" to 1
        ),
        mapOf(
            "icon" to R.drawable.icon_order,
            "icon_active" to R.drawable.icon_order_active,
            "label" to "订单",
            "page" to 2
        ),
        mapOf(
            "icon" to R.drawable.icon_person,
            "icon_active" to R.drawable.icon_person_active,
            "label" to "个人",
            "page" to 3
        ),
    )
    NavigationBar {
        items.map {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = (if (currentPage == it["page"] as Int?) it["icon"] else it["icon_active"]) as Int
                        ),
                        contentDescription = it["label"] as String
                    )
                },
                selected = currentPage == it["page"] as Int,
                label = {
                    Text(
                        it["name"] as String,
                        color = (if (currentPage == it["page"] as Int?) Color(R.color.theme_color) else YitaiGray)
                    )
                },
                onClick = {
                    scrollToPage(it["page"] as Int)
                }
            )
        }
    }
}