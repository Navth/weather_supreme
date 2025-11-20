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
 * Benin
 */
class MeteoBeninService @Inject constructor(
    @ApplicationContext injectedContext: Context,
    @Named("JsonClient") val injectedJsonClient: Retrofit.Builder,
) : ClimWebService() {

    override val id = "meteobenin"
    override val countryCode = "BJ"
    private val countryName = injectedContext.currentLocale.getCountryName(countryCode)
    override val name = "MÃ©tÃ©o BÃ©nin".let {
        if (it.contains(countryName)) {
            it
        } else {
            "$it ($countryName)"
        }
    }
    override val privacyPolicyUrl = ""
    override val weatherAttribution = "Agence Nationale de la MÃ©tÃ©orologie du BÃ©nin"

    override val context = injectedContext
    override val jsonClient = injectedJsonClient
    override val baseUrl = "https://www.meteobenin.bj/"
    override val instancePreference = R.string.settings_weather_source_meteo_benin_instance

    override val alertAttribution = weatherAttribution
    override val normalsAttribution = weatherAttribution

    override val cityClimatePageId = "105"
}
