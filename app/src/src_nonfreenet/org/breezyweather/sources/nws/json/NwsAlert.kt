package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsAlert(
    val properties: NwsAlertProperties?,
)
