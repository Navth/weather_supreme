package org.breezyweather.sources.recosante

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.recosante.json.GeoCommune
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API Geo
 */
interface GeoApi {
    @GET("communes?format=json&geometry=centre")
    fun getCommunes(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("fields", encoded = true) fields: String = "code,nom",
    ): Observable<List<GeoCommune>>
}
