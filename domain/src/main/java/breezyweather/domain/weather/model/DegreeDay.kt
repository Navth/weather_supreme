package breezyweather.domain.weather.model

import java.io.Serializable

/**
 * Degree Day
 */
class DegreeDay(
    val heating: org.breezyweather.unit.temperature.Temperature? = null,
    val cooling: org.breezyweather.unit.temperature.Temperature? = null,
) : Serializable {

    val isValid = (heating != null && heating.value > 0L) || (cooling != null && cooling.value > 0L)
}
