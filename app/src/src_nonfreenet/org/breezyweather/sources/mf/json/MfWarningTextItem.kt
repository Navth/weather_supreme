package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfWarningTextItem(
    @SerialName("hazard_code") val hazardCode: String?,
    @SerialName("term_items") val termItems: List<MfWarningTermItem>?,
)
