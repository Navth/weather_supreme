package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgIbfMessage(
    val en: String?,
    val id: String?,
)
