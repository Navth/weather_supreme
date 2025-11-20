package org.breezyweather.sources.aemet.json

import kotlinx.serialization.Serializable

@Serializable
data class AemetStationsResult(
    val latitud: String? = null,
    val longitud: String? = null,
    val indicativo: String? = null,
    val provincia: String? = null,
    val nombre: String? = null,
)
