package org.breezyweather.sources.atmo

import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.common.source.ConfigurableSource
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class AtmoServiceStub() :
    HttpSource(),
    WeatherSource,
    ConfigurableSource,
    NonFreeNetSource {

    protected abstract val attribution: String

    override val continent = SourceContinent.EUROPE

    override val supportedFeatures
        get() = mapOf(
            SourceFeature.AIR_QUALITY to attribution
        )
    protected abstract fun isLocationInRegion(location: Location): Boolean
    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        return feature == SourceFeature.AIR_QUALITY &&
            !location.countryCode.isNullOrEmpty() &&
            location.countryCode.equals("FR", ignoreCase = true) &&
            isLocationInRegion(location)
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
}
