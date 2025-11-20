package org.breezyweather.common.source

import androidx.annotation.StringRes
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.R
import org.breezyweather.domain.source.resourceName

interface Source {
    /**
     * Id for the source. Must be unique.
     */
    val id: String

    /**
     * Name of the source.
     */
    val name: String

    /**
     * How this source should be grouped:
     * - Recommended
     * - Worldwide
     * - Continent
     */
    @StringRes
    fun getGroup(location: Location, feature: SourceFeature): Int {
        return if (this is FeatureSource &&
            getFeaturePriorityForLocation(location, feature) >= 0
        ) {
            R.string.weather_source_recommended
        } else if (this is HttpSource) {
            continent.resourceName
        } else {
            SourceContinent.WORLDWIDE.resourceName
        }
    }
}
