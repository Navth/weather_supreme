package breezyweather.domain.weather.model

import java.io.Serializable

/**
 * Moon phase.
 */
class MoonPhase(
    /**
     * Angle between 0 to 360 (no negative! Add 180 if you have negative numbers)
     */
    val angle: Int? = null,
) : Serializable {

    val isValid: Boolean
        get() = angle != null
}
