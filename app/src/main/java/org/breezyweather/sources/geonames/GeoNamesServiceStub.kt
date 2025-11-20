package org.breezyweather.sources.geonames

import breezyweather.domain.source.SourceContinent
import org.breezyweather.common.source.ConfigurableSource
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.LocationSearchSource
import org.breezyweather.common.source.NonFreeNetSource

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class GeoNamesServiceStub() :
    HttpSource(),
    LocationSearchSource,
    ConfigurableSource,
    NonFreeNetSource {

    override val id = "geonames"
    override val name = "GeoNames"
    override val continent = SourceContinent.WORLDWIDE
    override val privacyPolicyUrl = ""

    override val locationSearchAttribution = "GeoNames (CC BY 4.0)"

    // No known ambiguous code
    override val knownAmbiguousCountryCodes: Array<String> = emptyArray()
}
