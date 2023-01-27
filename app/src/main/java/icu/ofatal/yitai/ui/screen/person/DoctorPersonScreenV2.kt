package icu.ofatal.yitai.ui.screen.person

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import icu.ofatal.yitai.R
import icu.ofatal.yitai.ui.component.YTTopBar
import icu.ofatal.yitai.util.findActivity

@Composable
fun DoctorPersonScreenV2(modifier: Modifier = Modifier, navController: NavController) {
    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        YTTopBar(title = "个人中心", color = Color(0xFF3D8AFF))
        buildBaseInfo()
        buildFunction(navController = navController)
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
                painter = painterResource(id = R.drawable.avatar2),
                contentDescription = null,
                modifier = Modifier
                    .size(93.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "普通医生A", fontSize = 19.sp, fontWeight = FontWeight.Bold)
            Text("浙江杭州", fontSize = 14.sp, color = Color.LightGray)
            Text(text = "这是一段普通的个人介绍", fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Composable
private fun buildFunction(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "病例管理",
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    navController.navigate("doctor/index") {
                        popUpTo("doctor/index") {
                            inclusive = true
                        }
                    }
                })
            Divider(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .width(1.dp)
                    .height(16.dp)
            )
            Text("实时监测", fontSize = 16.sp)
        }
        Divider(thickness = 2.dp, color = Color(0xFFFBB8A2))

        FlowRow(
            mainAxisSize = com.google.accompanist.flowlayout.SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
        ) {
            buildSubFunction(
                title = "设备微调",
                icon = R.drawable.icon_func_adjustment,
                path = "equipment/adjust",
                navController = navController
            )
            buildSubFunction(
                title = "初步建模",
                icon = R.drawable.icon_func_course,
                path = "timely-watch",
                navController = navController
            )
            buildSubFunction(
                title = "在线商城",
                icon = R.drawable.icon_func_shop,
                path = "common/index",
                navController = navController
            )
            buildSubFunction(
                title = "在线社区",
                icon = R.drawable.icon_func_community,
                path = "community",
                navController = navController
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}
