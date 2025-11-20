package org.breezyweather.sources.ilmateenistus

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
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
abstract class IlmateenistusServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    ReverseGeocodingSource,
    NonFreeNetSource {

    override val id = "ilmateenistus"
    override val name = "Ilmateenistus (${context.currentLocale.getCountryName("EE")})"
    override val continent = SourceContinent.EUROPE

    protected val weatherAttribution by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("et") -> "Keskkonnaagentuur"
                startsWith("ru") -> "ÐÐ³ÐµÐ½Ñ‚ÑÑ‚Ð²Ð¾ Ð¾ÐºÑ€ÑƒÐ¶Ð°ÑŽÑ‰ÐµÐ¹ ÑÑ€ÐµÐ´Ñ‹"
                else -> "Estonian Environment Agency"
            }
        }
    }
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution,
        SourceFeature.REVERSE_GEOCODING to weatherAttribution
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        return location.countryCode.equals("EE", ignoreCase = true)
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
