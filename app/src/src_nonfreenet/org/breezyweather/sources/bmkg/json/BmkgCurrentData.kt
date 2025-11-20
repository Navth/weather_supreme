package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgCurrentData(
    val cuaca: BmkgCuaca?,
)
