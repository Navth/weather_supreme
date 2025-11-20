package org.breezyweather.sources.brightsky.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class BrightSkyAlert(
    val id: Int?,
    @Serializable(DateSerializer::class) val onset: Date?,
    @Serializable(DateSerializer::class) val expires: Date?,
    val severity: String?,
    @SerialName("headline_en") val headlineEn: String?,
    @SerialName("headline_de") val headlineDe: String?,
    @SerialName("description_en") val descriptionEn: String?,
    @SerialName("description_de") val descriptionDe: String?,
    @SerialName("instruction_en") val instructionEn: String?,
    @SerialName("instruction_de") val instructionDe: String?,
)
