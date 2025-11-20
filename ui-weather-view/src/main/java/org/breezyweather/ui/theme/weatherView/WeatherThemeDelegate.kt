package org.breezyweather.ui.theme.weatherView

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.Size

interface WeatherThemeDelegate {

    fun getWeatherView(context: Context): WeatherView

    /**
     * @return colors[] {
     * theme color,
     * color of daytime chart line,
     * color of nighttime chart line
     * }
     */
    @ColorInt
    @Size(3)
    fun getThemeColors(
        context: Context,
        weatherKind: Int,
        daylight: Boolean,
    ): IntArray

    fun isLightBackground(
        context: Context,
        weatherKind: Int,
        daylight: Boolean,
    ): Boolean

    @ColorInt
    fun getBackgroundColor(
        context: Context,
        weatherKind: Int,
        daylight: Boolean,
    ): Int

    @ColorInt
    fun getOnBackgroundColor(
        context: Context,
        weatherKind: Int,
        daylight: Boolean,
    ): Int
}
