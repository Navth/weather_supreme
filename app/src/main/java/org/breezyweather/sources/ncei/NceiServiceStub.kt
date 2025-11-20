package org.breezyweather.sources.ncei

import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.LocationParametersSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.WeatherSource
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_HIGHEST
import org.breezyweather.common.source.WeatherSource.Companion.PRIORITY_NONE

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class NceiServiceStub() :
    HttpSource(),
    WeatherSource,
    LocationParametersSource,
    NonFreeNetSource {

    override val id = "ncei"
    override val name = "NCEI"
    override val continent = SourceContinent.WORLDWIDE

    protected val weatherAttribution = "National Centers for Environmental Information"
    override val supportedFeatures = mapOf(
        SourceFeature.NORMALS to weatherAttribution
    )

    override fun getFeaturePriorityForLocation(
        location: Location,
        feature: SourceFeature,
    ): Int {
        return when {
            arrayOf("US", "PR", "VI", "MP", "GU").any {
                location.countryCode.equals(it, ignoreCase = true)
            } -> PRIORITY_HIGHEST
            else -> PRIORITY_NONE
        }
    }
}
