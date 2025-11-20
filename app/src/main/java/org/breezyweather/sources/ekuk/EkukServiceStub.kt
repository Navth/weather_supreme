package org.breezyweather.sources.ekuk

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
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class EkukServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    LocationParametersSource,
    NonFreeNetSource {

    override val id = "ekuk"
    override val name = "EKUK (${context.currentLocale.getCountryName("EE")})"
    override val continent = SourceContinent.EUROPE
    override val privacyPolicyUrl = ""
    protected val weatherAttribution by lazy {
        with(context.currentLocale.code) {
            when {
                startsWith("et") -> "Eesti Keskkonnauuringute Keskus"
                startsWith("ru") -> "Ð­ÑÑ‚Ð¾Ð½ÑÐºÐ¸Ð¹ Ñ†ÐµÐ½Ñ‚Ñ€ ÑÐºÐ¾Ð»Ð¾Ð³Ð¸Ñ‡ÐµÑÐºÐ¸Ñ… Ð¸ÑÑÐ»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ð¹"
                startsWith("uk") -> "Ð•ÑÑ‚Ð¾Ð½ÑÑŒÐºÐ¸Ð¹ Ñ†ÐµÐ½Ñ‚Ñ€ ÐµÐºÐ¾Ð»Ð¾Ð³Ñ–Ñ‡Ð½Ð¸Ñ… Ð´Ð¾ÑÐ»Ñ–Ð´Ð¶ÐµÐ½ÑŒ"
                else -> "Estonian Environmental Research Center"
            }
        }
    }

    override val supportedFeatures = mapOf(
        SourceFeature.AIR_QUALITY to weatherAttribution
        // SourceFeature.POLLEN to weatherAttribution
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
}
