package org.breezyweather.sources.openweather

import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.common.source.ConfigurableSource
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.WeatherSource

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class OpenWeatherServiceStub() :
    HttpSource(),
    WeatherSource,
    ConfigurableSource,
    NonFreeNetSource {

    override val id = "openweather"
    override val name = "OpenWeather"
    override val continent = SourceContinent.WORLDWIDE

    override val supportedFeatures = mapOf(
        SourceFeature.FORECAST to name,
        SourceFeature.CURRENT to name,
        SourceFeature.AIR_QUALITY to name
    )
}
