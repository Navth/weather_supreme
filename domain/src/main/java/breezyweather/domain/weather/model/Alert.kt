package breezyweather.domain.weather.model

import android.graphics.Color
import androidx.annotation.ColorInt
import breezyweather.domain.weather.reference.AlertSeverity
import java.io.Serializable
import java.util.Date

/**
 * Alert.
 *
 * All properties are [androidx.annotation.NonNull].
 */
data class Alert(
    /**
     * If not provided by the source, can be created from Objects.hash().toString()
     * Usually, you will use three parameters: alert type or title, alert level, alert start time
     */
    val alertId: String,
    val startDate: Date? = null,
    val endDate: Date? = null,
    val headline: String? = null,
    /**
     * HTML accepted. For compatibility with non-HTML fields, line breaks (\n) will be replaced with <br />.
     */
    val description: String? = null,
    val instruction: String? = null,
    val source: String? = null,
    val severity: AlertSeverity = AlertSeverity.UNKNOWN,
    @ColorInt val color: Int,
) : Serializable {

    companion object {
        @ColorInt
        fun colorFromSeverity(severity: AlertSeverity): Int {
            return when (severity) {
                AlertSeverity.EXTREME -> Color.rgb(212, 45, 65)
                AlertSeverity.SEVERE -> Color.rgb(240, 140, 17)
                AlertSeverity.MODERATE -> Color.rgb(244, 207, 0)
                AlertSeverity.MINOR -> Color.rgb(57, 156, 199)
                else -> Color.rgb(130, 168, 223)
            }
        }
    }
}
