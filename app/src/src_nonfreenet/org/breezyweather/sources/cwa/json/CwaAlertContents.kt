package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertContents(
    val content: CwaAlertContent?,
)
