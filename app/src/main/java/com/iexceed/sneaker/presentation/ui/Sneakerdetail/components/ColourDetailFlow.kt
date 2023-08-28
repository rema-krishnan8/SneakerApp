package com.iexceed.bitcoinmca.presentation.ui.coindetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.iexceed.sneaker.ui.theme.*

@Composable
fun ColourDetailFlow(
    tag: String
) {
    var color: Color = colorprimarywhite
    when (tag) {
        "red" -> color = Teal200
        "beige" -> color = colorprimarywhite
        "blue" -> color = blue
    }
    Box(
        modifier = Modifier.border(
            width = 2.dp,
            shape = RoundedCornerShape(24.dp),
            color = color

        ).size(60.dp, 30.dp).background(color = color).clip(RoundedCornerShape(24.dp)),

        contentAlignment = Alignment.Center
    ) {
        if (color.equals(Teal200)) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "tick",
                tint = white
            )
        }
    }
}
