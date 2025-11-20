package org.breezyweather.common.source

import android.content.Context
import breezyweather.domain.location.model.LocationAddressInfo
import io.reactivex.rxjava3.core.Observable

/**
 * Reverse geocoding source
 */
interface ReverseGeocodingSource : FeatureSource, AddressSource {

    /**
     * Returns address info for the nearest location for the coordinates in parameter
     */
    fun requestNearestLocation(
        context: Context,
        latitude: Double,
        longitude: Double,
    ): Observable<List<LocationAddressInfo>>
}
