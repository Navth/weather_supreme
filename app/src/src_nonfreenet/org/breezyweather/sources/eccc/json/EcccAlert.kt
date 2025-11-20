package org.breezyweather.sources.eccc.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class EcccAlert(
    val alertId: String?,
    val type: String?,
    @Serializable(DateSerializer::class) val issueTime: Date?,
    @Serializable(DateSerializer::class) val expiryTime: Date?,
    val alertBannerText: String?,
    val bannerColour: String?,
    val text: String?,
    @SerialName("special_text") val specialText: List<EcccAlertSpecialText>?,
)
