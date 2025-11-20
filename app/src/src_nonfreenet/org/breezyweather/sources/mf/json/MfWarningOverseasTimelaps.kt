package org.breezyweather.sources.mf.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MfWarningOverseasTimelaps(
    @SerialName("phenomenon_id") val phenomenonId: Int,
    @SerialName("timelaps_items") val timelapsItems: List<MfWarningTimelapsItem>?,
)
