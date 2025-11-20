package org.breezyweather.sources.mf.json

import kotlinx.serialization.Serializable

@Serializable
data class MfWarningDictionaryColor(
    val id: Int?,
    val name: String?,
    val hexaCode: String?,
)
