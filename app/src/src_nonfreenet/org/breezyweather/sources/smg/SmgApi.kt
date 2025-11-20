package org.breezyweather.sources.smg

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.smg.json.SmgBulletinResult
import org.breezyweather.sources.smg.json.SmgCurrentResult
import org.breezyweather.sources.smg.json.SmgForecastResult
import org.breezyweather.sources.smg.json.SmgUvResult
import org.breezyweather.sources.smg.json.SmgWarningResult
import retrofit2.http.POST
import retrofit2.http.Query

interface SmgApi {
    @POST("weather_v2")
    fun getHourly(
        @Query("selection") selection: String = "48detail",
    ): Observable<SmgForecastResult>

    @POST("weather_v2")
    fun getDaily(
        @Query("selection") selection: String = "7daysforecast",
        @Query("lang") lang: String = "e",
    ): Observable<SmgForecastResult>

    @POST("weather_v2")
    fun getBulletin(
        @Query("selection") selection: String = "forecast",
        @Query("lang") lang: String = "e",
    ): Observable<SmgBulletinResult>

    @POST("weather_v2")
    fun getCurrent(
        @Query("selection") selection: String = "actualweather",
        @Query("lang") lang: String = "e",
    ): Observable<SmgCurrentResult>

    @POST("weather_v2")
    fun getUVIndex(
        @Query("selection") selection: String = "actualUVI",
        @Query("lang") lang: String = "e",
    ): Observable<SmgUvResult>

    @POST("weather_v2")
    fun getWarning(
        @Query("selection") warning: String,
        @Query("lang") lang: String = "e",
    ): Observable<SmgWarningResult>
}
