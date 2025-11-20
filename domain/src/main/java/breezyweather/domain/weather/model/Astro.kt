package breezyweather.domain.weather.model

import java.io.Serializable
import java.util.Date
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class Astro(
    val riseDate: Date? = null,
    val setDate: Date? = null,
) : Serializable {

    // Not made to be used for moon astro, only sun
    val duration: Duration?
        get() = if (riseDate == null || setDate == null) {
            // Polar night
            0.milliseconds
        } else if (riseDate.after(setDate)) {
            null
        } else {
            (setDate.time - riseDate.time).milliseconds
        }

    val isValid: Boolean
        get() = riseDate != null && setDate != null
}
