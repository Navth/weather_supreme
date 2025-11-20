package org.breezyweather.sources.atmo

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.atmo.json.AtmoFrancePollenResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * API
 */
interface AtmoFranceApi {

    @GET(
        "data/{api_code}/{\"code_zone\":{\"operator\":\"=\",\"value\":\"{code_insee}\"},\"date_ech\":{\"operator\":\"=\",\"value\":\"{date_ech}\"}}"
    )
    fun getPollen(
        @Header("Api-token") apiToken: String,
        @Header("User-Agent") userAgent: String = "okhttp/3.14.9",
        @Path("api_code") apiCode: Int,
        @Path("code_insee") codeInsee: String,
        @Path("date_ech") dateEch: String,
    ): Observable<AtmoFrancePollenResult>
}
