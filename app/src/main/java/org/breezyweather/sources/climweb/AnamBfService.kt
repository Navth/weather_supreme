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
 * Burkina Faso
 */
class AnamBfService @Inject constructor(
    @ApplicationContext injectedContext: Context,
    @Named("JsonClient") val injectedJsonClient: Retrofit.Builder,
) : ClimWebService() {

    override val id = "anambf"
    override val countryCode = "BF"
    override val name = "ANAM-BF (${injectedContext.currentLocale.getCountryName(countryCode)})"
    override val privacyPolicyUrl = ""
    override val weatherAttribution = "Agence Nationale de la MÃ©tÃ©orologie du Burkina Faso"

    override val context = injectedContext
    override val jsonClient = injectedJsonClient
    override val baseUrl = "https://www.meteoburkina.bf/"
    override val instancePreference = R.string.settings_weather_source_anam_bf_instance

    override val alertAttribution = weatherAttribution
    override val normalsAttribution = weatherAttribution

    override val cityClimatePageId = null
}
