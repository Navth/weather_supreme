package org.breezyweather.sources.metno.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

/**
 * MET Norway when interval
 */
@Suppress("ktlint")
@Serializable
data class MetNoAlertWhen(
    val interval: List<@Serializable(DateSerializer::class) Date>?,
)
