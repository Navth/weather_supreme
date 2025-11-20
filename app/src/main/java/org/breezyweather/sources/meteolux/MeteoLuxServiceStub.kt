package org.breezyweather.sources.meteolux

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.R
import org.breezyweather.common.extensions.code
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.ReverseGeocodingSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class MeteoLuxServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    ReverseGeocodingSource,
    NonFreeNetSource {

    override val id = "meteolux"
    override val name = "MeteoLux (${context.currentLocale.getCountryName("LU")})"
    override val continent = SourceContinent.EUROPE

    private val weatherAttribution by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("fr") -> "MeteoLux (Transfert universel dans le Domaine Public Creative Commons CC0 1.0)"
                startsWith("de") ->
                    "MeteoLux (universellen Transfers in die Gemeinfreiheit (Public Domain) Creative Commons CC0 1.0)"
                else -> "MeteoLux (Universal transfer into the Public Domain Creative Commons CC0 1.0)."
            }
        } +
            " ${context.getString(R.string.data_modified, context.getString(R.string.breezy_weather))}"
    }
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution,
        SourceFeature.CURRENT to weatherAttribution,
        SourceFeature.ALERT to weatherAttribution,
        SourceFeature.REVERSE_GEOCODING to weatherAttribution
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        return location.countryCode.equals("LU", ignoreCase = true)
    }

    override fun getFeaturePriorityForLocation(
        location: Location,
        feature: SourceFeature,
    ): Int {
        return when {
            isFeatureSupportedForLocation(location, feature) -> PRIORITY_HIGHEST
            else -> PRIORITY_NONE
        }
    }

    // Only supports its own country
    override val knownAmbiguousCountryCodes: Array<String>? = null
}
