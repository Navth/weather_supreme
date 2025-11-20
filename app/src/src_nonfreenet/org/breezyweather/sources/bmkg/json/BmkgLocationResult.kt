package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgLocationResult(
    val adm1: String?,
    val adm2: String?,
    val adm3: String?,
    val adm4: String?,
    val provinsi: String?,
    val kotkab: String?,
    val kecamatan: String?,
    val desa: String?,
    val distance: Double?,
)
