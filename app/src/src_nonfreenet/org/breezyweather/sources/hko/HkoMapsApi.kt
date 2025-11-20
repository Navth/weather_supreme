package org.breezyweather.sources.hko

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.hko.json.HkoForecastResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HkoMapsApi {
    // The output is in JSON despite the file extension in the URL
    @GET("ocf/dat/{grid}.xml")
    fun getForecast(
        @Path("grid") grid: String,
        @Query("v") v: Long,
    ): Observable<HkoForecastResult>
}
