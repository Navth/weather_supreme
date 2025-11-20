package org.breezyweather.common.source

import android.content.Context
import android.os.Bundle
import breezyweather.domain.location.model.Location

/**
 * Broadcast services
 */
interface BroadcastSource : Source {

    // Make sure to also add it to the Manifest!
    val intentAction: String

    /**
     * Return null if anything happens and you no longer want to send any data
     */
    fun getExtras(
        context: Context,
        allLocations: List<Location>,
        updatedLocationIds: Array<String>?,
    ): Bundle?
}
