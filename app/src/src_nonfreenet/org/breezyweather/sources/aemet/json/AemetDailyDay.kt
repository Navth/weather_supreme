package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetDailyDay(
    val fecha: String,
    val probPrecipitacion: List<AemetDailyData>?,
    val estadoCielo: List<AemetForecastData>?,
    val viento: List<AemetDailyData>?,
    val rachaMax: List<AemetForecastData>?,
    val temperatura: AemetDailyData?,
    val sensTermica: AemetDailyData?,
    val humedadRelativa: AemetDailyData?,
    val uvMax: Double?,
)
