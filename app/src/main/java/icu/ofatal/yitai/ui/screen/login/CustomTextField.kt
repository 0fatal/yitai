package icu.ofatal.yitai.ui.screen.login

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)?
) {
    val interactionSource = remember { MutableInteractionSource() }
    val enabled = true
    val singleLine = true

    val colors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Color.DarkGray,
        focusedBorderColor = Color.DarkGray,
        backgroundColor = Color.White
    )
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        // internal implementation of the BasicTextField will dispatch focus events
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = value,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = singleLine,
            enabled = enabled,

            // same interaction source as the one passed to BasicTextField to read focus state
            // for text field styling
            interactionSource = interactionSource,
            // keep vertical paddings but change the horizontal
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = 8.dp, end = 8.dp
            ),
            // update border thickness and shape
            border = {
                TextFieldDefaults.BorderBox(
                    enabled = enabled,
                    isError = false,
                    colors = colors,
                    interactionSource = interactionSource,
                    shape = RoundedCornerShape(30.dp),
                    unfocusedBorderThickness = 2.dp,
                    focusedBorderThickness = 4.dp
                )
            },
            // update border colors
            colors = colors,
            placeholder = placeholder,
            )
    }
}
