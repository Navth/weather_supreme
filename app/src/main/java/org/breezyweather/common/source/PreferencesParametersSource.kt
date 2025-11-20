package org.breezyweather.common.source

import android.content.Context
import androidx.compose.runtime.Composable
import breezyweather.domain.location.model.Location
import breezyweather.domain.source.SourceFeature
import kotlinx.collections.immutable.ImmutableList

/**
 * Implement this if you need parameters specific to each location
 * Use ConfigurableSource instead if you need all locations parameters
 */
interface PreferencesParametersSource : Source {

    /**
     * Must return true if the preferences screen is enabled for the given parameters
     *
     * Parameters:
     * - the location
     * - list of features requested. Empty if not specific to a feature
     */
    fun hasPreferencesScreen(
        location: Location,
        features: List<SourceFeature> = emptyList(),
    ): Boolean

    @Composable
    fun PerLocationPreferences(
        context: Context,
        location: Location,
        features: ImmutableList<SourceFeature>,
        onSave: (Map<String, String>) -> Unit,
    )
}
