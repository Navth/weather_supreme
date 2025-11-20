package breezyweather.domain.weather.model

import org.breezyweather.unit.temperature.Temperature
import java.io.Serializable

/**
 * Normals
 */
class Normals(
    val daytimeTemperature: Temperature? = null,
    val nighttimeTemperature: Temperature? = null,
) : Serializable {

    val isValid: Boolean
        get() = daytimeTemperature != null && nighttimeTemperature != null
}
