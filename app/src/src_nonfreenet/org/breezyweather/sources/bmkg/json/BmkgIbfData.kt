package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class BmkgIbfData(
    val id: String,
    val event: String?,
    val category: String?,
    val response: BmkgIbfResponse?,
    val effect: List<BmkgIbfMessage>?,
    @Serializable(DateSerializer::class) @SerialName("valid_for") val validFor: Date?,
)
