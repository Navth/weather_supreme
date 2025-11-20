package org.breezyweather.sources.mf.json

import kotlinx.serialization.Serializable

/**
 * Mf dictionary necessary for Outre-Mer
 */
@Serializable
data class MfWarningDictionaryResult(
    val phenomenons: List<MfWarningDictionaryPhenomenon>? = null,
    val colors: List<MfWarningDictionaryColor>? = null,
)
