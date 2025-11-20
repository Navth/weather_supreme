package org.breezyweather.sources.metoffice

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import org.breezyweather.common.source.ConfigurableSource
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class MetOfficeServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    ConfigurableSource,
    NonFreeNetSource {

    override val id = "metoffice"
    override val name = "Met Office (${context.currentLocale.getCountryName("GB")})"
    override val continent = SourceContinent.EUROPE

    protected val weatherAttribution = "Met Office"
    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to weatherAttribution
    )

    override fun getFeaturePriorityForLocation(
        location: Location,
        feature: SourceFeature,
    ): Int {
        return when {
            arrayOf("GB", "GG", "IM", "JE", "GI", "FK").any {
                location.countryCode.equals(it, ignoreCase = true)
            } -> PRIORITY_HIGHEST
            else -> PRIORITY_NONE
        }
    }
}
