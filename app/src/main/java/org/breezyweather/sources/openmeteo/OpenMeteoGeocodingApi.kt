package org.breezyweather.sources.openmeteo

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.openmeteo.json.OpenMeteoLocationResults
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Open-Meteo API
 */
interface OpenMeteoGeocodingApi {
    @GET("v1/search?format=json")
    fun getLocations(
        @Query("name") name: String,
        @Query("count") count: Int,
        @Query("language") language: String,
    ): Observable<OpenMeteoLocationResults>
}
