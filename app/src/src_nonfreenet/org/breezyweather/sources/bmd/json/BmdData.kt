package org.breezyweather.sources.bmd.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BmdData(
    @SerialName("upazila_name") val upazilaName: String?,
    @SerialName("district_name") val districtName: String?,
    @SerialName("division_name") val divisionName: String?,
    @SerialName("forecast_data") val forecastData: BmdForecastData?,
)
