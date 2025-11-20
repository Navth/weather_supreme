package org.breezyweather.sources.climweb

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.climweb.json.ClimWebAlertsResult
import org.breezyweather.sources.climweb.json.ClimWebLocation
import org.breezyweather.sources.climweb.json.ClimWebNormals
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * ClimWeb API
 */
interface ClimWebApi {
    @GET("api/cities")
    fun getLocationList(
        @Query("format") format: String = "json",
    ): Observable<List<ClimWebLocation>>

    @GET("api/cap/alerts.geojson")
    fun getAlerts(): Observable<ClimWebAlertsResult>

    @GET("api/cityclimate/data/{page_id}/")
    fun getNormals(
        @Path("page_id") pageId: String,
        @Query("city_id") cityId: String,
    ): Observable<List<ClimWebNormals>>
}
