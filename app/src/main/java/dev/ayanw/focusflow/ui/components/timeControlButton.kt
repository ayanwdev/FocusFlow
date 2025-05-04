package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.ayanw.focusflow.R
import dev.ayanw.focusflow.ui.theme.TwColors

@Preview
@Composable
fun timeControlButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: Dp = 50.dp,
    iconSize: Dp = 40.dp,
    icon: Int = R.drawable.ic_play,
    bgColor: Color = TwColors.Indigo_500,
    iconColor: Color = TwColors.Gray_100,
    onClick: () -> Unit = {},
) {
    Button(
        modifier =
            modifier
                .height(size)
                .width(size),
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            contentColor = iconColor,
            containerColor = bgColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        ),
    ) {
        Icon(
            modifier =
                Modifier
                    .height(iconSize)
                    .width(iconSize),
            painter = painterResource(id = icon),
            contentDescription = "TimeControlButton",
        )
    }
}