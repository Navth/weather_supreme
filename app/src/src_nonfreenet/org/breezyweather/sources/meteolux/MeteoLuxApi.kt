package org.breezyweather.sources.meteolux

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.meteolux.json.MeteoLuxWeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MeteoLuxApi {
    @GET("api/v1/metapp/weather")
    fun getWeather(
        @Query("langcode") language: String = "en",
        @Query("lat") lat: Double,
        @Query("long") lon: Double,
    ): Observable<MeteoLuxWeatherResult>
}
