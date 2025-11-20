package org.breezyweather.sources.namem

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.common.extensions.code
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.LocationParametersSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.ReverseGeocodingSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class NamemServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    ReverseGeocodingSource,
    LocationParametersSource,
    NonFreeNetSource {

    override val id = "namem"
    override val name by lazy {
        if (context.currentLocale.code.startsWith("mn")) {
            "Ð¦Ð°Ð³ ÑƒÑƒÑ€, Ð¾Ñ€Ñ‡Ð½Ñ‹ ÑˆÐ¸Ð½Ð¶Ð¸Ð»Ð³ÑÑÐ½Ð¸Ð¹ Ð³Ð°Ð·Ð°Ñ€"
        } else {
            "NAMEM (${context.currentLocale.getCountryName("MN")})"
        }
    }
    override val continent = SourceContinent.ASIA
    override val privacyPolicyUrl = ""

    protected val weatherAttribution by lazy {
        if (context.currentLocale.code.startsWith("mn")) {
            "Ð¦Ð°Ð³ ÑƒÑƒÑ€, Ð¾Ñ€Ñ‡Ð½Ñ‹ ÑˆÐ¸Ð½Ð¶Ð¸Ð»Ð³ÑÑÐ½Ð¸Ð¹ Ð³Ð°Ð·Ð°Ñ€"
        } else {
            "National Agency for Meteorology and Environmental Monitoring"
        }
    }
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution,
        SourceFeature.CURRENT to weatherAttribution,
        SourceFeature.AIR_QUALITY to weatherAttribution,
        SourceFeature.NORMALS to weatherAttribution,
        SourceFeature.REVERSE_GEOCODING to weatherAttribution
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        return location.countryCode.equals("MN", ignoreCase = true)
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
