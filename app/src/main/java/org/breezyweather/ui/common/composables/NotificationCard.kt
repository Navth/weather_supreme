package org.breezyweather.ui.common.composables

import androidx.compose.runtime.Composable
import org.breezyweather.R
import org.breezyweather.ui.settings.preference.composables.PreferenceViewWithCard

@Composable
fun NotificationCard(
    title: String,
    summary: String,
    onClick: () -> Unit,
    onClose: () -> Unit,
) {
    PreferenceViewWithCard(
        iconId = R.drawable.ic_notifications,
        onClick = onClick,
        title = title,
        summary = summary,
        isFirst = true,
        isLast = true,
        onClose = onClose
    )
}
