package icu.ofatal.yitai.ui.screen.equipment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R
import icu.ofatal.yitai.data.api.Bemfa
import icu.ofatal.yitai.ui.component.DashedDivider
import icu.ofatal.yitai.ui.component.VerticalSlider

@Preview
@Composable
fun EquipmentAdjustScreen() {
    Box(modifier = Modifier.fillMaxHeight(fraction = 0.85f)) {
        Image(
            painter = painterResource(id = R.drawable.equipment_adjustment_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(20.dp)) {
            buildTopBar()
            buildAdjust()
            buildAdjustPrecise()
        }
    }
}

@Composable
private fun buildTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("设备微调", fontSize = 18.sp)
        Image(
            painter = painterResource(id = R.drawable.icon_calendar),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
private fun buildAdjust() {
    val bemfa = remember {
        Bemfa()
    }
    Column {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Box(
//                modifier = Modifier
//                    .shadow(
//                        40.dp, shape = CircleShape, spotColor = Color(0xFFBAC2E7)
//                    )
//                    .size(120.dp)
//                    .background(Color.White, shape = CircleShape)
//                    .clickable {
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = "启动", fontSize = 24.sp, maxLines = 2, letterSpacing = 1.sp)
//            }
//        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .shadow(40.dp, shape = CircleShape, spotColor = Color(0xFFBAC2E7))
                    .size(68.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable {
                        bemfa.front()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "-", fontSize = 25.sp, color = Color(0xFF3D8AFF))
            }
//        Box(
//            modifier = Modifier
//                .size(120.dp)
//                .background(Color.White, shape = CircleShape), contentAlignment = Alignment.Center
//        ) {
//            Text(text = "松紧\n调节", fontSize = 24.sp, maxLines = 2, letterSpacing = 1.sp)
//        }
            Image(
                painter = painterResource(id = R.drawable.icon_adjustment),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )

            Box(
                modifier = Modifier
                    .shadow(40.dp, shape = RoundedCornerShape(22.dp), spotColor = Color(0xFFBAC2E7))
                    .size(68.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable {
                        bemfa.back()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+", fontSize = 25.sp, color = Color(0xFF3D8AFF))
            }
        }
        Column(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = "XXXX", color = Color(0xFF145AC4), fontSize = 18.sp)
            Text(text = "压强", color = Color(0xFF3D8AFF), fontSize = 15.sp)
        }
    }
}

@Composable
private fun buildAdjustPrecise() {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = "细节调整",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 24.dp)
        )
        Box(
            modifier = Modifier
                .shadow(40.dp, shape = RoundedCornerShape(22.dp), spotColor = Color(0xFFBAC2E7))
                .background(color = Color.White, shape = RoundedCornerShape(22.dp))
                .fillMaxWidth()
                .padding(20.dp)

        ) {
            Column {
                Box(contentAlignment = Alignment.Center) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        VerticalSlider(
                            progressValue = 25,
                            width = 6.dp,
                            height = 136.dp,
                            progressTrackColor = Color(0xFFFF8D1A).copy(0.62f)
                        )
                        VerticalSlider(
                            progressValue = 70,
                            width = 6.dp,
                            height = 136.dp,
                            progressTrackColor = Color(0xFF3D87FF).copy(0.62f)
                        )
                        VerticalSlider(
                            progressValue = 90,
                            width = 6.dp,
                            height = 136.dp,
                            progressTrackColor = Color(0xFF3D87FF).copy(0.62f)
                        )
                        VerticalSlider(
                            progressValue = 80,
                            width = 6.dp,
                            height = 136.dp,
                            progressTrackColor = Color(0xFF3D87FF).copy(0.62f)
                        )
                        VerticalSlider(
                            progressValue = 40,
                            width = 6.dp,
                            height = 136.dp,
                            progressTrackColor = Color(0xFFFF8D1A).copy(0.62f)
                        )
                        VerticalSlider(
                            progressValue = 75,
                            width = 6.dp,
                            height = 136.dp,
                            progressTrackColor = Color(0xFF3D87FF).copy(0.62f)
                        )
                        VerticalSlider(
                            progressValue = 45,
                            width = 6.dp,
                            height = 136.dp,
                            progressTrackColor = Color(0xFFFF8D1A).copy(0.62f)
                        )
                        VerticalSlider(
                            progressValue = 25,
                            width = 6.dp,
                            height = 136.dp,
                            progressTrackColor = Color(0xFFFF8D1A).copy(0.62f)
                        )
                    }
                    DashedDivider(
                        thickness = 1.dp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        color = Color(0xFFE9EAFF)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("节点1")
                    Text("节点3")
                    Text("节点5")
                    Text("节点8")
                }
            }
        }
    }
}