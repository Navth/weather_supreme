package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfWarningComments(
    @SerialName("bloc_title") val blocTitle: String?,
    @SerialName("text_bloc_item") val textBlocItems: List<MfWarningTextBlocItem>?,
)
