package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoWarningResult(
    val DYN_DAT_MINDS_WTCPRE8: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WTCB: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WRAINSA: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WTMW: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WFNTSA: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WMNB: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WLSA: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WTS: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WFIRE: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WHOT: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WCOLD: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_WFROST: Map<String, Map<String, String>>? = null,
    val DYN_DAT_MINDS_MHEAD: Map<String, Map<String, String>>? = null,
)
