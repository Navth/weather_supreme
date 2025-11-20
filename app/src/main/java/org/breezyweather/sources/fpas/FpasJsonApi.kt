package org.breezyweather.sources.fpas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FpasJsonApi {
    @GET("alert/area")
    fun getAlerts(
        @Query("min_lat") minLat: Double,
        @Query("max_lat") maxLat: Double,
        @Query("min_lon") minLon: Double,
        @Query("max_lon") maxLon: Double,
    ): Call<List<String>>
}
