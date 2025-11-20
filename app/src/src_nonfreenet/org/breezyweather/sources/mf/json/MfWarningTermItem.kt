package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfWarningTermItem(
    @SerialName("risk_name") val riskName: String?,
    @SerialName("subdivision_text") val subdivisionTexts: List<MfWarningSubdivisionText>?,
)
