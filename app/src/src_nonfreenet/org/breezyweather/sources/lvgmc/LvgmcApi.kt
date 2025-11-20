package org.breezyweather.sources.lvgmc

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.lvgmc.json.LvgmcAirQualityLocationResult
import org.breezyweather.sources.lvgmc.json.LvgmcAirQualityResult
import org.breezyweather.sources.lvgmc.json.LvgmcCurrentLocation
import org.breezyweather.sources.lvgmc.json.LvgmcCurrentResult
import org.breezyweather.sources.lvgmc.json.LvgmcForecastResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LvgmcApi {
    @GET("data/weather_points_forecast")
    fun getForecastLocations(
        @Query("laiks") laiks: String,
        @Query("bounds") bounds: String,
    ): Observable<List<LvgmcForecastResult>>

    @GET("data/weather_monitoring_points")
    fun getCurrentLocations(): Observable<List<LvgmcCurrentLocation>>

    @GET("data/na_atmosfera_stacijas")
    fun getAirQualityLocations(): Observable<List<LvgmcAirQualityLocationResult>>

    @GET("data/weather_forecast_for_location_{scope}")
    fun getForecast(
        @Path("scope") scope: String,
        @Query("punkts") punkts: String,
    ): Observable<List<LvgmcForecastResult>>

    @GET("data/weather_monitoring_data")
    fun getCurrent(): Observable<List<LvgmcCurrentResult>>

    @GET("data/gaisa_kvalitate_envista_batch")
    fun getAirQuality(
        @Query("stacija_id") station: String,
        @Query("no_datums") fromDate: String,
    ): Observable<List<LvgmcAirQualityResult>>
}
