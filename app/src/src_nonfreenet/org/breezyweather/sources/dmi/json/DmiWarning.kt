package org.breezyweather.sources.dmi.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class DmiWarning(
    @Serializable(DateSerializer::class) val validFrom: Date?,
    @Serializable(DateSerializer::class) val validTo: Date?,
    val warningTitle: String?,
    val warningText: String?,
    val additionalText: String?,
    val formattedCategory: Int?, // Severity
)
