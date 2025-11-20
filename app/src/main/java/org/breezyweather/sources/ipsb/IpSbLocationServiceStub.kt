package org.breezyweather.sources.ipsb

import android.content.Context
import breezyweather.domain.source.SourceContinent
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.LocationSource
import org.breezyweather.common.source.NonFreeNetSource

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class IpSbLocationServiceStub() :
    HttpSource(),
    LocationSource,
    NonFreeNetSource {

    override val id = "ipsb"
    override val name = "IP.SB"
    override val continent = SourceContinent.WORLDWIDE

    override fun hasPermissions(context: Context) = true

    override val permissions: Array<String> = emptyArray()
}
