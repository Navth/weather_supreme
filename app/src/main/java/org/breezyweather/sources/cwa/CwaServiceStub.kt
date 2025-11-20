package org.breezyweather.sources.cwa

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import com.google.maps.android.model.LatLng
import org.breezyweather.common.extensions.code
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import org.breezyweather.common.source.ConfigurableSource
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.LocationParametersSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.ReverseGeocodingSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGH
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE
import org.breezyweather.sources.nlsc.NlscServiceStub.Companion.KINMEN_BBOX
import org.breezyweather.sources.nlsc.NlscServiceStub.Companion.MATSU_BBOX
import org.breezyweather.sources.nlsc.NlscServiceStub.Companion.PENGHU_BBOX
import org.breezyweather.sources.nlsc.NlscServiceStub.Companion.TAIWAN_BBOX
import org.breezyweather.sources.nlsc.NlscServiceStub.Companion.WUQIU_BBOX

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class CwaServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    ReverseGeocodingSource,
    LocationParametersSource,
    ConfigurableSource,
    NonFreeNetSource {

    override val id = "cwa"
    override val name by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("zh") -> "ä¸­å¤®æ°£è±¡ç½²"
                else -> "CWA"
            }
        } +
            " (${context.currentLocale.getCountryName("TW")})"
    }
    override val continent = SourceContinent.ASIA

    protected val weatherAttribution by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("zh") -> "ä¸­å¤®æ°£è±¡ç½²"
                else -> "Central Weather Administration"
            }
        }
    }
    protected val airQualityAttribution by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("zh") -> "ç’°å¢ƒéƒ¨"
                else -> "Ministry of Environment"
            }
        }
    }
    protected val reverseGeocodingAttribution by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("zh") -> "å…§æ”¿éƒ¨åœ‹åœŸæ¸¬ç¹ªä¸­å¿ƒ"
                else -> "National Land Survey and Mapping Center"
            }
        }
    }
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution,
        SourceFeature.CURRENT to weatherAttribution,
        SourceFeature.AIR_QUALITY to airQualityAttribution,
        SourceFeature.ALERT to weatherAttribution,
        SourceFeature.NORMALS to weatherAttribution,
        SourceFeature.REVERSE_GEOCODING to weatherAttribution
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        val latLng = LatLng(location.latitude, location.longitude)
        return location.countryCode.equals("TW", ignoreCase = true) ||
            TAIWAN_BBOX.contains(latLng) ||
            PENGHU_BBOX.contains(latLng) ||
            KINMEN_BBOX.contains(latLng) ||
            WUQIU_BBOX.contains(latLng) ||
            MATSU_BBOX.contains(latLng)
    }

    override fun getFeaturePriorityForLocation(
        location: Location,
        feature: SourceFeature,
    ): Int {
        return when {
            isFeatureSupportedForLocation(location, feature) -> if (feature == SourceFeature.ALERT) {
                PRIORITY_HIGH // This makes NCDR being used in priority
            } else {
                PRIORITY_HIGHEST
            }
            else -> PRIORITY_NONE
        }
    }

    // Only supports its own country
    override val knownAmbiguousCountryCodes: Array<String>? = null
}
