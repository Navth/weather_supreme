package org.breezyweather.sources.bmd

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.bmd.json.BmdForecastResult
import retrofit2.http.GET
import retrofit2.http.Query

interface BmdApi {
    @GET("upazila_forecast_recent")
    fun getDaily(
        @Query("SOURCE") source: String = "BMDWRF",
        @Query("PCODE") pCode: String,
    ): Observable<BmdForecastResult>

    @GET("upazila_forecast_steps_recent")
    fun getHourly(
        @Query("SOURCE") source: String = "BMDWRF",
        @Query("PCODE") pCode: String,
    ): Observable<BmdForecastResult>
}
