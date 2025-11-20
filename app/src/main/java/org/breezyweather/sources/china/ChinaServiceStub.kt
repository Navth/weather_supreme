package org.breezyweather.sources.china

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.LocationParametersSource
import org.breezyweather.common.source.LocationSearchSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.ReverseGeocodingSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

abstract class ChinaServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    LocationSearchSource,
    ReverseGeocodingSource,
    LocationParametersSource,
    NonFreeNetSource {

    override val id = "china"
    override val name = context.currentLocale.getCountryName("CN")
    override val continent = SourceContinent.ASIA

    override val locationSearchAttribution = "åŒ—äº¬å¤©æ°”ã€å½©äº‘å¤©æ°”ã€ä¸­å›½çŽ¯å¢ƒç›‘æµ‹æ€»ç«™"

    protected val weatherAttribution = "åŒ—äº¬å¤©æ°”ã€å½©äº‘å¤©æ°”ã€ä¸­å›½çŽ¯å¢ƒç›‘æµ‹æ€»ç«™"
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution,
        SourceFeature.CURRENT to weatherAttribution,
        SourceFeature.AIR_QUALITY to weatherAttribution,
        SourceFeature.MINUTELY to weatherAttribution,
        SourceFeature.ALERT to weatherAttribution,
        SourceFeature.REVERSE_GEOCODING to name
    )
    override val attributionLinks = mapOf(
        "å½©äº‘å¤©æ°”" to "https://caiyunapp.com/",
        "ä¸­å›½çŽ¯å¢ƒç›‘æµ‹æ€»ç«™" to "https://www.cnemc.cn/"
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        return location.countryCode.equals("CN", ignoreCase = true)
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
