package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgIbfResult(
    val data: List<BmkgIbfData>? = null,
)
