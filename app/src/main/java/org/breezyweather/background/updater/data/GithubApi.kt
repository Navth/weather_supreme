package org.breezyweather.background.updater.data

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Open-Meteo API
 */
interface GithubApi {
    @GET("repos/{org}/{repository}/releases/latest")
    suspend fun getLatest(
        @Path("org") org: String,
        @Path("repository") repository: String,
    ): GithubRelease
}
