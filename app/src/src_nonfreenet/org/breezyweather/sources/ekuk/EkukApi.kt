package org.breezyweather.sources.ekuk

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.ekuk.json.EkukObservationsResult
import org.breezyweather.sources.ekuk.json.EkukStationsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface EkukApi {
    @GET("api/station/en")
    fun getStations(): Observable<EkukStationsResult>

    @GET("api/monitoring/en")
    fun getObservations(
        @Query("stations") station: String,
        @Query("indicators") indicators: String = "",
        @Query("range") range: String,
        @Query("type") type: String = "INDICATOR",
    ): Observable<List<EkukObservationsResult>>
}
