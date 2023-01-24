package icu.ofatal.yitai.ui.screen.person

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R

@Preview
@Composable
fun DoctorPersonScreen() {
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
                .padding(bottom = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                Text("个人中心", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
                buildBaseInfo()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFF7FBFF), Color(0xFFF2F6FC)),
                    )
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            buildMenus()
            buildList()
        }
    }
}

@Composable
private fun buildMenus() {
    Box(
        modifier = Modifier
            .shadow(3.dp, shape = RoundedCornerShape(8.dp))
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_doctor_person_menu1),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text("我的问诊", fontSize = 13.sp)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_doctor_person_menu2),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text("最新资讯", fontSize = 13.sp)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_doctor_person_menu3),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text("我的互动", fontSize = 13.sp)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_doctor_person_menu4),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text("最近浏览", fontSize = 13.sp)
            }
        }
    }
}

@Composable
private fun buildList() {
    Box(
        modifier = Modifier
            .shadow(3.dp, shape = RoundedCornerShape(8.dp))
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_doctor_person_list1),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text("我发布的内容")
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp),
                color = Color(0xFFB0B1B1).copy(0.2f)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_doctor_person_list2),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text("意见反馈")
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp),
                color = Color(0xFFB0B1B1).copy(0.2f)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_doctor_person_list3),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text("设置")
            }
        }
    }
}

@Composable
private fun buildBaseInfo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp, color = colorResource(
                        id = R.color.theme_color
                    ),
                    shape = CircleShape
                )
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.course_author1),
                contentDescription = null,
                modifier = Modifier
                    .size(93.dp)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            androidx.compose.material.Text(
                text = "医师A",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            androidx.compose.material.Text("浙江杭州", fontSize = 14.sp, color = Color.LightGray)
            androidx.compose.material.Text(
                text = "这是一段普通的个人介绍",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}