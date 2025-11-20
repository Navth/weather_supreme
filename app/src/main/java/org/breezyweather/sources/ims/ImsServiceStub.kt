package org.breezyweather.sources.ims

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
abstract class ImsServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    ReverseGeocodingSource,
    LocationParametersSource,
    NonFreeNetSource {

    override val id = "ims"
    override val name by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("ar") -> "Ø®Ø¯Ù…Ø© Ø§Ù„Ø£Ø±ØµØ§Ø¯ Ø§Ù„Ø¬ÙˆÙŠØ© Ø§Ù„Ø¥Ø³Ø±Ø§Ø¦ÙŠÙ„ÙŠØ©"
                startsWith("he") || startsWith("iw") -> "×”×©×™×¨×•×ª ×”×ž×˜××•×¨×•×œ×•×’×™ ×”×™×©×¨××œ×™"
                else -> "IMS (${context.currentLocale.getCountryName("IL")})"
            }
        }
    }
    override val continent = SourceContinent.ASIA

    protected val weatherAttribution by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("ar") -> "Ø®Ø¯Ù…Ø© Ø§Ù„Ø£Ø±ØµØ§Ø¯ Ø§Ù„Ø¬ÙˆÙŠØ© Ø§Ù„Ø¥Ø³Ø±Ø§Ø¦ÙŠÙ„ÙŠØ©"
                startsWith("he") || startsWith("iw") -> "×”×©×™×¨×•×ª ×”×ž×˜××•×¨×•×œ×•×’×™ ×”×™×©×¨××œ×™"
                else -> "Israel Meteorological Service"
            }
        }
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
        // Israel + West Bank + Gaza Strip
        return location.countryCode.equals("IL", ignoreCase = true) ||
            location.countryCode.equals("PS", ignoreCase = true)
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

    override val knownAmbiguousCountryCodes: Array<String> = arrayOf("IL")
}
