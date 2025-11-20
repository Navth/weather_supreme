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
 * Mali
 */
class MaliMeteoService @Inject constructor(
    @ApplicationContext injectedContext: Context,
    @Named("JsonClient") val injectedJsonClient: Retrofit.Builder,
) : ClimWebService() {

    override val id = "malimeteo"
    override val countryCode = "ML"
    private val countryName = injectedContext.currentLocale.getCountryName(countryCode)
    override val name = "Mali-MÃ©tÃ©o".let {
        if (it.contains(countryName)) {
            it
        } else {
            "$it ($countryName)"
        }
    }
    override val privacyPolicyUrl = ""
    override val weatherAttribution = "Agence Nationale de la MÃ©tÃ©orologie du Mali"

    override val context = injectedContext
    override val jsonClient = injectedJsonClient
    override val baseUrl = "https://malimeteo.ml/"
    override val instancePreference = R.string.settings_weather_source_mali_meteo_instance

    override val alertAttribution = weatherAttribution
    override val normalsAttribution = weatherAttribution

    override val cityClimatePageId = null
}
