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
 * Sudan
 */
class SmaSuService @Inject constructor(
    @ApplicationContext injectedContext: Context,
    @Named("JsonClient") val injectedJsonClient: Retrofit.Builder,
) : ClimWebService() {

    override val id = "smasu"
    override val countryCode = "SD"
    override val name = "SMA (${injectedContext.currentLocale.getCountryName(countryCode)})"
    override val privacyPolicyUrl = ""
    override val weatherAttribution = "Sudan Meteorological Authority"

    override val context = injectedContext
    override val jsonClient = injectedJsonClient
    override val baseUrl = "https://meteosudan.sd/"
    override val instancePreference = R.string.settings_weather_source_sma_su_instance

    override val alertAttribution = weatherAttribution
    override val normalsAttribution = weatherAttribution

    override val cityClimatePageId = null
}
