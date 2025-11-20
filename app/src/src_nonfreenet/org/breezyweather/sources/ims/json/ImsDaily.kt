package org.breezyweather.sources.ims.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImsDaily(
    @SerialName("maximum_uvi") val maximumUVI: String?,
)
