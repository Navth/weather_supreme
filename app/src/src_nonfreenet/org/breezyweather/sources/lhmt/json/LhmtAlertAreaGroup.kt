package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LhmtAlertAreaGroup(
    val areas: List<LhmtAlertArea>?,
    @SerialName("single_alerts") val singleAlerts: List<LhmtAlertSingleAlert>?,
)
