package dev.ayanw.focusflow.ui.components.presetList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SlowMotionVideo
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.ui.theme.Quicksand_Bold
import dev.ayanw.focusflow.ui.theme.TailWind

//Column (
//modifier = Modifier
//.padding(8.dp)
//.clip(RoundedCornerShape(4.dp))
//) {
//    Row(
//        modifier = Modifier
//            .background(color = TailWind.Slate_800)
//            .padding(16.dp)
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Box(
//            modifier = Modifier
//                .size(20.dp)
//                .background(
//                    color = TailWind.Emerald_500,
//                    shape = RoundedCornerShape(4.dp)
//                )
//                .border(
//                    width = 2.dp,
//                    color = TailWind.Gray_100,
//                    shape = RoundedCornerShape(4.dp)
//                )
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(
//            text = preset.name,
//            style = TextStyle(
//                fontSize = 20.sp,
//                color = TailWind.Gray_100,
//                fontFamily = Quicksand_Bold
//            ),
//        )
//    }
//    Row() {
//        buttons.forEach { button ->
//            IconButton(
//                onClick = button.action,
//                colors = IconButtonColors(
//                    contentColor = button.color,
//                    containerColor = Color.Transparent,
//                    disabledContainerColor = Color.Transparent,
//                    disabledContentColor = Color.Transparent,
//                )
//            ) {
//                Icon(
//                    imageVector = button.icon,
//                    contentDescription = "Button",
//                )
//            }
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun PresetListItem(
    color: Color = TailWind.Gray_100,
    name: String = "Preset",
    favourite: Boolean = false,
    isActive: Boolean = false,
) {

    val buttons: List<ItemButtonProps> = listOf(
        ItemButtonProps(icon = Icons.Rounded.SlowMotionVideo),
        ItemButtonProps(icon = Icons.Sharp.Star),
        ItemButtonProps(icon = Icons.Rounded.Tune),
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = TailWind.Slate_800,
                shape = RoundedCornerShape(4.dp)
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = TailWind.Emerald_500,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = TailWind.Gray_100,
                            shape = RoundedCornerShape(4.dp)
                        ),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = TailWind.Gray_100,
                        fontFamily = Quicksand_Bold
                    )
                )
            }
            Row {
                buttons.forEach { button ->
                    Box(modifier = Modifier.padding(start = 4.dp)) {
                        Button(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(shape = CircleShape),
                            onClick = button.action,
                            colors = ButtonColors(
                                contentColor = button.color,
                                containerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = Color.Transparent,
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                imageVector = button.icon,
                                contentDescription = "Button",
                            )
                        }
                    }
                }
            }
        }
    }
}