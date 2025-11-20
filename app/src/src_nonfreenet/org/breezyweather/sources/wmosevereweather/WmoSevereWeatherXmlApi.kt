package org.breezyweather.sources.wmosevereweather

import org.breezyweather.sources.common.xml.CapAlert
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * API World Meteorological Organization
 */
interface WmoSevereWeatherXmlApi {

    @GET
    fun getAlert(
        @Url capUrl: String,
    ): Call<CapAlert>
}
