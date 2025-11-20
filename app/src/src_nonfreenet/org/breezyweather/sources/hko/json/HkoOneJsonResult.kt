package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoOneJsonResult(
    val FLW: HkoOneJsonFlw? = null,
    val F9D: HkoOneJsonF9d? = null,
    val RHRREAD: HkoOneJsonRhrread? = null,
)
