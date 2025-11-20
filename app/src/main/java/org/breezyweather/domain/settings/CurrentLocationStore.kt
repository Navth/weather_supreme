package org.breezyweather.domain.settings

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Date
import javax.inject.Inject

/**
 * Store the current location independently from the current location, so that in a future update, we could have
 * multiple current locations with different sources
 */
class CurrentLocationStore @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val config: ConfigStore = ConfigStore(context, CURRENT_LOCATION_STORE)

    var lastKnownLongitude: Float
        get() = config.getFloat(KEY_LAST_KNOWN_LONGITUDE, 0f)
        private set(value) {
            config.edit().putFloat(KEY_LAST_KNOWN_LONGITUDE, value).apply()
        }

    var lastKnownLatitude: Float
        get() = config.getFloat(KEY_LAST_KNOWN_LATITUDE, 0f)
        private set(value) {
            config.edit().putFloat(KEY_LAST_KNOWN_LATITUDE, value).apply()
        }

    var lastSuccessfulRefresh: Long
        get() = config.getLong(KEY_LAST_SUCCESSFUL_REFRESH, 0)
        private set(value) {
            config.edit().putLong(KEY_LAST_SUCCESSFUL_REFRESH, value).apply()
        }

    val isUsable: Boolean
        get() = lastKnownLatitude != 0f || lastKnownLongitude != 0f

    /**
     * Store an updated current location
     */
    fun updateCurrentLocation(
        longitude: Float,
        latitude: Float,
    ) {
        lastKnownLongitude = longitude
        lastKnownLatitude = latitude
        lastSuccessfulRefresh = Date().time
    }

    /**
     * Call this when you no longer have any need for current location
     */
    fun clearCurrentLocation() {
        lastKnownLongitude = 0f
        lastKnownLatitude = 0f
        lastSuccessfulRefresh = 0
    }

    companion object {
        private const val CURRENT_LOCATION_STORE = "current_location"
        private const val KEY_LAST_SUCCESSFUL_REFRESH = "last_successful_refresh"
        private const val KEY_LAST_KNOWN_LONGITUDE = "last_known_longitude"
        private const val KEY_LAST_KNOWN_LATITUDE = "last_known_latitude"
    }
}
