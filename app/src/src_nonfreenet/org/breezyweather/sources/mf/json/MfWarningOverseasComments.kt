package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class MfWarningOverseasComments(
    @Serializable(DateSerializer::class) @SerialName("begin_time") val beginTime: Date? = null,
    @Serializable(DateSerializer::class) @SerialName("end_time") val endTime: Date? = null,
    // TODO: Sometimes return a single string "pas de vigilance particuliÃ¨re", see VIGI973 for example
    @SerialName("text_bloc_item") val textBlocItems: List<MfWarningOverseasTextBlocItem>?,
)
