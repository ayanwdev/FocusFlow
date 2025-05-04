package dev.ayanw.focusflow.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.ayanw.focusflow.ui.theme.TwColors

@Preview
@Composable
fun timeControlButton(
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    iconSize: Dp = 40.dp,
    icon: ImageVector = Icons.Rounded.Refresh,
    onClick: () -> Unit = {},
) {
    Button(
        modifier =
            modifier
                .height(size)
                .width(size),
        onClick = onClick,
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            contentColor = TwColors.Gray_100,
            containerColor = TwColors.Indigo_500,
            disabledContainerColor = TwColors.Gray_100,
            disabledContentColor = TwColors.Gray_100,
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