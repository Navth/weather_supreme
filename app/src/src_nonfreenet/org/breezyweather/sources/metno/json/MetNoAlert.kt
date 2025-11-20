package org.breezyweather.sources.metno.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * MET Norway single alert
 */
@Serializable
data class MetNoAlert(
    val properties: MetNoAlertProperties?,
    @SerialName("when") val whenAlert: MetNoAlertWhen?,
)
