package org.breezyweather.sources.atmo.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AtmoFrancePollenProperties(
    @SerialName("conc_ambr") val concAmbr: Double?,
    @SerialName("conc_arm") val concArm: Double?,
    @SerialName("conc_aul") val concAul: Double?,
    @SerialName("conc_boul") val concBoul: Double?,
    @SerialName("conc_gram") val concGram: Double?,
    @SerialName("conc_oliv") val concOliv: Double?,
)
