package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgAirQuality(
    val HE_PM10: String?,
    val HE_PM2_5: String?,
    val HE_NO2: String?,
    val HE_O3: String?,
    val HE_SO2: String?,
    val HE_CO: String?,
)
