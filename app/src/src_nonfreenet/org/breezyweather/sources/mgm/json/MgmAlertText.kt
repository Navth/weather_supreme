package org.breezyweather.sources.mgm.json

import kotlinx.serialization.Serializable

@Serializable
data class MgmAlertText(
    val yellow: String?,
    val orange: String?,
    val red: String?,
)
