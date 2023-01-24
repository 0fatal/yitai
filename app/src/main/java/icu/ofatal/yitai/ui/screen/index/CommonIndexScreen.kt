package icu.ofatal.yitai.ui.screen.index

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import icu.ofatal.yitai.R
import icu.ofatal.yitai.ui.screen.notification.NotificationScreenInline
import icu.ofatal.yitai.ui.screen.order.OrderScreen
import icu.ofatal.yitai.ui.screen.person.PersonScreen
import icu.ofatal.yitai.ui.screen.playground.PlaygroundScreen
import icu.ofatal.yitai.ui.theme.YitaiGray
import kotlinx.coroutines.launch

@Composable
fun CommonIndexScreen(navController: NavController) {
    val pagerState = rememberPagerState(0)
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
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier

                .background(MaterialTheme.colorScheme.background)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                count = 4,
                userScrollEnabled = false,
            ) { page ->
                when (page) {
                    0 -> {
                        PlaygroundScreen(
                            navController = navController,
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(top = 20.dp)
                                .padding(horizontal = 20.dp),
                        )
                    }
                    1 -> {
                        NotificationScreenInline(modifier = Modifier.padding(innerPadding).padding(horizontal = 20.dp))
                    }
                    2 -> {
                        OrderScreen()
                    }
                    3 -> {
                        PersonScreen(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding).padding(horizontal = 20.dp)
                        )
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
            "icon" to R.drawable.icon_message,
            "icon_active" to R.drawable.icon_message,
            "label" to "消息",
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
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFB0B1B1).copy(0.2f),
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            )
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
    ) {
        items.map {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = it["icon"] as Int
                        ),
                        contentDescription = it["label"] as String,
                        modifier = Modifier.size(20.dp),
                        tint = (if (currentPage == it["page"] as Int?) colorResource(id = R.color.theme_color) else LocalContentColor.current),
                    )
                },
                selected = currentPage == it["page"] as Int,
                label = {
                    Text(
                        it["label"] as String,
                        color = (if (currentPage == it["page"] as Int?) colorResource(id = R.color.theme_color) else YitaiGray)
                    )
                },
                onClick = {
                    scrollToPage(it["page"] as Int)
                }
            )
        }
    }
}