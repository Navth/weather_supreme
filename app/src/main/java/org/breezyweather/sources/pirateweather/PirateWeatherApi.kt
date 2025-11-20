package org.breezyweather.sources.pirateweather

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.pirateweather.json.PirateWeatherForecastResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * See https://docs.pirateweather.net/en/latest/Specification/
 */
interface PirateWeatherApi {
    @GET("forecast/{apikey}/{lat},{lon}")
    fun getForecast(
        @Path("apikey") apikey: String,
        @Path("lat") lat: Double,
        @Path("lon") lon: Double,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("exclude") exclude: String?,
        @Query("extend") extend: String?,
    ): Observable<PirateWeatherForecastResult>
}
