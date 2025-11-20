package org.breezyweather.sources.geosphereat

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.geosphereat.json.GeoSphereAtTimeseriesResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GeoSphereAtApi {

    @GET("v1/timeseries/forecast/nwp-v1-1h-2500m")
    fun getHourlyForecast(
        @Query("lat_lon", encoded = true) latLon: String,
        @Query("parameters", encoded = true) parameters: String,
    ): Observable<GeoSphereAtTimeseriesResult>

    @GET("v1/timeseries/forecast/nowcast-v1-15min-1km")
    fun getNowcast(
        @Query("lat_lon", encoded = true) latLon: String,
        @Query("parameters", encoded = true) parameters: String,
    ): Observable<GeoSphereAtTimeseriesResult>

    @GET("v1/timeseries/forecast/chem-v2-1h-{km}km")
    fun getAirQuality(
        @Path("km") km: Int, // 3 or 9 depending on bbox
        @Query("lat_lon", encoded = true) latLon: String,
        @Query("parameters", encoded = true) parameters: String,
    ): Observable<GeoSphereAtTimeseriesResult>
}
