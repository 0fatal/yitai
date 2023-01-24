package icu.ofatal.yitai.ui.screen.health

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import icu.ofatal.yitai.R
import icu.ofatal.yitai.ui.component.YTTopBarWithBack
import icu.ofatal.yitai.ui.screen.equipment.buildDataBoard

@Composable
fun HealthScreen(navController: NavController) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            YTTopBarWithBack(title = "健康信息", navController = navController)
            Text("健康报告", fontSize = 17.sp, fontWeight = FontWeight.Bold)
            buildDataBoard()
            Text("历史报告", fontSize = 17.sp, fontWeight = FontWeight.Bold)
            buildHistoryReport(bg = Color(0xFFDCEDF9), icon = R.drawable.icon_doc1)
            buildHistoryReport(bg = Color(0xFFD6FFF3), icon = R.drawable.icon_doc2)
        }
    }
}

@Composable
private fun buildHistoryReport(bg: Color, icon: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                6.dp,
                spotColor = Color(0xFF6B86B3).copy(0.25f),
                shape = RoundedCornerShape(28.dp)
            )
            .border(1.dp, color = Color(0xFFD7DDEA), shape = RoundedCornerShape(28.dp))
            .background(Color.White, shape = RoundedCornerShape(28.dp))
            .padding(horizontal = 11.dp, vertical = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .background(bg, shape = RoundedCornerShape(20.dp))
                    .size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("健康报告", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("8 files", color = Color.DarkGray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.icon_more2),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}