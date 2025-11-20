package org.breezyweather.sources.atmo

import android.content.Context
import breezyweather.domain.location.model.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import org.breezyweather.BuildConfig
import org.breezyweather.R
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * ATMO Auvergne-RhÃ´ne-Alpes air quality service.
 */
class AtmoAuraService @Inject constructor(
    @ApplicationContext injectedContext: Context,
    @Named("JsonClient") injectedJsonClient: Retrofit.Builder,
) : AtmoService() {

    override val id = "atmoaura"
    override val name = "Atmo Auvergne-RhÃ´ne-Alpes (${injectedContext.currentLocale.getCountryName("FR")})"
    override val attribution = "Atmo Auvergne-RhÃ´ne-Alpes"
    override val attributionLinks = mapOf(
        attribution to "https://www.atmo-auvergnerhonealpes.fr/"
    )
    override val privacyPolicyUrl = "https://www.atmo-auvergnerhonealpes.fr/article/politique-de-confidentialite"

    override val context = injectedContext
    override val jsonClient = injectedJsonClient
    override val baseUrl = "https://api.atmo-aura.fr/air2go/v3/"

    override val apiKeyPreference = R.string.settings_weather_source_atmo_aura_api_key
    override val builtInApiKey = BuildConfig.ATMO_AURA_KEY

    override fun isLocationInRegion(location: Location): Boolean {
        return location.admin1 in arrayOf(
            "Auvergne-RhÃ´ne-Alpes",
            "Auvergne-Rhone-Alpes",
            "Auvergne RhÃ´ne Alpes",
            "Auvergne Rhone Alpes"
        ) ||
            location.admin1Code in arrayOf("FR-ARA", "ARA", "84") ||
            location.admin2 in arrayOf(
                "Ain", // 01
                "Allier", // 03
                "ArdÃ¨che", // 07
                "Ardeche", // 07
                "Cantal", // 15
                "DrÃ´me", // 26
                "Drome", // 26
                "IsÃ¨re", // 38
                "Isere", // 38
                "Loire", // 42
                "Haute Loire", // 43
                "Haute-Loire", // 43
                "MÃ©tropole de Lyon", // 69M
                "Puy-de-DÃ´me", // 63
                "Puy-de-Dome", // 63
                "Puy de DÃ´me", // 63
                "Puy de Dome", // 63
                "RhÃ´ne", // 69
                "Rhone", // 69
                "Savoie", // 73
                "Haute-Savoie", // 74
                "Haute Savoie" // 74
            ) ||
            arrayOf(
                "01", // Ain
                "03", // Allier
                "07", // ArdÃ¨che
                "15", // Cantal
                "26", // DrÃ´me
                "38", // IsÃ¨re
                "42", // Loire
                "43", // Haute-Loire
                "63", // Puy-de-DÃ´me
                "69", // RhÃ´ne
                "69M", // MÃ©tropole de Lyon
                "73", // Savoie
                "74" // Haute-Savoie
            ).any { location.admin2Code?.endsWith(it, ignoreCase = true) == true }
    }
}
