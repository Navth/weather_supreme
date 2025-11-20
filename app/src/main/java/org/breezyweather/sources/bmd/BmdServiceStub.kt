package org.breezyweather.sources.bmd

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
abstract class BmdServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    ReverseGeocodingSource,
    LocationParametersSource,
    NonFreeNetSource {

    override val id = "bmd"
    override val name by lazy {
        if (context.currentLocale.code.startsWith("bn")) {
            "à¦¬à¦¾à¦‚à¦²à¦¾à¦¦à§‡à¦¶ à¦†à¦¬à¦¹à¦¾à¦“à¦¯à¦¼à¦¾ à¦…à¦§à¦¿à¦¦à¦ªà§à¦¤à¦°"
        } else {
            "BMD (${context.currentLocale.getCountryName("BD")})"
        }
    }
    override val continent = SourceContinent.ASIA
    override val privacyPolicyUrl = ""

    protected val weatherAttribution = if (context.currentLocale.code.startsWith("bn")) {
        "à¦¬à¦¾à¦‚à¦²à¦¾à¦¦à§‡à¦¶ à¦†à¦¬à¦¹à¦¾à¦“à¦¯à¦¼à¦¾ à¦…à¦§à¦¿à¦¦à¦ªà§à¦¤à¦°"
    } else {
        "Bangladesh Meteorological Department"
    }
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution,
        SourceFeature.REVERSE_GEOCODING to weatherAttribution
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        return location.countryCode.equals("BD", ignoreCase = true)
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
