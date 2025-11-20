package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoWarningSummary(
    val Warning_Name: String?,
    val Warning_Action: String?,
    val Warning_Code: String?,
    val Warning_Type: String?,
    val Warning_Description: String?,
    val Bulletin_Date: String?,
    val Bulletin_Time: String?,
    val Expiry_Date: String?,
    val Expiry_Time: String?,
    val Issue_Date: String?,
    val Issue_Time: String?,
)
