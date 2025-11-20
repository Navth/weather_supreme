package org.breezyweather.sources.ims.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImsWarningsMetadata(
    @SerialName("ims_warning_type") val imsWarningType: Map<String, ImsWarningType>?,
    @SerialName("warning_severity") val warningSeverity: Map<String, ImsWarningSeverity>?,
)
