package icu.ofatal.yitai.ui.screen.playground

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R
import icu.ofatal.yitai.data.model.MockProcutions

@Composable
fun PlaygroundScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            buildTopBar()
            buildHeadline()
            buildMenu()
            buildNewProduction()
        }
    }
}

@Composable
private fun buildHeadline() {
    Column {
        Text(stringResource(id = R.string.app_name))
        Text("只为你的健康")
    }
}


@Composable
private fun buildTopBar() {
    Row {
        Icon(painter = painterResource(id = R.drawable.icon_more), contentDescription = "更多")
        Icon(
            painter = painterResource(id = R.drawable.icon_notification_normal),
            contentDescription = "通知"
        )
    }
}

@Composable
private fun buildMenu() {
    val items = listOf<Map<String, *>>(
        mapOf(
            "icon" to R.drawable.icon_util,
            "text" to "支具",
        ),
        mapOf(
            "icon" to R.drawable.icon_equipment,
            "text" to "康复器材",
        ),
        mapOf(
            "icon" to R.drawable.icon_medicine,
            "text" to "康复药物",
        ),
        mapOf(
            "icon" to R.drawable.icon_equipment,
            "text" to "健身",
        )
    )

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        items.map { v ->
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = v["icon"] as Int),
                        contentDescription = v["text"] as String
                    )
                    Text(v["text"] as String)
                }
            }
        }
    }
}

@Composable
private fun buildNewProduction() {
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text("新上市", fontSize = 20.sp)
            Text("查看全部", fontSize = 14.sp, color = Color.LightGray)
        }

        MockProcutions().map {
            Box {
                Column {
                    Image(
                        painter = painterResource(id = it.image),
                        contentDescription = it.name,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    )
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text(it.name)
                            Text(it.intro)
                        }
                        Text(String.format("%.1f", it.price))
                    }
                }
            }
        }
    }
}