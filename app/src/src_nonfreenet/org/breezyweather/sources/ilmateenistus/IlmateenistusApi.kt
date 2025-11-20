package org.breezyweather.sources.ilmateenistus

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.ilmateenistus.json.IlmateenistusForecastResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IlmateenistusApi {
    @GET("wp-content/themes/ilm2020/meteogram.php")
    fun getHourly(
        @Query("coordinates") coordinates: String,
    ): Observable<IlmateenistusForecastResult>
}
