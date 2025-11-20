package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoWarningSummaryResult(
    val DYN_DAT_WARNSUM: Map<String, HkoWarningSummary>?,
)
