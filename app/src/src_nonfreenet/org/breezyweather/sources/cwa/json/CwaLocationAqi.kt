package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaLocationAqi(
    val station: CwaLocationStation?,
    val town: CwaLocationTown?,
)
