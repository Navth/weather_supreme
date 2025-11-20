package org.breezyweather.common.source

import breezyweather.domain.weather.model.Weather

class WeatherResult(
    val weather: Weather? = null,
    val errors: List<RefreshError> = emptyList(),
)
