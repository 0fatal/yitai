package icu.ofatal.yitai.ui.screen.equipment

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import icu.ofatal.yitai.R
import icu.ofatal.yitai.data.api.Bemfa
import icu.ofatal.yitai.ui.component.DashedDivider
import icu.ofatal.yitai.ui.component.VerticalSlider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.Timer
import java.util.TimerTask

@Preview
@Composable
fun EquipmentAdjustScreen(vm: EquipmentAdjustViewModel = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxHeight(fraction = 0.85f)) {
        Image(
            painter = painterResource(id = R.drawable.equipment_adjustment_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(20.dp)) {
            buildTopBar()
            buildAdjust(vm)
            buildAdjustPrecise(vm)
        }
    }
}

@Composable
private fun buildTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("设备微调", fontSize = 18.sp)
        Image(
            painter = painterResource(id = R.drawable.icon_calendar),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
private fun buildAdjust(vm: EquipmentAdjustViewModel) {
    val context = LocalContext.current

    Column {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Box(
//                modifier = Modifier
//                    .shadow(
//                        40.dp, shape = CircleShape, spotColor = Color(0xFFBAC2E7)
//                    )
//                    .size(120.dp)
//                    .background(Color.White, shape = CircleShape)
//                    .clickable {
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = "启动", fontSize = 24.sp, maxLines = 2, letterSpacing = 1.sp)
//            }
//        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .shadow(40.dp, shape = CircleShape, spotColor = Color(0xFFBAC2E7))
                    .size(68.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable {
                        vm.frontEquipment {
                            if(it != null) {
                                Toast.makeText(context,"发生了错误: ${it!!.message}", Toast.LENGTH_SHORT).show()
                                return@frontEquipment
                            }

                            val timer = Timer()
                            val timerTask = object : TimerTask() {
                                override fun run() {
                                    vm.fetchData()
                                }
                            }
                            timer.schedule(timerTask, 6000)

                            runBlocking {
                                withContext(Dispatchers.Main) {
                                    Toast
                                        .makeText(context, "设备调松", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "-", fontSize = 25.sp, color = Color(0xFF3D8AFF))
            }
//        Box(
//            modifier = Modifier
//                .size(120.dp)
//                .background(Color.White, shape = CircleShape), contentAlignment = Alignment.Center
//        ) {
//            Text(text = "松紧\n调节", fontSize = 24.sp, maxLines = 2, letterSpacing = 1.sp)
//        }
            Image(
                painter = painterResource(id = R.drawable.icon_adjustment),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )

            Box(
                modifier = Modifier
                    .shadow(40.dp, shape = RoundedCornerShape(22.dp), spotColor = Color(0xFFBAC2E7))
                    .size(68.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable {
                        vm.backEquipment {
                            if(it != null) {
                                Toast.makeText(context,"发生了错误: ${it!!.message}", Toast.LENGTH_SHORT).show()
                                return@backEquipment
                            }

                            val timer = Timer()
                            val timerTask = object : TimerTask() {
                                override fun run() {
                                    vm.fetchData()
                                }
                            }
                            timer.schedule(timerTask, 6000)


                            runBlocking {
                                withContext(Dispatchers.Main) {
                                    Toast
                                        .makeText(context, "设备调紧", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+", fontSize = 25.sp, color = Color(0xFF3D8AFF))
            }
        }
//        Column(
//            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(4.dp)
//        ) {
//            Text(text = "XXXX", color = Color(0xFF145AC4), fontSize = 18.sp)
//            Text(text = "压强", color = Color(0xFF3D8AFF), fontSize = 15.sp)
//        }
    }
}

@Composable
private fun buildAdjustPrecise(vm: EquipmentAdjustViewModel) {
    val pressValue by vm.pressValue.collectAsState()
    val error by vm.error.collectAsState()
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val timer = Timer()
        var firstRun = false
        val timerTask = object : TimerTask() {
            override fun run() {
                if(!firstRun) {
                    firstRun = true
                    runBlocking {
                        vm.twinkle()
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context,"连接成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
//                Log.d("ha","begin fetch")
                vm.fetchData()
            }
        }
        timer.schedule(timerTask, 50, 9000000)

        onDispose {
            timerTask.cancel()
            timer.cancel()
        }
    }

    LaunchedEffect(key1 = error) {
        if(error != null) {
            Toast.makeText(context,"发生了错误: ${error!!.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = "矫形力数据",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 20.dp, bottom = 24.dp)
        )
        Box(
            modifier = Modifier
                .shadow(40.dp, shape = RoundedCornerShape(22.dp), spotColor = Color(0xFFBAC2E7))
                .background(color = Color.White, shape = RoundedCornerShape(22.dp))
                .fillMaxWidth()
                .padding(20.dp)

        ) {
            Column {
                Box(contentAlignment = Alignment.Center) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        pressValue.forEachIndexed {idx, it ->
                            val value = (it * 100).toInt()
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                VerticalSlider(
                                    progressValue = value,
                                    width = 6.dp,
                                    height = 136.dp,
                                    progressTrackColor = Color(listOf(0xFFFF8D1A, 0xFF3D87FF,0xFF3D87FF,0xFF3D87FF,0xFFFF8D1A,0xFF3D87FF,0xFFFF8D1A,0xFFFF8D1A,0xFF3D87FF)[idx]).copy(0.62f)
                                )
                                Text("F${idx+1}", fontSize = 10.sp)
                                Text("${String.format("%.2f", it)}N", fontSize = 8.sp)
                            }
                        }
                    }
                    DashedDivider(
                        thickness = 1.dp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        color = Color(0xFFE9EAFF)
                    )
                }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 6.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text("节点1")
//                    Text("节点3")
//                    Text("节点5")
//                    Text("节点8")
//                }
            }
        }
    }
}