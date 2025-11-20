package breezyweather.domain.weather.model

import java.io.Serializable

/**
 * Precipitation.
 */
data class Precipitation(
    val total: org.breezyweather.unit.precipitation.Precipitation? = null,
    val thunderstorm: org.breezyweather.unit.precipitation.Precipitation? = null,
    val rain: org.breezyweather.unit.precipitation.Precipitation? = null,
    val snow: org.breezyweather.unit.precipitation.Precipitation? = null,
    val ice: org.breezyweather.unit.precipitation.Precipitation? = null,
) : Serializable {

    companion object {
        // Based on India Meteorological Department day values (divided by two for half days)
        // https://mausam.imd.gov.in/imd_latest/contents/pdf/forecasting_sop.pdf
        const val PRECIPITATION_HALF_DAY_VERY_LIGHT = 1.25
        const val PRECIPITATION_HALF_DAY_LIGHT = 7.75
        const val PRECIPITATION_HALF_DAY_MEDIUM = 32.25
        const val PRECIPITATION_HALF_DAY_HEAVY = 57.75
        const val PRECIPITATION_HALF_DAY_RAINSTORM = 102.25

        // Chapter 9.3.1 - Nowcasting
        const val PRECIPITATION_HOURLY_LIGHT = 5.0
        const val PRECIPITATION_HOURLY_MEDIUM = 10.0
        const val PRECIPITATION_HOURLY_HEAVY = 15.0
        const val PRECIPITATION_HOURLY_RAINSTORM = 20.0
    }
}
