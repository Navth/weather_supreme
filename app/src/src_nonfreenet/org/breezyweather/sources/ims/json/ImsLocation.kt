package org.breezyweather.sources.ims.json

import kotlinx.serialization.Serializable

@Serializable
data class ImsLocation(
    val lid: String,
    val name: String,
    val lat: String,
    val lon: String,
)
