package org.breezyweather.sources.ipma

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.ipma.json.IpmaAlertResult
import org.breezyweather.sources.ipma.json.IpmaDistrictResult
import org.breezyweather.sources.ipma.json.IpmaForecastResult
import org.breezyweather.sources.ipma.json.IpmaLocationResult
import retrofit2.http.GET
import retrofit2.http.Path

interface IpmaApi {
    @GET("public-data/districts.json")
    fun getDistricts(): Observable<List<IpmaDistrictResult>>

    @GET("public-data/forecast/locations.json")
    fun getLocations(): Observable<List<IpmaLocationResult>>

    @GET("public-data/forecast/aggregate/{globalIdLocal}.json")
    fun getForecast(
        @Path("globalIdLocal") globalIdLocal: String,
    ): Observable<List<IpmaForecastResult>>

    @GET("public-data/warnings/warnings_www.json")
    fun getAlerts(): Observable<IpmaAlertResult>
}
