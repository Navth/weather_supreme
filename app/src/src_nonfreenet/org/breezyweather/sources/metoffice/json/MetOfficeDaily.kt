package org.breezyweather.sources.metoffice.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class MetOfficeDaily(
    @Serializable(with = DateSerializer::class)
    val time: Date,
    val maxUvIndex: Int?,
    val daySignificantWeatherCode: Int?,
    val nightSignificantWeatherCode: Int?,
    val dayMaxScreenTemperature: Double?,
    val nightMinScreenTemperature: Double?,
    val dayUpperBoundMaxTemp: Double?,
    val nightUpperBoundMinTemp: Double?,
    val dayLowerBoundMaxTemp: Double?,
    val nightLowerBoundMinTemp: Double?,
    val dayMaxFeelsLikeTemp: Double?,
    val nightMinFeelsLikeTemp: Double?,
    val dayUpperBoundMaxFeelsLikeTemp: Double?,
    val nightUpperBoundMinFeelsLikeTemp: Double?,
    val dayLowerBoundMaxFeelsLikeTemp: Double?,
    val nightLowerBoundMinFeelsLikeTemp: Double?,
    val dayProbabilityOfPrecipitation: Int?,
    val nightProbabilityOfPrecipitation: Int?,
    val dayProbabilityOfSnow: Int?,
    val nightProbabilityOfSnow: Int?,
    val dayProbabilityOfHeavySnow: Int?,
    val nightProbabilityOfHeavySnow: Int?,
    val dayProbabilityOfRain: Int?,
    val nightProbabilityOfRain: Int?,
    val dayProbabilityOfHeavyRain: Int?,
    val nightProbabilityOfHeavyRain: Int?,
    val dayProbabilityOfHail: Int?,
    val nightProbabilityOfHail: Int?,
    val dayProbabilityOfSferics: Int?,
    val nightProbabilityOfSferics: Int?,
)
