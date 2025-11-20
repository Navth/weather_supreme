package org.breezyweather.sources.atmo

import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.common.source.ConfigurableSource
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.LocationParametersSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class AtmoFranceServiceStub() :
    HttpSource(),
    WeatherSource,
    LocationParametersSource,
    ConfigurableSource,
    NonFreeNetSource {

    override val id = "atmofrance"
    override val name = "Atmo France"
    override val continent = SourceContinent.EUROPE

    override val supportedFeatures = mapOf(
        SourceFeature.POLLEN to "Atmo France â€¢ data.gouv.fr (Etalab 2.0)"
    )
    override val attributionLinks = mapOf(
        "Atmo France" to "https://www.atmo-france.org/",
        "data.gouv.fr" to "https://www.data.gouv.fr/"
    )

    override fun isFeatureSupportedForLocation(
        location: Location,
        feature: SourceFeature,
    ): Boolean {
        return feature == SourceFeature.POLLEN &&
            !location.countryCode.isNullOrEmpty() &&
            location.countryCode.equals("FR", ignoreCase = true)
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
