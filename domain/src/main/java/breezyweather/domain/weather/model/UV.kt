package breezyweather.domain.weather.model

import java.io.Serializable
import kotlin.math.roundToInt

/**
 * UV.
 */
class UV(
    val index: Double? = null,
) : Serializable {

    val isValid: Boolean
        get() = index != null

    companion object {
        const val UV_INDEX_LOW = 3.0
        const val UV_INDEX_MIDDLE = 6.0
        const val UV_INDEX_HIGH = 8.0
        const val UV_INDEX_EXCESSIVE = 11.0

        val uvThresholds = listOf(
            0,
            UV_INDEX_LOW.roundToInt(),
            UV_INDEX_MIDDLE.roundToInt(),
            UV_INDEX_HIGH.roundToInt(),
            UV_INDEX_EXCESSIVE.roundToInt()
        )
    }
}
