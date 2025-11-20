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
 * Democratic Republic of Congo
 */
class MettelsatService @Inject constructor(
    @ApplicationContext injectedContext: Context,
    @Named("JsonClient") val injectedJsonClient: Retrofit.Builder,
) : ClimWebService() {

    override val id = "mettelsat"
    override val countryCode = "CD"
    override val name = "Mettelsat RDC (${injectedContext.currentLocale.getCountryName(countryCode)})"
    override val privacyPolicyUrl = ""
    override val weatherAttribution = "Agence Nationale de MÃ©tÃ©orologie et de TÃ©lÃ©dÃ©tection par Satellite"

    override val context = injectedContext
    override val jsonClient = injectedJsonClient
    override val baseUrl = "https://www.meteordcongo.cd/"
    override val instancePreference = R.string.settings_weather_source_mettelsat_instance

    override val alertAttribution = weatherAttribution
    override val normalsAttribution = weatherAttribution

    override val cityClimatePageId = null
}
