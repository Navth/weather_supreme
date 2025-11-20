package org.breezyweather.sources.fpas

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.common.xml.CapAlert
import retrofit2.http.GET
import retrofit2.http.Path

interface FpasXmlApi {
    @GET("alert/{uuid}")
    fun getAlert(
        @Path("uuid") uuid: String,
    ): Observable<CapAlert>
}
