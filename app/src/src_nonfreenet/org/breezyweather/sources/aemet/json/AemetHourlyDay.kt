package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetHourlyDay(
    val fecha: String,
    val orto: String?,
    val ocaso: String?,
    val estadoCielo: List<AemetForecastData>?,
    val precipitacion: List<AemetForecastData>?,
    val probPrecipitacion: List<AemetForecastData>?,
    val probTormenta: List<AemetForecastData>?,
    val nieve: List<AemetForecastData>?,
    val probNieve: List<AemetForecastData>?,
    val temperatura: List<AemetForecastData>?,
    val sensTermica: List<AemetForecastData>?,
    val humedadRelativa: List<AemetForecastData>?,
    val vientoAndRachaMax: List<AemetForecastData>?,
)
