package org.breezyweather.sources.dmi

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.dmi.json.DmiResult
import org.breezyweather.sources.dmi.json.DmiWarningResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DmiApi {
    @GET("NinJo2DmiDk/ninjo2dmidk")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cmd") cmd: String,
    ): Observable<DmiResult>

    @GET("dmidk_byvejrWS/rest/texts/varsler/geonameid/{id}")
    fun getAlerts(
        @Path("id") id: String,
    ): Observable<DmiWarningResult>
}
