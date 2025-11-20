package org.breezyweather.sources.recosante

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.recosante.json.RecosanteResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API RecosantÃ©
 */
interface RecosanteApi {
    @GET("v1/")
    fun getData(
        @Query("show_raep") showRaep: Boolean,
        @Query("insee") insee: String,
    ): Observable<RecosanteResult>
}
