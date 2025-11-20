package org.breezyweather.sources.bmkg

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.bmkg.json.BmkgCurrentResult
import org.breezyweather.sources.bmkg.json.BmkgForecastResult
import org.breezyweather.sources.bmkg.json.BmkgIbfResult
import org.breezyweather.sources.bmkg.json.BmkgLocationResult
import org.breezyweather.sources.bmkg.json.BmkgWarningResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BmkgApi {
    @GET("api/df/v1/adm/coord")
    fun getLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<BmkgLocationResult>

    @GET("api/presentwx/coord")
    fun getCurrent(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<BmkgCurrentResult>

    @GET("api/df/v1/forecast/coord")
    fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): Observable<BmkgForecastResult>

    @GET("api/v1/public/weather/warning")
    fun getWarning(
        @Header("X-API-KEY") apiKey: String,
        @Query("lat") lat: Double,
        @Query("long") lon: Double,
    ): Observable<BmkgWarningResult>

    @GET("api/v1/public/weather/warning/ibf")
    fun getIbf(
        @Header("X-API-KEY") apiKey: String,
        @Query("lat") lat: Double,
        @Query("long") lon: Double,
        @Query("day") day: Int,
    ): Observable<BmkgIbfResult>
}
