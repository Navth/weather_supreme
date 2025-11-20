package org.breezyweather.common.source

/**
 * Interface for sources providing pollen data expressed in a scale or level way
 * (for example 0 to 5)
 */
interface PollenIndexSource : Source {

    /**
     * Array containing 0 to max level non-translatable labels
     * If a data exceed max level, it will fallback to last item
     */
    val pollenLabels: Int

    /**
     * Array containing 0 to max level colors
     * If a data exceed max level, it will fallback to last item
     */
    val pollenColors: Int
}
