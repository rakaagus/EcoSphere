package com.neirasphere.ecosphere.presentation.screen.classification.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.neirasphere.ecosphere.utils.shimmerEffect


@Composable
fun ShimmerImage(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.small)
                .shimmerEffect()
        )
    }else {
        content()
    }
}