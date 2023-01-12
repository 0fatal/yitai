package icu.ofatal.yitai.ui.screen.playground

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R
import icu.ofatal.yitai.data.model.MockProcutions

@Composable
fun PlaygroundScreen() {
    val scrollState = rememberScrollState(0)

    Scaffold(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color(0xFFFBFBFD))
                .fillMaxSize()
                .padding(it)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            buildTopBar()
            buildHeadline()
            buildSearchBox()
            buildMenu()
            buildNewProduction()
        }
    }
}

@Composable
private fun buildHeadline() {
    Column {
        Text(
            stringResource(id = R.string.app_name),
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(R.font.alimamashuhiti_bold))
        )
        Text("只为你的健康", fontSize = 20.sp, color = Color.LightGray, letterSpacing = 2.sp)
    }
}


@Composable
private fun buildTopBar(modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = R.drawable.icon_more),
            contentDescription = "更多",
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(
                id = R.drawable.icon_notification_normal
            ),
            contentDescription = "通知",
            modifier = Modifier.size(24.dp)
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
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.map { v ->
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .width(75.dp)
                    .height(75.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = v["icon"] as Int),
                        contentDescription = v["text"] as String,
                        modifier = Modifier.size(30.dp, 32.dp)
                    )
                    Text(v["text"] as String, fontSize = 12.sp, color = Color.DarkGray)
                }
            }
        }
    }
}


@Preview
@Composable
private fun buildNewProduction() {
    val scrollState = rememberScrollState(0)

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("新上市", fontSize = 20.sp)
            Text("查看全部", fontSize = 14.sp, color = Color.LightGray)
        }


        Row(
            modifier = Modifier
                .horizontalScroll(
                    state = scrollState
                )

        ) {
            MockProcutions().map {
                Card(
                    modifier = Modifier
                        .padding(1.dp)
                        .size(154.dp, 185.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(1.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = it.image),
                            contentScale = ContentScale.Crop,
                            contentDescription = it.name,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                                .fillMaxWidth()
                                .aspectRatio(16 / 14f)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    it.name,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    it.intro,
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Text(
                                String.format("¥ %.1f", it.price),
                                fontSize = 12.sp,
                                color = colorResource(
                                    id = R.color.theme_color
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}

@Composable
private fun buildSearchBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(14.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
            Text(text = "搜索...", fontSize = 14.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.theme_color), shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_option),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(
    widthDp = 200,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun PreviewSearchBox() {
    buildSearchBox()
}