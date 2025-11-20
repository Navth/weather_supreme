package org.breezyweather.common.source

import android.content.Context
import org.breezyweather.common.preference.Preference

/**
 * Implement this if you need a preference screen for all locations
 * Use PreferencesParametersSource instead if you need per-location parameters
 */
interface ConfigurableSource : Source {

    val isConfigured: Boolean
    val isRestricted: Boolean

    fun getPreferences(context: Context): List<Preference>
}
