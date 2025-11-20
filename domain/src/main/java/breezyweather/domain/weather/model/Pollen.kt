package breezyweather.domain.weather.model

import org.breezyweather.unit.pollen.PollenConcentration
import java.io.Serializable

/**
 * Pollen (tree, grass, weed) and mold
 */
class Pollen(
    val alder: PollenConcentration? = null,
    val ash: PollenConcentration? = null,
    val birch: PollenConcentration? = null,
    val chestnut: PollenConcentration? = null,
    val cypress: PollenConcentration? = null,
    val grass: PollenConcentration? = null,
    val hazel: PollenConcentration? = null,
    val hornbeam: PollenConcentration? = null,
    val linden: PollenConcentration? = null,
    val mold: PollenConcentration? = null, // Not a pollen, but probably relevant to put with them
    val mugwort: PollenConcentration? = null,
    val oak: PollenConcentration? = null,
    val olive: PollenConcentration? = null,
    val plane: PollenConcentration? = null,
    val plantain: PollenConcentration? = null,
    val poplar: PollenConcentration? = null,
    val ragweed: PollenConcentration? = null,
    val sorrel: PollenConcentration? = null,
    val tree: PollenConcentration? = null,
    val urticaceae: PollenConcentration? = null,
    val willow: PollenConcentration? = null,
) : Serializable {

    val isMoldValid: Boolean
        get() = mold != null

    val isValid: Boolean
        get() = alder != null ||
            ash != null ||
            birch != null ||
            chestnut != null ||
            cypress != null ||
            grass != null ||
            hazel != null ||
            hornbeam != null ||
            linden != null ||
            mold != null ||
            mugwort != null ||
            oak != null ||
            olive != null ||
            plane != null ||
            plantain != null ||
            poplar != null ||
            ragweed != null ||
            sorrel != null ||
            tree != null ||
            urticaceae != null ||
            willow != null
}
