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
 * Chad
 */
class MeteoTchadService @Inject constructor(
    @ApplicationContext injectedContext: Context,
    @Named("JsonClient") val injectedJsonClient: Retrofit.Builder,
) : ClimWebService() {

    override val id = "meteotchad"
    override val countryCode = "TD"
    private val countryName = injectedContext.currentLocale.getCountryName(countryCode)
    override val name = "MÃ©tÃ©o Tchad".let {
        if (it.contains(countryName)) {
            it
        } else {
            "$it ($countryName)"
        }
    }
    override val privacyPolicyUrl = ""
    override val weatherAttribution = "Agence Nationale de la MÃ©tÃ©orologie du Tchad"

    override val context = injectedContext
    override val jsonClient = injectedJsonClient
    override val baseUrl = "https://www.meteotchad.org/"
    override val instancePreference = R.string.settings_weather_source_meteo_tchad_instance

    override val alertAttribution = weatherAttribution
    override val normalsAttribution = weatherAttribution

    override val cityClimatePageId = "65"
}
