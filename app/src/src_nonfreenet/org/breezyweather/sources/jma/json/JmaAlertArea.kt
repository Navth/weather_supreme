package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaAlertArea(
    val code: String?,
    val warnings: List<JmaAlertWarning>?,
)
