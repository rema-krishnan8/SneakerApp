package com.iexceed.bitcoinmca.presentation.ui.coindetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iexceed.sneaker.ui.theme.colorprimarywhite
import com.iexceed.sneaker.ui.theme.white

@Composable
fun SizeDetailFlow(
    tag: String
) {
    Box(
        modifier = Modifier.border(
            width = 2.dp,
            shape = RoundedCornerShape(10.dp),
            color = colorprimarywhite
        ).size(60.dp, 30.dp).background(color = if (tag == "7") colorprimarywhite else white).clip(RoundedCornerShape(10.dp)),

        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tag,
            color = if (tag == "7") white else colorprimarywhite,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )
    }
}
