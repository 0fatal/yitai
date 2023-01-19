package icu.ofatal.yitai.ui.screen.person

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import icu.ofatal.yitai.R
import icu.ofatal.yitai.ui.component.YTTopBar
import icu.ofatal.yitai.util.findActivity

@Preview
@Composable
fun PersonScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        YTTopBar(title = "个人中心")
        buildBaseInfo()
        buildFunction()
    }
}

@Composable
fun buildBaseInfo() {
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
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(93.dp)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "普通客户A", fontSize = 19.sp, fontWeight = FontWeight.Bold)
            Text("浙江杭州", fontSize = 14.sp, color = Color.LightGray)
            Text(text = "这是一段普通的个人介绍", fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun buildFunction() {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("设备管理", fontSize = 16.sp)
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(16.dp)
            )
            Text("实时监测", fontSize = 16.sp)
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(16.dp)
            )
            Text("健康信息", fontSize = 16.sp)
        }
        Divider(thickness = 2.dp, color = Color(0xFFFBB8A2))

        FlowRow(
            mainAxisSize = com.google.accompanist.flowlayout.SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
        ) {
            buildSubFunction(title = "设备微调", icon = R.drawable.icon_func_adjustment, path = "")
            buildSubFunction(title = "在线商城", icon = R.drawable.icon_func_shop, path = "")
            buildSubFunction(title = "课程资源", icon = R.drawable.icon_func_course, path = "")
            buildSubFunction(title = "在线社区", icon = R.drawable.icon_func_community, path = "")
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun adaptiveGridCell(): GridCells {
    val windowSizeClass = calculateWindowSizeClass(LocalContext.current.findActivity())
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> GridCells.Fixed(2)
        else -> GridCells.Adaptive(200.dp)
    }
}


@Composable
fun buildSubFunction(title: String, icon: Int, path: String) {
    val itemSize = (LocalConfiguration.current.screenWidthDp.dp / 2.3f)

    ElevatedCard(
        modifier = Modifier
            .size(itemSize)
            .padding(horizontal = 3.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(title, fontSize = 16.sp, color = Color.DarkGray)
        }
    }
}

