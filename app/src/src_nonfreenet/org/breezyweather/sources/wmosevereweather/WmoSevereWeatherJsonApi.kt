package org.breezyweather.sources.wmosevereweather

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.wmosevereweather.json.WmoSevereWeatherAlertResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API World Meteorological Organization
 */
interface WmoSevereWeatherJsonApi {
    @GET("f/wfs")
    fun getAlerts(
        @Query("request") request: String = "GetFeature",
        @Query("version") version: String = "1.1.0",
        @Query("typeName", encoded = true) typeName: String,
        @Query("cql_filter", encoded = true) cqlFilter: String,
        @Query("outputFormat") outputFormat: String = "json",
    ): Observable<WmoSevereWeatherAlertResult>
}
