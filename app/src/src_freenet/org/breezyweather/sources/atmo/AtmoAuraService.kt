package org.breezyweather.sources.atmo

import android.content.Context
import breezyweather.domain.location.model.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCountryName
import javax.inject.Inject

/**
 * ATMO Auvergne-RhÃ´ne-Alpes air quality service.
 */
class AtmoAuraService @Inject constructor(
    @ApplicationContext injectedContext: Context,
) : AtmoService() {

    override val id = "atmoaura"
    override val name = "Atmo Auvergne-RhÃ´ne-Alpes (${injectedContext.currentLocale.getCountryName("FR")})"
    override val attribution = "Atmo Auvergne-RhÃ´ne-Alpes"

    override fun isLocationInRegion(location: Location): Boolean {
        return location.admin1 in arrayOf(
            "Auvergne-RhÃ´ne-Alpes",
            "Auvergne-Rhone-Alpes",
            "Auvergne RhÃ´ne Alpes",
            "Auvergne Rhone Alpes"
        ) ||
            location.admin1Code in arrayOf("FR-ARA", "ARA", "84") ||
            location.admin2 in arrayOf(
                "Ain", // 01
                "Allier", // 03
                "ArdÃ¨che", // 07
                "Ardeche", // 07
                "Cantal", // 15
                "DrÃ´me", // 26
                "Drome", // 26
                "IsÃ¨re", // 38
                "Isere", // 38
                "Loire", // 42
                "Haute Loire", // 43
                "Haute-Loire", // 43
                "MÃ©tropole de Lyon", // 69M
                "Puy-de-DÃ´me", // 63
                "Puy-de-Dome", // 63
                "Puy de DÃ´me", // 63
                "Puy de Dome", // 63
                "RhÃ´ne", // 69
                "Rhone", // 69
                "Savoie", // 73
                "Haute-Savoie", // 74
                "Haute Savoie" // 74
            ) ||
            arrayOf(
                "01", // Ain
                "03", // Allier
                "07", // ArdÃ¨che
                "15", // Cantal
                "26", // DrÃ´me
                "38", // IsÃ¨re
                "42", // Loire
                "43", // Haute-Loire
                "63", // Puy-de-DÃ´me
                "69", // RhÃ´ne
                "69M", // MÃ©tropole de Lyon
                "73", // Savoie
                "74" // Haute-Savoie
            ).any { location.admin2Code?.endsWith(it, ignoreCase = true) == true }
    }
}
