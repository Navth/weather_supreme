package org.breezyweather.sources.dmi

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
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
abstract class DmiServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    ReverseGeocodingSource,
    LocationParametersSource,
    NonFreeNetSource {

    override val id = "dmi"
    override val name = "DMI (${context.currentLocale.getCountryName("DK")})"
    override val continent = SourceContinent.EUROPE

    private val weatherAttribution = "DMI (Creative Commons CC BY)"
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution,
        SourceFeature.ALERT to weatherAttribution,
        SourceFeature.REVERSE_GEOCODING to weatherAttribution
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        return feature != SourceFeature.ALERT ||
            arrayOf("DK", "FO", "GL").any { it.equals(location.countryCode, ignoreCase = true) }
    }

    override fun getFeaturePriorityForLocation(
        location: Location,
        feature: SourceFeature,
    ): Int {
        return when {
            // Always use the same criterias as alert
            isFeatureSupportedForLocation(location, SourceFeature.ALERT) -> PRIORITY_HIGHEST
            else -> PRIORITY_NONE
        }
    }

    // We have no way to distinguish the ones below
    override val knownAmbiguousCountryCodes: Array<String> = arrayOf(
        "FI", // Territories: AX
        "MA" // Claims: EH
    )
}
