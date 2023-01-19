package icu.ofatal.yitai.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.ofatal.yitai.R

@Composable
fun YTTopBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_menu_outline),
            modifier = Modifier.size(35.dp),
            contentDescription = null
        )
        Text("消息", color = colorResource(id = R.color.theme_color), fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.icon_notifications_dot),
            modifier = Modifier.size(35.dp),
            contentDescription = null
        )
    }
}