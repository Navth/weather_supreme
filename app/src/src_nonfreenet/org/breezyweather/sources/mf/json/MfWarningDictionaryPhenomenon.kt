package org.breezyweather.sources.mf.json

import kotlinx.serialization.Serializable

@Serializable
data class MfWarningDictionaryPhenomenon(
    val id: Int?,
    val name: String?,
)
