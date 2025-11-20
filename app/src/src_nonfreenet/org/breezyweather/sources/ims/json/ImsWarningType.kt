package org.breezyweather.sources.ims.json

import kotlinx.serialization.Serializable

@Serializable
data class ImsWarningType(
    val name: String?,
)
