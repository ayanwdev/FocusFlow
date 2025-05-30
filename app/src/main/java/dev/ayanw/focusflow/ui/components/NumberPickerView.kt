package dev.ayanw.focusflow.ui.components

import android.widget.NumberPicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.viewinterop.AndroidView
import java.util.Locale

@Composable
fun NumberPickerView(
    value: Int = 0,
    range: IntRange = 0..59,
    onValueChange: (Int) -> Unit = {}
) {
    AndroidView(
        modifier = Modifier.scale(1.07f),
        factory = { context ->
            NumberPicker(context).apply {
                minValue = range.first
                maxValue = range.last
                this.value = value
                setOnValueChangedListener { _, _, newVal ->
                    onValueChange(newVal)
                }
                wrapSelectorWheel = true
                setFormatter { value ->
                    String.format(locale = Locale.getDefault(), format = "%02d", value)
                }
            }
        },
        update = {
            it.value = value
        },
    )
}