package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgIbfResponse(
    val public: List<BmkgIbfMessage>?,
)
