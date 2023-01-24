package icu.ofatal.yitai.ui.screen.community

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R
import icu.ofatal.yitai.data.model.MockPostList
import icu.ofatal.yitai.data.model.Post

@Preview
@Composable
fun CommunityScreen() {
    Scaffold() {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(it)
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Image(painter = painterResource(id = R.drawable.icon_arrow_back), contentDescription = null, modifier = Modifier.size(21.dp, 15.dp))
                Text("在线社区", fontSize = 16.sp)
                Spacer(modifier = Modifier.size(21.dp))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(modifier = Modifier.drawBehind {
                    val y = size.height + 6f
                    drawLine(
                        Color.Black,
                        Offset(0f, y),
                        Offset(size.width, y),
                        5f
                    )
                }) {
                    Text(text = "热门")
                }
                Box() {
                    Text(text = "最新", color = Color(0xFF9699A2))
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                MockPostList().map {
                    buildPost(post = it)
                }
            }
        }
    }
}

@Composable
fun buildPost(post: Post) {
    Box(
        modifier = Modifier
            .shadow(8.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = post.author.avatar),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Column() {
                        Text(text = post.author.name, fontSize = 16.sp)
                        Text(text = post.time, fontSize = 12.sp, color = Color(0xFFA6A8AD))
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.icon_more2),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(text = post.content)
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_forward),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_comment),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = post.commentCount.toString(), fontSize = 13.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_like),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = post.likeCount.toString(), fontSize = 13.sp)
                }
            }
        }
    }
}