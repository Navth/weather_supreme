package org.breezyweather.sources.mf.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.StringOrStringListSerializer

@Serializable
data class MfWarningOverseasTextBlocItem(
    @Serializable(StringOrStringListSerializer::class) val title: List<String>?,
    @Serializable(StringOrStringListSerializer::class) val text: List<String>?,
)
