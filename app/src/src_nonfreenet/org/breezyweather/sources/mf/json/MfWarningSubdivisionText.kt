package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfWarningSubdivisionText(
    @SerialName("underline_text") val underlineText: String?,
    @SerialName("bold_text") val boldText: String?,
    val text: List<String>?,
)
