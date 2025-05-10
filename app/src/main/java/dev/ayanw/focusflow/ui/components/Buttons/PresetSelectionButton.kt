package dev.ayanw.focusflow.ui.components.Buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.ui.theme.Quicksand_Bold
import dev.ayanw.focusflow.ui.theme.TailWind

@Composable
fun PresetSelectionButton() {
    Button(
        onClick = {},
        colors = ButtonColors(
            containerColor = TailWind.Gray_800,
            contentColor = TailWind.Gray_100,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
        ),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .background(color = TailWind.Gray_800)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(14.dp)
                    .width(14.dp)
                    .background(color = TailWind.Emerald_500)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "TIMER",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = TailWind.Gray_100,
                    fontFamily = Quicksand_Bold
                ),
            )
        }
    }
}