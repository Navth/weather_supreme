package breezyweather.domain.weather.model

import org.breezyweather.unit.ratio.Ratio
import java.io.Serializable

/**
 * Precipitation duration.
 */
data class PrecipitationProbability(
    val total: Ratio? = null,
    val thunderstorm: Ratio? = null,
    val rain: Ratio? = null,
    val snow: Ratio? = null,
    val ice: Ratio? = null,
) : Serializable {

    val isValid: Boolean
        get() = total != null && total.value > 0
}
