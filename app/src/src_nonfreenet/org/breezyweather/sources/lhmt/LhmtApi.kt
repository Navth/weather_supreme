package org.breezyweather.sources.lhmt

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.lhmt.json.LhmtLocationsResult
import org.breezyweather.sources.lhmt.json.LhmtWeatherResult
import retrofit2.http.GET
import retrofit2.http.Path

interface LhmtApi {
    @GET("v1/places")
    fun getForecastLocations(): Observable<List<LhmtLocationsResult>>

    @GET("v1/stations")
    fun getCurrentLocations(): Observable<List<LhmtLocationsResult>>

    @GET("v1/places/{code}/forecasts/long-term")
    fun getForecast(
        @Path("code") code: String,
    ): Observable<LhmtWeatherResult>

    @GET("v1/stations/{code}/observations/latest")
    fun getCurrent(
        @Path("code") code: String,
    ): Observable<LhmtWeatherResult>
}
