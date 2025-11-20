package org.breezyweather.sources.smg

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.smg.json.SmgAirQualityResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SmgCmsApi {
    @GET("/uploads/sync/json/latestAirConcentration.json")
    fun getAirQuality(
        @Query("v") v: Long,
    ): Observable<SmgAirQualityResult>
}
