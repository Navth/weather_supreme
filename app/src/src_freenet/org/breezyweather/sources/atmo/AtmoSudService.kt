package org.breezyweather.sources.atmo

import android.content.Context
import breezyweather.domain.location.model.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import javax.inject.Inject

/**
 * AtmoSud air quality service.
 */
class AtmoSudService @Inject constructor(
    @ApplicationContext injectedContext: Context,
) : AtmoService() {

    override val id = "atmosud"
    override val name = "AtmoSud (${injectedContext.currentLocale.getCountryName("FR")})"
    override val attribution = "AtmoSud"

    override fun isLocationInRegion(location: Location): Boolean {
        return location.admin1 in arrayOf(
            "Provence-Alpes-CÃ´te d'Azur",
            "Provence-Alpes-CÃ´te dâ€™Azur",
            "Provence-Alpes-Cote d'Azur",
            "Provence-Alpes-Cote dâ€™Azur",
            "Provence Alpes CÃ´te d'Azur",
            "Provence Alpes CÃ´te dâ€™Azur",
            "Provence Alpes Cote d'Azur",
            "Provence Alpes Cote dâ€™Azur",
            "Sud Provence-Alpes-CÃ´te d'Azur",
            "Sud Provence-Alpes-CÃ´te dâ€™Azur",
            "Sud Provence-Alpes-Cote d'Azur",
            "Sud Provence-Alpes-Cote dâ€™Azur",
            "Sud Provence Alpes CÃ´te d'Azur",
            "Sud Provence Alpes CÃ´te dâ€™Azur",
            "Sud Provence Alpes Cote d'Azur",
            "Sud Provence Alpes Cote dâ€™Azur"
        ) ||
            location.admin1Code in arrayOf("FR-PAC", "PAC", "93") ||
            location.admin2 in arrayOf(
                "Alpes-de-Haute Provence", // 04
                "Alpes de Haute Provence", // 04
                "Hautes-Alpes", // 05
                "Hautes Alpes", // 05
                "Alpes-Maritimes", // 06
                "Alpes Maritimes", // 06
                "Bouches-du-RhÃ´ne", // 13
                "Bouches-du-Rhone", // 13
                "Bouches du RhÃ´ne", // 13
                "Bouches du Rhone", // 13
                "Var", // 83
                "Vaucluse" // 84
            ) ||
            arrayOf(
                "04", // Alpes-de-Haute Provence
                "05", // Hautes-Alpes
                "06", // Alpes-Maritimes
                "13", // Bouches du RhÃ´ne
                "83", // Var
                "84" // Vaucluse
            ).any { location.admin2Code?.endsWith(it) == true }
    }
}
