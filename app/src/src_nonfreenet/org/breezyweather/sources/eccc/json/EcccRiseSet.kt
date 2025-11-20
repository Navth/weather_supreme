package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccRiseSet(
    val set: EcccEpochTime?,
    val rise: EcccEpochTime?,
    val sunStatus: String?, // Can be "alwaysUp" // TODO: Must check if "alwaysDown" exists in winter
)
