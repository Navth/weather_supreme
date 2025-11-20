package org.breezyweather.sources.smhi

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.smhi.json.SmhiForecastResult
import retrofit2.http.GET
import retrofit2.http.Path

interface SmhiApi {
    @GET("category/pmp3g/version/2/geotype/point/lon/{lon}/lat/{lat}/data.json")
    fun getForecast(
        @Path("lon") lon: Double,
        @Path("lat") lat: Double,
    ): Observable<SmhiForecastResult>
}
