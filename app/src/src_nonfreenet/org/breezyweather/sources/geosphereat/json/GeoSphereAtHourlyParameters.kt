package org.breezyweather.sources.geosphereat.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoSphereAtHourlyParameters(
    // Hourly
    val sy: GeoSphereAtHourlyDoubleParameter?, // Always integer but formatted as a double
    val t2m: GeoSphereAtHourlyDoubleParameter?,
    @SerialName("rr_acc") val rrAcc: GeoSphereAtHourlyDoubleParameter?, // kg mÂ²
    @SerialName("rain_acc") val rainAcc: GeoSphereAtHourlyDoubleParameter?, // kg mÂ²
    @SerialName("snow_acc") val snowAcc: GeoSphereAtHourlyDoubleParameter?, // kg mÂ²
    val u10m: GeoSphereAtHourlyDoubleParameter?, // m/s
    val ugust: GeoSphereAtHourlyDoubleParameter?, // m/s
    val v10m: GeoSphereAtHourlyDoubleParameter?, // m/s
    val vgust: GeoSphereAtHourlyDoubleParameter?, // m/s
    val rh2m: GeoSphereAtHourlyDoubleParameter?, // %
    val tcc: GeoSphereAtHourlyDoubleParameter?, // to be multiplied by 100
    val sp: GeoSphereAtHourlyDoubleParameter?, // Pa

    // Nowcast
    val rr: GeoSphereAtHourlyDoubleParameter?, // kg mÂ²

    // Air quality
    val pm25surf: GeoSphereAtHourlyDoubleParameter?, // ug m-3
    val pm10surf: GeoSphereAtHourlyDoubleParameter?, // ug m-3
    val no2surf: GeoSphereAtHourlyDoubleParameter?, // ug m-3
    val o3surf: GeoSphereAtHourlyDoubleParameter?, // ug m-3
)
