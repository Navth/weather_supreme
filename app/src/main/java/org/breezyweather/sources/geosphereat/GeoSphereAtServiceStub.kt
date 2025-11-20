package org.breezyweather.sources.geosphereat

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import com.google.maps.android.model.LatLng
import com.google.maps.android.model.LatLngBounds
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGH
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class GeoSphereAtServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    NonFreeNetSource {

    override val id = "geosphereat"
    private val countryName = context.currentLocale.getCountryName("AT")
    override val name = "GeoSphere Austria".let {
        if (it.contains(countryName)) {
            it
        } else {
            "$it ($countryName)"
        }
    }
    override val continent = SourceContinent.EUROPE

    private val weatherAttribution = "GeoSphere Austria (Creative Commons Attribution 4.0)"
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution,
        SourceFeature.AIR_QUALITY to weatherAttribution,
        SourceFeature.MINUTELY to weatherAttribution,
        SourceFeature.ALERT to weatherAttribution
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        val latLng = LatLng(location.latitude, location.longitude)
        return when (feature) {
            SourceFeature.FORECAST -> hourlyBbox.contains(latLng)
            SourceFeature.AIR_QUALITY -> airQuality9KmBbox.contains(latLng)
            SourceFeature.MINUTELY -> nowcastBbox.contains(latLng)
            SourceFeature.ALERT -> location.countryCode.equals("AT", ignoreCase = true)
            else -> false
        }
    }

    /**
     * We donâ€™t recommend forecast as itâ€™s way too light, compared to other sources
     * Highest priority for Austria
     * High priority for neighbour countries
     */
    override fun getFeaturePriorityForLocation(
        location: Location,
        feature: SourceFeature,
    ): Int {
        return when {
            location.countryCode.equals("AT", ignoreCase = true) &&
                feature != SourceFeature.FORECAST -> PRIORITY_HIGHEST
            feature == SourceFeature.MINUTELY &&
                isFeatureSupportedForLocation(location, feature) -> PRIORITY_HIGH
            else -> PRIORITY_NONE
        }
    }

    companion object {
        val hourlyBbox = LatLngBounds.parse(west = 5.49, south = 42.98, east = 22.1, north = 51.82)
        val airQuality3KmBbox = LatLngBounds.parse(west = 2.86, south = 40.91, east = 23.74, north = 53.75)
        val airQuality9KmBbox = LatLngBounds.parse(west = -53.73, south = 15.59, east = 80.33, north = 74.39)
        val nowcastBbox = LatLngBounds.parse(west = 8.1, south = 45.5, east = 17.74, north = 49.48)
    }
}
