package org.breezyweather.sources.climweb

import android.content.Context
import breezyweather.domain.location.model.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import org.breezyweather.R
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * Gambia
 */
class DwrGmService @Inject constructor(
    @ApplicationContext injectedContext: Context,
    @Named("JsonClient") val injectedJsonClient: Retrofit.Builder,
) : ClimWebService() {

    override val id = "dwrgm"
    override val countryCode = "GM"
    override val name = "DWR (${injectedContext.currentLocale.getCountryName(countryCode)})"
    override val privacyPolicyUrl = ""
    override val weatherAttribution = "Department of Water Resources"

    override val context = injectedContext
    override val jsonClient = injectedJsonClient
    override val baseUrl = "https://meteogambia.org/"
    override val instancePreference = R.string.settings_weather_source_dwr_gm_instance

    override val alertAttribution = weatherAttribution
    override val normalsAttribution = weatherAttribution

    override val cityClimatePageId = null
}
