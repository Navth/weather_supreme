package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccAlertSpecialText(
    val type: String?,
    val link: String?,
)
