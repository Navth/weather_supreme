package org.breezyweather.sources.ipma.json

import kotlinx.serialization.Serializable

@Serializable
data class IpmaAlertResult(
    val data: List<IpmaAlert>? = null,
)
