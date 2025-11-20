package breezyweather.domain.weather.model

import org.breezyweather.unit.speed.Speed
import java.io.Serializable

/**
 * Wind
 */
data class Wind(
    /**
     * Between 0 and 360, or -1 if variable
     */
    val degree: Double? = null,
    /**
     * In m/s
     */
    val speed: Speed? = null,
    /**
     * In m/s
     */
    val gusts: Speed? = null,
) : Serializable {

    val isValid: Boolean
        get() = speed != null && speed.value > 0

    val arrow: String?
        get() = when (degree) {
            null -> null
            -1.0 -> "âŸ³"
            in 22.5..67.5 -> "â†™"
            in 67.5..112.5 -> "â†"
            in 112.5..157.5 -> "â†–"
            in 157.5..202.5 -> "â†‘"
            in 202.5..247.5 -> "â†—"
            in 247.5..292.5 -> "â†’"
            in 292.5..337.5 -> "â†˜"
            else -> "â†“"
        }

    companion object {

        fun validateDegree(degree: Double?): Double? {
            return degree?.let { if (it == -1.0 || it in 0.0..360.0) it else null }
        }
    }
}
