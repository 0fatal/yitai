package icu.ofatal.yitai.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import icu.ofatal.yitai.R

@Composable
fun ScheduleScreen() {
    Image(
        painter = painterResource(id = R.drawable.screen_mock_1),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}