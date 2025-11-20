package org.breezyweather.sources.nlsc

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.nlsc.xml.NlscLocationCodesResult
import retrofit2.http.GET
import retrofit2.http.Path

interface NlscApi {
    @GET("other/TownVillagePointQuery1/{lon}/{lat}/{epsg}")
    fun getLocationCodes(
        @Path("lon") lon: Double,
        @Path("lat") lat: Double,
        @Path("epsg") epsg: Long = 4326,
    ): Observable<NlscLocationCodesResult>
}
