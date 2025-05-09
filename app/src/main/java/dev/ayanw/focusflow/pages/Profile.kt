package dev.ayanw.focusflow.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ayanw.focusflow.R
import dev.ayanw.focusflow.ui.theme.Quicksand_Bold
import dev.ayanw.focusflow.ui.theme.TailWind

@Preview(showBackground = true)
@Composable
fun Profile() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .size(175.dp)
                    .clip(CircleShape)
                    .border(shape = CircleShape, width = 2.dp, color = TailWind.Blue_500),

                painter = painterResource(R.drawable.avatar),
                contentDescription = "Profile Avatar Background",
            )
            Text(
                text = "User Name",
                fontSize = 24.sp,
                fontFamily = Quicksand_Bold
            )
        }
    }
}