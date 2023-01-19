package icu.ofatal.yitai.ui.screen.case_data

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import icu.ofatal.yitai.R
import icu.ofatal.yitai.ui.component.DashedDivider

//@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun CaseDataScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        buildTopCard(modifier)
        buildData(navController = navController)
    }
}

@Composable
private fun buildSearchBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(Color(0xFF3D8BFF), shape = CircleShape)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
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
private fun buildScrollBox() {
    val scrollState = rememberScrollState(0)
    Row(
        modifier = Modifier
            .offset(y = 190.dp)
            .horizontalScroll(scrollState)
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .padding(start = if (it == 0) 20.dp else 0.dp)
                    .padding(end = 30.dp)
                    .shadow(15.dp, spotColor = Color(0xFFE5EAF5), shape = RoundedCornerShape(6.dp))
                    .background(
                        color = if (it == 0) Color(0xFF1B69C7) else Color(0xFF3C94FF),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .size(240.dp, 104.dp)
            ) {
                Row() {
                    Box(
                        modifier = Modifier
                            .width(5.dp)
                            .fillMaxHeight()
                            .background(
                                Color(0xFF66AAFF),
                                shape = RoundedCornerShape(6.dp, 0.dp, 0.dp, 6.dp)
                            )
                    )
                    Column(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(text = "患者提问/ 3名", color = Color.White, fontSize = 14.sp)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_doctor_clock),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "8:30 AM - 02:00 PM",
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                repeat(3) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFFFFFFF).copy(alpha = 0.44f),
                                                shape = CircleShape
                                            )
                                            .size(23.dp)
                                    )
                                }
                            }
                            Image(
                                painter = painterResource(id = R.drawable.icon_verified),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun buildTopCard(modifier: Modifier = Modifier) {
    var expanded = remember { mutableStateOf(false) }

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
            .zIndex(3f)
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(20.dp)
                .padding(top = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                buildSearchBox(modifier = Modifier.weight(1f))
                buildAvatarBox()
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "我的病例",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text("共 12 个", color = Color(0xFFC2DDFF), fontSize = 12.sp)
                }

                Column {
                    Button(
                        onClick = { expanded.value = true },
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D8BFF))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("近一周", color = Color.White)
                            Icon(
                                painter = painterResource(id = R.drawable.icon_arrow_down),
                                contentDescription = null,
                                modifier = Modifier.size(10.dp, 8.dp)
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                    ) {
                        DropdownMenuItem(onClick = { expanded.value = false }, text = {
                            Text("今天")
                        })
                        DropdownMenuItem(onClick = { expanded.value = false }, text = {
                            Text("近一月")
                        })
                        DropdownMenuItem(onClick = { expanded.value = false }, text = {
                            Text("近一年")
                        })
                    }
                }
            }

        }
        buildScrollBox()
    }
}

@Composable
private fun buildUpdatedData() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            "已更新",
            modifier = Modifier
                .background(Color(0xFFA1CDFF).copy(alpha = 0.21f))
                .padding(vertical = 4.dp, horizontal = 12.dp),
            color = Color(0xFFA6A6A6),
            fontSize = 14.sp
        )

        Box(modifier = Modifier.padding(start = 12.dp, end = 20.dp)) {
            DashedDivider(
                modifier = Modifier
                    .height(100.dp)
            )
        }
        Box(
            modifier = Modifier
                .shadow(13.dp, spotColor = Color(0xFFD6E2F6), shape = RoundedCornerShape(10.dp))
                .background(Color(0xFFF8FBFF), shape = RoundedCornerShape(10.dp))
                .size(240.dp, 60.dp)
                .padding(start = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color(0xFFB0BED4).copy(alpha = 0.1f), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_anesthesia),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "患者A数据库", fontSize = 12.sp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_doctor_clock),
                            contentDescription = null,
                            modifier = Modifier.size(11.dp)
                        )
                        Text(
                            "最后调整时间：20天前",
                            fontSize = 10.sp,
                            color = Color(0xFF8193AE)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun buildNotUpdatedData(navController: NavController) {
    val items = listOf(
        mapOf(
            "text" to "患者B数据库",
            "desc" to "待更新",
            "icon" to R.drawable.icon_appointment
        ),
        mapOf(
            "text" to "患者C数据库",
            "desc" to "待更新",
            "icon" to R.drawable.icon_anesthesia
        ),
        mapOf(
            "text" to "患者D数据库",
            "desc" to "待更新",
            "icon" to R.drawable.icon_mouthwash
        )
    )
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            "待更新",
            modifier = Modifier
                .background(Color(0xFFA1CDFF).copy(alpha = 0.21f))
                .padding(vertical = 4.dp, horizontal = 12.dp),
            color = Color(0xFFA6A6A6),
            fontSize = 14.sp
        )

        Box(modifier = Modifier.padding(start = 12.dp, end = 20.dp)) {
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(210.dp)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items.map {
                Box(
                    modifier = Modifier
                        .clickable { navController.navigate("timely-watch") }
                        .shadow(
                            13.dp,
                            spotColor = Color(0xFFD6E2F6),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(Color(0xFFF8FBFF), shape = RoundedCornerShape(10.dp))
                        .size(240.dp, 60.dp)
                        .padding(start = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    Color(0xFFB0BED4).copy(alpha = 0.1f),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = it["icon"] as Int),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(text = it["text"] as String, fontSize = 12.sp)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icon_doctor_clock),
                                    contentDescription = null,
                                    modifier = Modifier.size(11.dp)
                                )
                                Text(
                                    it["desc"] as String,
                                    fontSize = 10.sp,
                                    color = Color(0xFF8193AE)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun buildData(navController: NavController) {
    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF7FBFF),
                        Color(0xFFE2E9F5)
                    )
                )
            )
            .padding(top = 70.dp)
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        buildUpdatedData()
        buildNotUpdatedData(navController)
        Spacer(modifier = Modifier.height(100.dp))
    }
}