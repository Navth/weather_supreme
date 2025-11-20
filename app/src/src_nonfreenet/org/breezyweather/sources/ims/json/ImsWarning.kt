package org.breezyweather.sources.ims.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImsWarning(
    @SerialName("alert_id") val alertId: String,
    @SerialName("severity_id") val severityId: String?,
    @SerialName("warning_type_id") val warningTypeId: String?,
    @SerialName("valid_from_unix") val validFromUnix: Int?,
    @SerialName("valid_to") val validTo: String?, // TimeZone dependant!
    @SerialName("text") val text: String?,
)
