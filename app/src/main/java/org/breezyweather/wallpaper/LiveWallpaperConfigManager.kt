package org.breezyweather.wallpaper

import android.content.Context
import org.breezyweather.domain.settings.ConfigStore

class LiveWallpaperConfigManager(context: Context) {
    val weatherKind: String
    val dayNightType: String
    val animationsEnabled: Boolean

    init {
        val config = ConfigStore(context, SP_LIVE_WALLPAPER_CONFIG)
        weatherKind = config.getString(KEY_WEATHER_KIND, null) ?: "auto"
        dayNightType = config.getString(KEY_DAY_NIGHT_TYPE, null) ?: "auto"
        animationsEnabled = config.getBoolean(KEY_ANIMATIONS_ENABLED, false)
    }

    companion object {
        private const val SP_LIVE_WALLPAPER_CONFIG = "live_wallpaper_config"
        private const val KEY_WEATHER_KIND = "weather_kind"
        private const val KEY_DAY_NIGHT_TYPE = "day_night_type"
        private const val KEY_ANIMATIONS_ENABLED = "animations_enabled"

        fun update(context: Context, weatherKind: String?, dayNightType: String?, animationsEnabled: Boolean) {
            ConfigStore(context, SP_LIVE_WALLPAPER_CONFIG)
                .edit()
                .putString(KEY_WEATHER_KIND, weatherKind)
                .putString(KEY_DAY_NIGHT_TYPE, dayNightType)
                .putBoolean(KEY_ANIMATIONS_ENABLED, animationsEnabled)
                .apply()
        }
    }
}
