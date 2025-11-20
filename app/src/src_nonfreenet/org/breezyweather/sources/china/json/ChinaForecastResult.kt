package org.breezyweather.sources.china.json

import kotlinx.serialization.Serializable

@Serializable
data class ChinaForecastResult(
    val current: ChinaCurrent? = null,
    val forecastDaily: ChinaForecastDaily? = null,
    val forecastHourly: ChinaForecastHourly? = null,
    val yesterday: ChinaYesterday? = null,
    val updateTime: Long? = null,
    val aqi: ChinaAqi? = null,
    val alerts: List<ChinaAlert>? = null,
)
