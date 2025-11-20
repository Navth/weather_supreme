package org.breezyweather.sources.mgm.json

import kotlinx.serialization.Serializable

@Serializable
data class MgmAlertTowns(
    val yellow: List<Int>?,
    val orange: List<Int>?,
    val red: List<Int>?,
)
