package breezyweather.domain.weather.model

import org.breezyweather.unit.precipitation.Precipitation
import java.io.Serializable
import java.util.Date
import kotlin.math.log10
import kotlin.math.pow
import kotlin.time.Duration.Companion.minutes

/**
 * Minutely.
 */
data class Minutely(
    val date: Date,
    val minuteInterval: Int,
    val precipitationIntensity: Precipitation? = null,
) : Serializable {

    val dbz: Int?
        get() = precipitationIntensityToDBZ(precipitationIntensity?.inMillimeters)

    val endingDate: Date
        get() = Date(date.time + minuteInterval.minutes.inWholeMilliseconds)

    fun toValidOrNull(): Minutely? {
        return copy(
            precipitationIntensity = precipitationIntensity?.toValidHourlyOrNull()
        )
    }

    companion object {

        private fun precipitationIntensityToDBZ(intensity: Double?): Int? {
            return intensity?.let {
                (10.0 * log10(200.0 * Math.pow(intensity, 8.0 / 5.0))).toInt()
            }
        }

        fun dbzToPrecipitationIntensity(dbz: Double?): Double? {
            return if (dbz == null) {
                null
            } else {
                if (dbz <= 5) 0.0 else (10.0.pow(dbz / 10.0) / 200.0).pow(5.0 / 8.0)
            }
        }
    }
}
