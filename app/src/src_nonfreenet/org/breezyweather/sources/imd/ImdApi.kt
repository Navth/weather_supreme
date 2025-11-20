package org.breezyweather.sources.imd

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.imd.json.ImdWeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ImdApi {
    @GET("test4_mme.php")
    fun getForecast(
        @Query("lat_gfs") lat: Double,
        @Query("lon_gfs") lon: Double,
        @Query("date") date: String,
    ): Observable<ImdWeatherResult>
}
