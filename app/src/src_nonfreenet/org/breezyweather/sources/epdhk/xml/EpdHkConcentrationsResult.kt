package org.breezyweather.sources.epdhk.xml

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("AQHI24HrPollutantConcentration", "", "epdhk")
data class EpdHkConcentrationsResult(
    val pollutantConcentration: List<PollutantConcentration>? = null,
) {
    @Serializable
    @XmlSerialName("PollutantConcentration", "", "epdhk")
    data class PollutantConcentration(
        val stationName: StationName,
        val dateTime: DateTime,
        val nO2: NO2? = null,
        val o3: O3? = null,
        val sO2: SO2? = null,
        val cO: CO? = null,
        val pM10: PM10? = null,
        val pM25: PM25? = null,
    ) {
        @Serializable
        @XmlSerialName("StationName", "", "epdhk")
        data class StationName(
            @XmlValue(true) val value: String,
        )

        @Serializable
        @XmlSerialName("DateTime", "", "epdhk")
        data class DateTime(
            @XmlValue(true) val value: String,
        )

        @Serializable
        @XmlSerialName("NO2", "", "epdhk")
        data class NO2(
            @XmlValue(true) val value: String? = null,
        )

        @Serializable
        @XmlSerialName("O3", "", "epdhk")
        data class O3(
            @XmlValue(true) val value: String? = null,
        )

        @Serializable
        @XmlSerialName("SO2", "", "epdhk")
        data class SO2(
            @XmlValue(true) val value: String? = null,
        )

        @Serializable
        @XmlSerialName("CO", "", "epdhk")
        data class CO(
            @XmlValue(true) val value: String? = null,
        )

        @Serializable
        @XmlSerialName("PM10", "", "epdhk")
        data class PM10(
            @XmlValue(true) val value: String? = null,
        )

        @Serializable
        @XmlSerialName("PM2.5", "", "epdhk")
        data class PM25(
            @XmlValue(true) val value: String? = null,
        )
    }
}
