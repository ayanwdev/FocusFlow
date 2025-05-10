package dev.ayanw.focusflow.ui.components.Buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.ayanw.focusflow.ui.theme.TailWind

@Preview
@Composable
fun TimeControlButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: Dp = 50.dp,
    icon: ImageVector = Icons.Rounded.PlayArrow,
    iconSize: Dp = 40.dp,
    bgColor: Color = TailWind.Indigo_500,
    iconColor: Color = TailWind.Gray_100,
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
            imageVector = icon,
            contentDescription = "TimeControlButton",
        )
    }
}