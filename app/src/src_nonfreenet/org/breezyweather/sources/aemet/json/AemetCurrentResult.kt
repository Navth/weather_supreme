package org.breezyweather.sources.aemet.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AemetCurrentResult(
    val vmax: Double? = null,
    val vv: Double? = null,
    val dv: Double? = null,
    val hr: Double? = null,
    @SerialName("pres_nmar") val pres: Double? = null,
    val ta: Double? = null,
    val tpr: Double? = null,
    val vis: Double? = null,
)
