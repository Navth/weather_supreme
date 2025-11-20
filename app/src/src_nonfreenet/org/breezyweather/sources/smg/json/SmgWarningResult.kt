package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgWarningResult(
    val TyphoonWarning: SmgWarningRoot? = null,
    val RainstormWarning: SmgWarningRoot? = null,
    val MonsoonWarning: SmgWarningRoot? = null,
    val ThunderstormWarning: SmgWarningRoot? = null,
    val StormsurgeWarning: SmgWarningRoot? = null,
    val TsunamiWarning: SmgWarningRoot? = null,
)
