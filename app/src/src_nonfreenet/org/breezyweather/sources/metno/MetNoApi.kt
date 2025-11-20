package org.breezyweather.sources.metno

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.metno.json.MetNoAirQualityResult
import org.breezyweather.sources.metno.json.MetNoAlertResult
import org.breezyweather.sources.metno.json.MetNoForecastResult
import org.breezyweather.sources.metno.json.MetNoNowcastResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * MET Norway Weather API.
 */
interface MetNoApi {
    @GET("locationforecast/2.0/complete.json")
    fun getForecast(
        @Header("User-Agent") userAgent: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<MetNoForecastResult>

    // Only available in Nordic area
    @GET("nowcast/2.0/complete.json")
    fun getNowcast(
        @Header("User-Agent") userAgent: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<MetNoNowcastResult>

    @GET("airqualityforecast/0.1/")
    fun getAirQuality(
        @Header("User-Agent") userAgent: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<MetNoAirQualityResult>

    @GET("metalerts/2.0/current.json")
    fun getAlerts(
        @Header("User-Agent") userAgent: String,
        @Query("lang") lang: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<MetNoAlertResult>
}
