package icu.ofatal.yitai.ui.screen.course

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import icu.ofatal.yitai.data.model.Course
import icu.ofatal.yitai.data.model.MockCourse

@Preview
@Composable
fun CourseScreen() {
    val scrollState = rememberScrollState(0)
    Scaffold {
        Column(
            modifier = Modifier
                .background(color = Color(0xFFFBFBFD))
                .padding(it)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            buildTopBar()
            buildCourseList()
        }
    }
}

@Composable
private fun buildTopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = icu.ofatal.yitai.R.drawable.icon_arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Text(
            "我的课程",
            modifier = Modifier.weight(1f),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun buildCourseList() {
    val items = listOf(1, 3)
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row() {
                Text(items.size.toString(), fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text(" 门课程", fontSize = 17.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = icu.ofatal.yitai.R.drawable.icon_course_card_select),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp, 31.dp)
                )
                Image(
                    painter = painterResource(id = icu.ofatal.yitai.R.drawable.icon_course_list),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp, 16.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MockCourse().map {
                buildCourseCard(it)
            }
        }
    }
}


@Composable
private fun buildCourseCard(course: Course) {
    Box(
        modifier = Modifier
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .offset(20.dp, 157.dp)
                .shadow(4.dp, shape = CircleShape)
                .background(color = Color.White, shape = CircleShape)
                .padding(vertical = 4.dp, horizontal = 4.dp)
                .zIndex(3f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Image(
                    painter = painterResource(id = course.author.avatar),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text(course.author.name, modifier = Modifier.padding(end = 4.dp))
            }
        }
        Column {
            Image(
                painter = painterResource(id = course.cover),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 25.dp, bottom = 15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(course.name, fontSize = 15.sp)
                    Text("${course.totalCount} 个视频", fontSize = 13.sp, color = Color.Gray)
                }
                Box(
                    modifier = Modifier
                        .background(color = Color(0xFF46BD84), shape = CircleShape)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        "${course.finishCount} / ${course.totalCount}",
                        fontSize = 13.sp,
                        color = Color.White
                    )
                }
            }
        }
    }

}