package org.breezyweather.sources.atmo

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.atmo.json.AtmoPointResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * API
 */
interface AtmoApi {

    @GET("point?with_list=true")
    fun getPointDetails(
        @Header("api_token") headerApiToken: String?,
        @Header("User-Agent") userAgent: String = "okhttp/3.14.9",
        @Query("api_token") queryApiToken: String?,
        @Query("x") longitude: Double,
        @Query("y") latitude: Double,
        @Query("datetime_echeance") datetimeEcheance: String,
    ): Observable<AtmoPointResult>
}
