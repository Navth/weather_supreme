package org.breezyweather.sources.nominatim

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.nominatim.json.NominatimLocationResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Nominatim API
 */
interface NominatimApi {

    @GET("search")
    fun searchLocations(
        @Header("Accept-Language") acceptLanguage: String,
        @Header("User-Agent") userAgent: String,
        @Query("q") q: String,
        @Query("limit") limit: Int = 10,
        @Query("featureType") featureType: String = "city",
        @Query("format") format: String = "jsonv2",
        @Query("addressdetails") addressDetails: Boolean = true,
    ): Observable<List<NominatimLocationResult>>

    @GET("reverse")
    fun getReverseLocation(
        @Header("Accept-Language") acceptLanguage: String,
        @Header("User-Agent") userAgent: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("zoom") zoom: Int = 13,
        @Query("format") format: String = "jsonv2",
        @Query("addressdetails") addressDetails: Boolean = true,
    ): Observable<NominatimLocationResult>
}
