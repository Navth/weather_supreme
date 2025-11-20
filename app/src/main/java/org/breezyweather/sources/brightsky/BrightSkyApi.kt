package org.breezyweather.sources.brightsky

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.brightsky.json.BrightSkyAlertsResult
import org.breezyweather.sources.brightsky.json.BrightSkyCurrentWeatherResult
import org.breezyweather.sources.brightsky.json.BrightSkyWeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface BrightSkyApi {
    @GET("weather")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("date") date: String,
        @Query("last_date") lastDate: String,
    ): Observable<BrightSkyWeatherResult>

    @GET("current_weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<BrightSkyCurrentWeatherResult>

    @GET("alerts")
    fun getAlerts(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<BrightSkyAlertsResult>
}
