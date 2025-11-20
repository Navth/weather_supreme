package org.breezyweather.sources.lhmt

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.lhmt.json.LhmtAlertsResult
import retrofit2.http.GET
import retrofit2.http.Path

interface LhmtWwwApi {
    @GET("app/mu-plugins/Meteo/Components/WeatherWarningsNew/list_JSON.php")
    fun getAlertList(): Observable<List<String>>

    @GET("{path}")
    fun getAlerts(
        @Path("path", encoded = true) path: String,
    ): Observable<LhmtAlertsResult>
}
