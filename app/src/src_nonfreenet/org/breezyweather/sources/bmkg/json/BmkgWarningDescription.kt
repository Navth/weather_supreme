package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BmkgWarningDescription(
    @SerialName("ID_Kode") val idKode: String,
    @SerialName("date_start") val dateStart: String?,
    val expired: String?,
    val headline: String?,
    val description: String?,
)
