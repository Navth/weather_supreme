package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaNormalsMonthly(
    val Month: String?,
    val Maximum: String?,
    val Minimum: String?,
)
