package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

/**
 * Mf warning overseas (old endpoint)
 */
@Serializable
data class MfWarningsOverseasResult(
    @Serializable(DateSerializer::class) @SerialName("update_time") val updateTime: Date? = null,
    @Serializable(DateSerializer::class) @SerialName("end_validity_time") val endValidityTime: Date? = null,
    @SerialName("color_max") val colorMax: Int? = null,
    val timelaps: List<MfWarningOverseasTimelaps>? = null,
    // @SerialName("phenomenons_items") val phenomenonsItems: List<MfWarningPhenomenonMaxColor>? = null,
    val advices: List<MfWarningOverseasAdvice>? = null,
    val consequences: List<MfWarningOverseasConsequence>? = null,
    // @SerialName("max_count_items") val maxCountItems: List<MfWarningMaxCountItems>? = null,
    val comments: MfWarningOverseasComments? = null,
    val text: MfWarningOverseasComments? = null,
    @SerialName("text_avalanche") val textAvalanche: MfWarningOverseasComments? = null,
)
