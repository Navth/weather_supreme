package org.breezyweather.sources.openweather

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.openweather.json.OpenWeatherAirPollutionResult
import org.breezyweather.sources.openweather.json.OpenWeatherForecast
import org.breezyweather.sources.openweather.json.OpenWeatherForecastResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * OpenWeather API.
 */
interface OpenWeatherApi {

    @GET("data/2.5/forecast")
    fun getForecast(
        @Query("appid") apikey: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("lang") lang: String,
    ): Observable<OpenWeatherForecastResult>

    @GET("data/2.5/weather")
    fun getCurrent(
        @Query("appid") apikey: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("lang") lang: String,
    ): Observable<OpenWeatherForecast>

    @GET("data/2.5/air_pollution/forecast")
    fun getAirPollution(
        @Query("appid") apikey: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<OpenWeatherAirPollutionResult>
}
