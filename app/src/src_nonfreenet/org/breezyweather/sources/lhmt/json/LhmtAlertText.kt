package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.Serializable

@Serializable
data class LhmtAlertText(
    val en: String?,
    val lt: String?,
)
