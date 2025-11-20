package org.breezyweather.common.source

import breezyweather.domain.source.SourceContinent

/**
 * TODO: We should inject Retrofit.Builder here, however I still havenâ€™t figure out how to do it yet
 */
abstract class HttpSource : Source {

    /**
     * Privacy policy of the website, like: https://mysite.com/privacy
     */
    abstract val privacyPolicyUrl: String

    /**
     * Add a link each time a string appears in an attribution
     * Example: <"Open-Meteo", "https://open-meteo.com/">
     */
    open val attributionLinks: Map<String, String> = emptyMap()

    /**
     * The continent the source is mainly based of
     *
     * Worldwide sources will use `SourceContinent.WORLDWIDE`
     * National sources even if supporting worldwide will use the continent their mainland is based on
     * E.g. MÃ©tÃ©o-France will use `SourceContinent.EUROPE` even if it supports oversea territories on other continents
     * E.g. TÃ¼rkiye will use `SourceContinent.ASIA` even if 10% of its territory is technically in Europe
     */
    abstract val continent: SourceContinent
}
