package icu.ofatal.yitai.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientButton(
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit
) {
    Button(
        modifier = Modifier.wrapContentWidth().padding(horizontal = 16.dp, vertical = 8.dp).then(modifier),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        shape = RoundedCornerShape(100.dp)
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .wrapContentWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp).then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}