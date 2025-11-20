package org.breezyweather.sources.meteoam

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.meteoam.json.MeteoAmForecastResult
import org.breezyweather.sources.meteoam.json.MeteoAmObservationResult
import org.breezyweather.sources.meteoam.json.MeteoAmReverseLocationResult
import retrofit2.http.GET
import retrofit2.http.Path

interface MeteoAmApi {
    @GET("deda-meteograms/api/GetMeteogram/preset1/{lat},{lon}")
    fun getForecast(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double,
    ): Observable<MeteoAmForecastResult>

    @GET("deda-ows/api/GetStationRadius/{lat}/{lon}")
    fun getCurrent(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double,
    ): Observable<MeteoAmObservationResult>

    @GET("geocoder/r/{lon}/{lat}")
    fun getReverseLocation(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double,
    ): Observable<MeteoAmReverseLocationResult>
}
