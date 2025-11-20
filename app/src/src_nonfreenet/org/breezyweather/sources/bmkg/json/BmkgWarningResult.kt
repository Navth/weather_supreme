package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgWarningResult(
    val data: BmkgWarningData? = null,
)
