package org.breezyweather.common.source

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceFeature
import io.reactivex.rxjava3.core.Observable

/**
 * Implement this if you need parameters such as an ID for the location
 * For example, before fetching weather, you need to call an URL with longitude,latitude that
 * will then give you the ID that needs to be stored
 * ONLY used before fetching main weather OR secondary weather data
 */
interface LocationParametersSource : Source {

    /**
     * Parameters:
     * - the location
     * - if coordinates were changed (only on the current location)
     * - list of features requested. Empty if not specific to a feature (main source)
     */
    fun needsLocationParametersRefresh(
        location: Location,
        coordinatesChanged: Boolean,
        features: List<SourceFeature> = emptyList(),
    ): Boolean

    /**
     * Fetch any parameters you need and then make a map. For example :
     * {"gridId": "20", "gridX": "30", "gridY": "25"}
     * TODO: Add feature parameter (NWS needs to know if requesting current)
     */
    fun requestLocationParameters(
        context: Context,
        location: Location,
    ): Observable<Map<String, String>>
}
