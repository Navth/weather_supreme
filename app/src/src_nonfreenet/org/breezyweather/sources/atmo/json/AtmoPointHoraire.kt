package org.breezyweather.sources.atmo.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class AtmoPointHoraire(
    @SerialName("datetime_echeance") @Serializable(DateSerializer::class) val datetimeEcheance: Date,
    val concentration: Double?,
)
