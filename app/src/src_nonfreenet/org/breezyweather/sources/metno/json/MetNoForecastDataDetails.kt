package org.breezyweather.sources.metno.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetNoForecastDataDetails(
    @SerialName("air_pressure_at_sea_level") val airPressureAtSeaLevel: Double?,
    @SerialName("air_temperature") val airTemperature: Double?,
    @SerialName("dew_point_temperature") val dewPointTemperature: Double?,
    @SerialName("precipitation_rate") val precipitationRate: Double?,
    @SerialName("precipitation_amount") val precipitationAmount: Double?,
    @SerialName("probability_of_precipitation") val probabilityOfPrecipitation: Double?,
    @SerialName("probability_of_thunder") val probabilityOfThunder: Double?,
    @SerialName("relative_humidity") val relativeHumidity: Double?,
    @SerialName("ultraviolet_index_clear_sky") val ultravioletIndexClearSky: Double?,
    @SerialName("wind_from_direction") val windFromDirection: Double?,
    @SerialName("wind_speed") val windSpeed: Double?,
    @SerialName("cloud_area_fraction") val cloudAreaFraction: Double?,
)
