package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

/**
 * Mf warning current phenomenons
 */
@Serializable
data class MfWarningsResult(
    @Serializable(DateSerializer::class) @SerialName("update_time") val updateTime: Date? = null,
    @Serializable(DateSerializer::class) @SerialName("end_validity_time") val endValidityTime: Date? = null,
    val timelaps: List<MfWarningTimelaps>? = null,
    // @SerialName("phenomenons_items") val phenomenonsItems: List<MfWarningPhenomenonMaxColor>? = null,
    val advices: List<MfWarningAdvice>? = null,
    val consequences: List<MfWarningConsequence>? = null,
    // @SerialName("max_count_items") val maxCountItems: List<MfWarningMaxCountItems>? = null,
    val comments: MfWarningComments? = null,
    val text: MfWarningComments? = null,
    @SerialName("text_avalanche") val textAvalanche: MfWarningComments? = null,
)
