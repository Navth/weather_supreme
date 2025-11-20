package org.breezyweather.sources.polleninfo.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollenInfoResult(
    val contamination: List<PollenContamination>?,
)

@Serializable
data class PollenContamination(
    @SerialName("poll_id") val pollId: Int,
    @SerialName("poll_title") val pollTitle: String,
    @SerialName("contamination_1") val contamination1: Int,
    @SerialName("contamination_2") val contamination2: Int,
    @SerialName("contamination_3") val contamination3: Int,
    @SerialName("contamination_4") val contamination4: Int,
) {
    fun getContaminationForDay(day: Int): Int? =
        when (day) {
            1 -> contamination1
            2 -> contamination2
            3 -> contamination3
            4 -> contamination4
            else -> null
        }
}
