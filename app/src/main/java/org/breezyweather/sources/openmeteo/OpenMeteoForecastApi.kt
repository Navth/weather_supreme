package org.breezyweather.sources.openmeteo

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.openmeteo.json.OpenMeteoWeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Open-Meteo API
 */
interface OpenMeteoForecastApi {
    @GET("v1/forecast?timezone=auto&timeformat=unixtime")
    fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("models") models: String = OpenMeteoWeatherModel.BEST_MATCH.id,
        @Query("daily") daily: String,
        @Query("hourly") hourly: String,
        @Query("minutely_15") minutely15: String,
        @Query("current") current: String,
        @Query("forecast_days") forecastDays: Int,
        @Query("past_days") pastDays: Int,
        @Query("windspeed_unit") windspeedUnit: String,
    ): Observable<OpenMeteoWeatherResult>
}
