package org.breezyweather.sources.bmkg

import io.reactivex.rxjava3.core.Observable
import org.breezyweather.sources.bmkg.json.BmkgPm25Result
import retrofit2.http.GET

interface BmkgAppApi {
    @GET("api/pm25/")
    fun getPm25(): Observable<List<BmkgPm25Result>>
}
