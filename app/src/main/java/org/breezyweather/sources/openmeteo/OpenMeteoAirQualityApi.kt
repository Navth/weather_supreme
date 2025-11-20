package org.breezyweather.sources.openmeteo

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.openmeteo.json.OpenMeteoAirQualityResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Open-Meteo API
 */
interface OpenMeteoAirQualityApi {
    @GET("v1/air-quality?timezone=auto&timeformat=unixtime")
    fun getAirQuality(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String,
        @Query("forecast_days") forecastDays: Int,
        @Query("past_days") pastDays: Int,
    ): Observable<OpenMeteoAirQualityResult>
}
