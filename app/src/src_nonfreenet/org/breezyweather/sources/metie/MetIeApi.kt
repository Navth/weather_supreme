package org.breezyweather.sources.metie

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.metie.json.MetIeForecastResult
import org.breezyweather.sources.metie.json.MetIeLocationResult
import org.breezyweather.sources.metie.json.MetIeWarningResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MetIeApi {
    @GET("v4/meteogram")
    fun getForecast(
        @Header("token") token: String,
        @Query("lat") lat: Double,
        @Query("lng") lon: Double,
        @Query("date") date: String,
        @Query("src") src: String,
        @Query("version") version: String,
        @Query("env") env: String,
    ): Observable<MetIeForecastResult>

    @GET("v3/warnings")
    fun getWarnings(
        @Header("token") token: String,
        @Query("src") src: String,
        @Query("version") version: String,
        @Query("env") env: String,
    ): Observable<MetIeWarningResult>

    @GET("location/reverse/{lat}/{lon}")
    fun getReverseLocation(
        @Header("token") token: String,
        @Path("lat") lat: Double,
        @Path("lon") lon: Double,
        @Query("src") src: String,
        @Query("version") version: String,
        @Query("env") env: String,
    ): Observable<MetIeLocationResult>
}
