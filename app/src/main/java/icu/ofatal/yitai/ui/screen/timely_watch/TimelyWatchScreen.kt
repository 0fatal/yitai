package icu.ofatal.yitai.ui.screen.timely_watch

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import icu.ofatal.yitai.R
import kotlin.math.absoluteValue

@Composable
fun TimelyWatchScreen(navController: NavController) {
    val pagerState = rememberPagerState(initialPage = 1)

    Box() {
        Column() {
            Box(
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF2E7AE6), Color(0xFF093D87)),
                            start = Offset(-120f, 550f),
                            end = Offset(300f, -100f),
                        )
                    )
                    .height(346.dp)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFF0C4492)
                    )
                    .height(320.dp)
                    .fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                buildTopBar(navController = navController)
                buildModel(pagerState)
            }
            buildModelAdjust(pagerState)
        }
    }

}

@Composable
fun buildTopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .size(21.dp, 15.dp).clickable { navController.popBackStack() },
                tint = Color.White
            )
        }
        Text(
            "实时监测",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 18.sp
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = null,
                modifier = Modifier
                    .size(21.dp, 15.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.icon_menu2),
                contentDescription = null,
                modifier = Modifier
                    .size(21.dp, 15.dp),
                tint = Color.White
            )
        }
    }
}


@Composable
fun buildModel(pagerState: PagerState) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("病例${pagerState.currentPage + 1}", color = Color.White, fontSize = 18.sp)
        HorizontalPager(
            count = 10, state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
//            contentPadding = PaddingValues(horizontal = 30.dp),
        ) { page ->
            Box(
                modifier = Modifier
                    .shadow(10.dp, shape = RoundedCornerShape(6.dp))
                    .fillMaxWidth()
                    .background(Color(0xFFC4E0FF).copy(0.73f), shape = RoundedCornerShape(6.dp))
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.case_example1),
                    contentDescription = null,
                    modifier = Modifier
                        .height(380.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun buildModelAdjust(pagerState: PagerState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        HorizontalPager(
            count = 10, state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 30.dp),
        ) { page ->
            Box(
                modifier = Modifier
                    .shadow(6.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .width(305.dp)
                    .padding(20.dp)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = ScaleFactor(0.85f, 0.85f),
                            stop = ScaleFactor(1f, 1f),
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale.scaleX
                            scaleY = scale.scaleY
                        }
                        alpha = lerp(
                            start = ScaleFactor(0.5f, 0.5f),
                            stop = ScaleFactor(1f, 1f),
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).scaleX
                    }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_doctor_clock),
                                contentDescription = null,
                                tint = Color(0xFF2674DA),
                                modifier = Modifier.size(11.dp)
                            )
                            Text(text = "正在监测", color = Color(0xFF2674DA), fontSize = 10.sp)
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFE0F5EB), RoundedCornerShape(1.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "确认", color = Color(0xFF56CB8F), fontSize = 10.sp)
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFF5DFDF), RoundedCornerShape(1.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "调整", color = Color(0xFFD43030), fontSize = 10.sp)
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "脊柱模型", fontSize = 16.sp)

                        Text(
                            text = "拖动滑块调整角度",
                            fontSize = 10.sp,
                            color = Color(0xFF8193AE),
                            modifier = Modifier.align(
                                alignment = Alignment.CenterHorizontally
                            )
                        )
                        Slider(value = 0.2f, onValueChange = {})
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFE8E8E8), shape = CircleShape)
                                    .size(23.dp)
                            )
                            Text("切换支具页面", fontSize = 10.sp, letterSpacing = 1.sp)
                        }
                    }
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(16.dp),
        )

    }
}

