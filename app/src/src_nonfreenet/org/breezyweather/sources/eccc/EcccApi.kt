package org.breezyweather.sources.eccc

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.eccc.json.EcccResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface EcccApi {
    @GET("v3/{lang}/Location/{lat},{lon}")
    fun getForecast(
        @Header("User-Agent") userAgent: String,
        @Header("x-api-key") apiKey: String,
        @Path("lang") lang: String,
        @Path("lat") lat: Double,
        @Path("lon") lon: Double,
    ): Observable<List<EcccResult>>
}
