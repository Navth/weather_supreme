package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LhmtAlertsResult(
    @SerialName("phenomenon_groups") val phenomenonGroups: List<LhmtAlertPhenomenonGroup>? = null,
)
