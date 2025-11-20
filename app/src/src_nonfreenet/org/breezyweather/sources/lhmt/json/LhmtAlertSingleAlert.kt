package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateUtcSerializer
import java.util.Date

@Serializable
data class LhmtAlertSingleAlert(
    val description: LhmtAlertText?,
    val headline: LhmtAlertText?,
    val instruction: LhmtAlertText?,
    val phenomenon: String?,
    @SerialName("response_type") val responseType: LhmtAlertResponseType?,
    val severity: String?,
    @SerialName("t_from") val tFrom:
    @Serializable(DateUtcSerializer::class)
    Date?,
    @SerialName("t_to") val tTo:
    @Serializable(DateUtcSerializer::class)
    Date?,
)
