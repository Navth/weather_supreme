package org.breezyweather.sources.nlsc.xml

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import nl.adaptivity.xmlutil.serialization.XmlValue

@Serializable
@XmlSerialName("townVillageItem", "", "nlsc")
data class NlscLocationCodesResult(
    val countyCode: CountyCode? = null,
    val countyName: CountyName? = null,
    val townshipCode: TownshipCode? = null,
    val townshipName: TownshipName? = null,
    val villageCode: VillageCode? = null,
    val villageName: VillageName? = null,
) {
    @Serializable
    @XmlSerialName("ctyCode", "", "nlsc")
    data class CountyCode(
        @XmlValue(true) val value: String,
    )

    @Serializable
    @XmlSerialName("ctyName", "", "nlsc")
    data class CountyName(
        @XmlValue(true) val value: String,
    )

    @Serializable
    @XmlSerialName("townCode", "", "nlsc")
    data class TownshipCode(
        @XmlValue(true) val value: String,
    )

    @Serializable
    @XmlSerialName("townName", "", "nlsc")
    data class TownshipName(
        @XmlValue(true) val value: String,
    )

    @Serializable
    @XmlSerialName("villageCode", "", "nlsc")
    data class VillageCode(
        @XmlValue(true) val value: String,
    )

    @Serializable
    @XmlSerialName("villageName", "", "nlsc")
    data class VillageName(
        @XmlValue(true) val value: String,
    )
}
