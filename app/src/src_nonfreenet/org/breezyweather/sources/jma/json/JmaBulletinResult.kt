package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaBulletinResult(
    val text: String? = null,
)
