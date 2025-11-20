package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.Serializable

@Serializable
data class LhmtAlertResponseType(
    val none: Boolean?,
)
