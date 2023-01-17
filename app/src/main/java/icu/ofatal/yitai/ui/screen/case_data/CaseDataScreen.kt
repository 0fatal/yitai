package icu.ofatal.yitai.ui.screen.case_data

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R

@Composable
fun CaseDataScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        buildTopCard()
        buildData()
    }
}

@Composable
private fun buildSearchBox() {
    Box(
        modifier = Modifier.background(Color(0xFF3D8BFF), shape = CircleShape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text("搜索", color = Color(0xFFC2DDFF), fontSize = 14.sp)
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun buildAvatarBox() {
    Box(
        modifier = Modifier
            .background(Color.White, shape = CircleShape)
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.msg_list_people1),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
private fun buildTopCard() {
    Box(
        modifier = Modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF2E7AE6),
                        Color(0xFF053476)
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .height(246.dp)
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                buildSearchBox()
                buildAvatarBox()
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("我的病例", color = Color.White, fontSize = 20.sp)
                    Text("共12个", color = Color(0xFFC2DDFF), fontSize = 12.sp)
                }
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D8BFF))
                ) {
                    Text("近一周", color = Color.White)
                }
            }

        }
    }
}

private fun buildData() {

}