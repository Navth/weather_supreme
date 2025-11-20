package org.breezyweather.ui.common.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun AnimatedVisibilitySlideVertically(
    visible: Boolean,
    label: String = "",
    expandFrom: Alignment.Vertical = Alignment.Top,
    shrinkTowards: Alignment.Vertical = Alignment.Top,
    content: @Composable (AnimatedVisibilityScope.() -> Unit),
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(expandFrom = expandFrom) + slideInVertically(),
        exit = slideOutVertically(targetOffsetY = { -it / 2 }) +
            shrinkVertically(shrinkTowards = shrinkTowards) +
            fadeOut(),
        label = label,
        content = content
    )
}
