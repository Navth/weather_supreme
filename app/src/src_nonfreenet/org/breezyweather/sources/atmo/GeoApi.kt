package org.breezyweather.sources.atmo

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.atmo.json.GeoResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API
 */
interface GeoApi {

    @GET("reverse")
    fun getReverseAddress(
        @Query("lon") longitude: Double,
        @Query("lat") latitude: Double,
    ): Observable<GeoResult>
}
