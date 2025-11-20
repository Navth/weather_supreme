package org.breezyweather.sources.ipma.json

import kotlinx.serialization.Serializable

@Serializable
data class IpmaAlert(
    val text: String?,
    val awarenessTypeName: String?,
    val idAreaAviso: String?,
    val startTime: String,
    val awarenessLevelID: String?,
    val endTime: String,
)
