package org.breezyweather.common.source

import android.content.Context
import breezyweather.domain.location.model.Location
import io.reactivex.rxjava3.core.Observable
import java.util.TimeZone

/**
 * Timezone matcher source
 */
interface TimeZoneSource : Source {

    /**
     * Returns the timezone for this location
     * Returns GMT if failed to find the timezone
     */
    fun requestTimezone(context: Context, location: Location): Observable<TimeZone>
}
