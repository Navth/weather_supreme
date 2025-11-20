package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgBulletinRoot(
    val Custom: List<SmgBulletinCustom>?,
)
