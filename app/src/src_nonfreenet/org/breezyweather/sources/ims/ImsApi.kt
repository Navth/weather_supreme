package org.breezyweather.sources.ims

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.ims.json.ImsLocationResult
import org.breezyweather.sources.ims.json.ImsWeatherResult
import retrofit2.http.GET
import retrofit2.http.Path

interface ImsApi {

    @GET("{lang}/locations_info")
    fun getLocations(
        @Path("lang") lang: String, // Allowed values: "en", "he" or "ar"
    ): Observable<ImsLocationResult>

    @GET("{lang}/city_portal/{locationId}")
    fun getWeather(
        @Path("lang") lang: String, // Allowed values: "en", "he" or "ar"
        @Path("locationId") locationId: String,
    ): Observable<ImsWeatherResult>
}
