package org.breezyweather.sources.wmosevereweather

import android.content.Context
import breezyweather.domain.source.SourceContinent
import breezyweather.domain.source.SourceFeature
import org.breezyweather.common.extensions.code
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.source.HttpSource
import org.breezyweather.common.source.NonFreeNetSource
import org.breezyweather.common.source.WeatherSource

/**
 * The actual implementation is in the src_freenet and src_nonfreenet folders
 */
abstract class WmoSevereWeatherServiceStub(context: Context) :
    HttpSource(),
    WeatherSource,
    NonFreeNetSource {

    override val id = "wmosevereweather"
    override val name by lazy {
        with(context.currentLocale.code) {
            when {
                // Missing arabic abbreviation for WMO
                startsWith("ar") -> "WMO Ù…Ø±ÙƒØ² Ù…Ø¹Ù„ÙˆÙ…Ø§Øª Ø§Ù„Ø·Ù‚Ø³ Ø§Ù„Ù‚Ø§Ø³ÙŠ"
                startsWith("eo") -> "MOM Severe Weather Information Centre"
                startsWith("es") -> "OMM Centro de InformaciÃ³n de Tiempo Severo"
                startsWith("fr") -> "OMM Centre dâ€™Information des PhÃ©nomÃ¨nes Dangereux"
                startsWith("it") -> "OMM Eventi Meteorologici Estremi"
                startsWith("ko") -> "WMO ìœ„í—˜ê¸°ìƒì •ë³´ì„¼í„°"
                startsWith("pl") -> "WMO Centrum Informacji o GroÅºnych Zjawiskach Pogodowych"
                startsWith("pt") -> "OMM Centro de InformaÃ§Ã£o Tempo Severo"
                startsWith("ru") -> "Ð’ÐœÐž Ð˜Ð½Ñ„Ð¾Ñ€Ð¼Ð°Ñ†Ð¸Ð¾Ð½Ð½Ñ‹Ð¹ Ñ†ÐµÐ½Ñ‚Ñ€ Ð½ÐµÐ±Ð»Ð°Ð³Ð¾Ð¿Ñ€Ð¸ÑÑ‚Ð½Ñ‹Ñ… Ð¿Ð¾Ð³Ð¾Ð´Ð½Ñ‹Ñ… ÑƒÑÐ»Ð¾Ð²Ð¸Ð¹"
                equals("zh-tw") || equals("zh-hk") || equals("zh-mo") -> "ä¸–ç•Œæ°£è±¡çµ„ç¹”æƒ¡åŠ£å¤©æ°£ä¿¡æ¯ä¸­å¿ƒ"
                startsWith("zh") -> "ä¸–ç•Œæ°”è±¡ç»„ç»‡æ¶åŠ£å¤©æ°”ä¿¡æ¯ä¸­å¿ƒ"
                else -> "WMO Severe Weather Information Centre"
            }
        }
    }
    override val continent = SourceContinent.WORLDWIDE

    override val supportedFeatures = mapOf(
        SourceFeature.ALERT to "Hong Kong Observatory on behalf of WMO + 141 issuing organizations"
    )
}
