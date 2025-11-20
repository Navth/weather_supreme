package org.breezyweather.sources.namem.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class NamemNormals(
    @SerialName("obs_date") @Serializable(DateSerializer::class) val obsDate: Date?,
    @SerialName("tt_min_ave") val ttMinAve: Double?,
    @SerialName("tt_max_ave") val ttMaxAve: Double?,
)
