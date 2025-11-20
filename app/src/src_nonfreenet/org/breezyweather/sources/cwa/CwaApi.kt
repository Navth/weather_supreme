package org.breezyweather.sources.cwa

import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import org.breezyweather.sources.cwa.json.CwaAirQualityResult
import org.breezyweather.sources.cwa.json.CwaAlertResult
import org.breezyweather.sources.cwa.json.CwaAssistantResult
import org.breezyweather.sources.cwa.json.CwaCurrentResult
import org.breezyweather.sources.cwa.json.CwaForecastResult
import org.breezyweather.sources.cwa.json.CwaLocationResult
import org.breezyweather.sources.cwa.json.CwaNormalsResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CwaApi {

    @GET("api/v1/rest/datastore/W-C0033-002")
    fun getAlerts(
        @Query("Authorization") apiKey: String,
        @Query("format") format: String = "json",
    ): Observable<CwaAlertResult>

    @POST("linked/graphql")
    fun getLocation(
        @Query("Authorization") apiKey: String,
        @Body body: RequestBody,
    ): Observable<CwaLocationResult>

    @GET("api/v1/rest/datastore/C-B0027-001")
    fun getNormals(
        @Query("Authorization") apiKey: String,
        @Query("format") format: String = "json",
        @Query("StationID") stationId: String,
        @Query("weatherElement") weatherElement: String = "AirTemperature",
        @Query("Month") month: String,
    ): Observable<CwaNormalsResult>

    @GET("api/v1/rest/datastore/O-A0001-001")
    fun getCurrent(
        @Query("Authorization") apiKey: String,
        @Query("StationId") stationId: String,
    ): Observable<CwaCurrentResult>

    @GET("fileapi/v1/opendataapi/{endpoint}")
    fun getAssistant(
        @Path("endpoint") endpoint: String,
        @Query("Authorization") apiKey: String,
        @Query("downloadType") downloadType: String = "WEB",
        @Query("format") format: String = "JSON",
    ): Observable<CwaAssistantResult>

    @POST("linked/graphql")
    fun getAirQuality(
        @Query("Authorization") apiKey: String,
        @Body body: RequestBody,
    ): Observable<CwaAirQualityResult>

    @GET("api/v1/rest/datastore/F-D0047-093")
    fun getForecast(
        @Query("Authorization") apiKey: String,
        @Query("locationId") endpoint: String,
        @Query("locationName") townshipName: String,
    ): Observable<CwaForecastResult>
}
