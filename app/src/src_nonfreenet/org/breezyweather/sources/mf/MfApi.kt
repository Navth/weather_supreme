package org.breezyweather.sources.mf

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.mf.json.MfCurrentResult
import org.breezyweather.sources.mf.json.MfForecastResult
import org.breezyweather.sources.mf.json.MfNormalsResult
import org.breezyweather.sources.mf.json.MfRainResult
import org.breezyweather.sources.mf.json.MfWarningDictionaryResult
import org.breezyweather.sources.mf.json.MfWarningsOverseasResult
import org.breezyweather.sources.mf.json.MfWarningsResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * API MÃ©tÃ©o France
 */
interface MfApi {

    @GET("v2/forecast")
    fun getForecast(
        @Header("User-Agent") userAgent: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("formatDate") formatDate: String,
        @Query("token") token: String,
    ): Observable<MfForecastResult>

    @GET("v2/observation")
    fun getCurrent(
        @Header("User-Agent") userAgent: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String,
        @Query("formatDate") formatDate: String,
        @Query("token") token: String,
    ): Observable<MfCurrentResult>

    @GET("v3/nowcast/rain")
    fun getRain(
        @Header("User-Agent") userAgent: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String,
        @Query("formatDate") formatDate: String,
        @Query("token") token: String,
    ): Observable<MfRainResult>

    @GET("v2/normals")
    fun getNormals(
        @Header("User-Agent") userAgent: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("token") token: String,
    ): Observable<MfNormalsResult>

    @GET("v3/warning/full")
    fun getWarnings(
        @Header("User-Agent") userAgent: String,
        @Query(encoded = true, value = "domain") domain: String,
        @Query("echeance") echeance: String,
        @Query("formatDate") formatDate: String,
        @Query("token") token: String,
    ): Observable<MfWarningsResult>

    @GET("v2/warning/dictionary")
    fun getOverseasWarningsDictionary(
        @Header("User-Agent") userAgent: String,
        @Query(encoded = true, value = "domain") domain: String,
        @Query("token") token: String,
    ): Observable<MfWarningDictionaryResult>

    @GET("v2/warning/full")
    fun getOverseasWarnings(
        @Header("User-Agent") userAgent: String,
        @Query(encoded = true, value = "domain") domain: String,
        @Query("warning_type") warningType: String?, // vigilance4colors needed for VIGI974 / La RÃ©union
        @Query("formatDate") formatDate: String,
        @Query("token") token: String,
    ): Observable<MfWarningsOverseasResult>
}
