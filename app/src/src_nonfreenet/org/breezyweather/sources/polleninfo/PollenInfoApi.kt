package org.breezyweather.sources.polleninfo

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.geosphereat.json.GeoSphereAtTimeseriesResult
import org.breezyweather.sources.polleninfo.json.PollenInfoResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PollenInfoApi {

    @GET("api/forecast/public")
    fun getData(
        @Query("country") twoLetterIsoCountryCode: String,
        @Query("lang") twoLetterIsoLanguageCode: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("apikey") apikey: String,
    ): Observable<PollenInfoResult>
}
