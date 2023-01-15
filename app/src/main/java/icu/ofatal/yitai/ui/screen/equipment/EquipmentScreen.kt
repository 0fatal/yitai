package icu.ofatal.yitai.ui.screen.equipment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R

@Preview
@Composable
fun EquipmentScreen() {
    Scaffold(bottomBar = {
        buildBottomBar()
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.equipment_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(it)
                    .padding(20.dp)
            ) {
                buildTopBar()
                buildDashboard()
                buildDataBoard()
            }
        }
    }
}

@Composable
private fun buildTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("我的设备", fontSize = 30.sp, fontWeight = FontWeight(500))
        Image(
            painter = painterResource(id = R.drawable.icon_equipment_menu),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun buildDashboard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 15.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_calendar),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.equipment_calories),
            contentDescription = null,
            modifier = Modifier
                .size(264.dp, 155.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .offset(y = (-50).dp)
        )
    }
}


@Composable
private fun buildDataBoard() {
    val itemSize = (LocalConfiguration.current.screenWidthDp.dp / 2.4f)

    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFF3D87FF).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(22.dp)
                    )
                    .width(itemSize)
                    .padding(horizontal = 14.dp)
                    .padding(top = 12.dp, bottom = 24.dp),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
                    Text("今日佩戴时长", color = Color(0xFF3D8AFF), fontSize = 15.sp)
                    Text("6h 47m", fontSize = 20.sp)
                    Image(
                        painter = painterResource(id = R.drawable.equipment_chart_day),
                        contentDescription = null,
                        modifier = Modifier.size(124.dp, 59.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .background(
                        color = Color(0xFFF7E8CC), shape = RoundedCornerShape(22.dp)
                    )
                    .width(itemSize)
                    .padding(horizontal = 14.dp)
                    .padding(top = 12.dp, bottom = 24.dp)

            ) {
                Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
                    Text("健康得分", color = Color(0xFFF8AF0A), fontSize = 15.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(33.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                            Text("85", fontSize = 20.sp)
                            Text("Score", fontSize = 15.sp)
                        }
                        Image(
                            painter = painterResource(id = R.drawable.equipment_chart_score),
                            contentDescription = null,
                            modifier = Modifier.size(52.dp, 52.dp)
                        )
                    }
                }
            }
        }
        Column() {
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFF7DAEC), shape = RoundedCornerShape(22.dp)
                    )
                    .width(itemSize)
                    .padding(horizontal = 14.dp)
                    .padding(top = 12.dp, bottom = 24.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(13.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("详细佩戴监测", color = Color(0xFFFF6B9E), fontSize = 15.sp)
                    Image(
                        painter = painterResource(id = R.drawable.euipment_chart_watch),
                        contentDescription = null,
                        modifier = Modifier
                            .size(68.dp, 68.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFC4ECF2).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(22.dp)
                    )
                    .width(itemSize)
                    .padding(horizontal = 14.dp)
                    .padding(top = 12.dp, bottom = 24.dp),

                ) {
                Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
                    Text("微调", color = Color(0xFF00AE98), fontSize = 15.sp)
                    Text("点击进入", fontSize = 20.sp)
                    Image(
                        painter = painterResource(id = R.drawable.equipment_chart_adjust),
                        contentDescription = null,
                        modifier = Modifier.size(156.dp, 55.dp)
                    )
                }
            }
        }
    }
}


@Composable
private fun buildBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 25.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "我的设备",
            color = Color(0xFF3D8AFF),
            fontSize = 15.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.icon_add_circle),
            contentDescription = null,
            modifier = Modifier
                .size(68.dp)
                .weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.icon_notification2),
            contentDescription = null,
            modifier = Modifier
                .size(19.dp, 20.dp)
                .weight(1f)
        )
    }
}