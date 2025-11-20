package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfWarningTextBlocItem(
    @SerialName("type_name") val typeName: String?,
    @SerialName("text_items") val textItems: List<MfWarningTextItem>?,
)
