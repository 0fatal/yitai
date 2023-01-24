package icu.ofatal.yitai.ui.screen.index

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import icu.ofatal.yitai.R
import icu.ofatal.yitai.ui.screen.ScheduleScreen
import icu.ofatal.yitai.ui.screen.case_data.CaseDataScreen
import icu.ofatal.yitai.ui.screen.person.DoctorPersonScreen
import icu.ofatal.yitai.ui.screen.playground.PlaygroundScreen
import icu.ofatal.yitai.ui.theme.YitaiGray
import kotlinx.coroutines.launch

@Composable
fun DoctorIndexScreen(navController: NavController) {
    val pagerState = rememberPagerState(1)
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
                modifier = Modifier
                    .fillMaxSize(),
//                    .padding(horizontal = 20.dp),
                count = 4,
                userScrollEnabled = false,
            ) { page ->
                when (page) {
                    0 -> {
                        PlaygroundScreen(
                            navController = navController,
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(20.dp)
                        )
                    }
                    1 -> {
                        CaseDataScreen(navController = navController)
                    }
                    2 -> {
                        ScheduleScreen()
                    }
                    3 -> {
                        DoctorPersonScreen()
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
            "label" to "广场",
            "page" to 0
        ),
        mapOf(
            "icon" to R.drawable.icon_doctor_calendar,
            "label" to "病例数据",
            "page" to 1,
        ),
        mapOf(
            "icon" to R.drawable.icon_doctor_clock,
            "label" to "日程表",
            "page" to 2
        ),
        mapOf(
            "icon" to R.drawable.icon_person,
            "label" to "个人中心",
            "page" to 3
        ),
    )
    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .zIndex(3f)
                .offset(y = (-64).dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.White,
                            Color(0xFFE2E8F5)
                        )
                    ),
                    shape = CircleShape
                )
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF3E83FF), shape = CircleShape)
                    .size(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+", color = Color.White, fontSize = 30.sp)
            }
        }
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
            items.forEachIndexed { index, it ->
                if (index == 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = it["icon"] as Int
                            ),
                            contentDescription = it["label"] as String,
                            modifier = Modifier.size(20.dp),
                            tint = (if (currentPage == it["page"] as Int?) Color(0xFF3D8AFF) else LocalContentColor.current),
                        )
                    },
                    selected = currentPage == it["page"] as Int,
                    label = {
                        Text(
                            it["label"] as String,
                            color = (if (currentPage == it["page"] as Int?) Color(0xFF3D8AFF) else YitaiGray)
                        )
                    },
                    onClick = {
                        scrollToPage(it["page"] as Int)
                    }
                )
            }
        }
    }
}