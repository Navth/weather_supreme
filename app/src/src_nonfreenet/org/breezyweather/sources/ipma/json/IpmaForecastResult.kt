package org.breezyweather.sources.ipma.json

import kotlinx.serialization.Serializable

@Serializable
data class IpmaForecastResult(
    val dataPrev: String,
    val idPeriodo: Int,
    val tMed: String?,
    val tMin: String?,
    val tMax: String?,
    val iUv: String?,
    val idTipoTempo: Int?,
    val probabilidadePrecipita: String?,
    val hR: String?,
    val utci: String?,
    val ffVento: String?,
    val ddVento: String?,
)
