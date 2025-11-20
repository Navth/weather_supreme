package org.breezyweather.sources.ncdr

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.common.xml.CapAlert
import org.breezyweather.sources.ncdr.xml.NcdrAlertsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NcdrApi {
    @GET("RssAtomFeeds.ashx")
    fun getAlerts(): Call<NcdrAlertsResult>

    @GET
    fun getAlert(
        @Url url: String,
    ): Observable<CapAlert>
}
