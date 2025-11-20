package org.breezyweather.sources.geosphereat

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.geosphereat.json.GeoSphereAtWarningsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoSphereAtWarningApi {

    @GET("getWarningsForCoords")
    fun getWarningsForCoords(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("lang") lang: String, // "en" or "de"
    ): Observable<GeoSphereAtWarningsResult>
}
