package icu.ofatal.yitai.ui.screen.notification

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R
import icu.ofatal.yitai.data.model.MockMessageList
import icu.ofatal.yitai.ui.component.YTTopBar
import icu.ofatal.yitai.ui.theme.SmallGray

@Preview
@Composable
fun NotificationScreen() {
    val scrollState = rememberScrollState(0)
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            YTTopBar("消息")
            buildSearchBox()
            buildMsgList()
        }
    }
}

@Preview
@Composable
fun NotificationScreenInline() {
    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        YTTopBar("消息")
        buildSearchBox()
        buildMsgList()
    }
}


@Composable
private fun buildSearchBox() {
    Box(
        modifier = Modifier
            .background(SmallGray, shape = RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text("查找...", fontSize = 14.sp, color = Color.LightGray)
        }
    }
}

@Composable
private fun buildMsgList() {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        MockMessageList().map {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Blue,
                            shape = CircleShape
                        )
                        .padding(3.dp)
                ) {
                    Image(
                        painter = painterResource(id = it.target.avatar),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(it.target.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(
                        it.latestMsg, color = Color.DarkGray, fontSize = 14.sp, maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(it.time, color = Color.LightGray, fontSize = 12.sp)
            }
            Divider(color = Color(0xFFE4EBEC), thickness = (0.5).dp)
        }
    }
}
