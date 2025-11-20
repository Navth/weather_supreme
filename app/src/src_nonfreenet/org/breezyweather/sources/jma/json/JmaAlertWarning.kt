package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaAlertWarning(
    val code: String?,
    val status: String?,
)
