package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LhmtAlertPhenomenonGroup(
    @SerialName("area_groups") val areaGroups: List<LhmtAlertAreaGroup>?,
)
