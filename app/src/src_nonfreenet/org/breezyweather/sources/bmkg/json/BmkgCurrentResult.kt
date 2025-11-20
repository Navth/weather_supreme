package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgCurrentResult(
    val data: BmkgCurrentData? = null,
)
