package org.breezyweather.sources.jma

import android.content.Context
import android.graphics.Color
import breezyweather.domain.location.model.Location
import breezyweather.domain.location.model.LocationAddressInfo
import breezyweather.domain.source.SourceFeature
import breezyweather.domain.weather.model.Alert
import breezyweather.domain.weather.model.Normals
import breezyweather.domain.weather.model.PrecipitationProbability
import breezyweather.domain.weather.model.Wind
import breezyweather.domain.weather.reference.AlertSeverity
import breezyweather.domain.weather.reference.WeatherCode
import breezyweather.domain.weather.wrappers.CurrentWrapper
import breezyweather.domain.weather.wrappers.DailyWrapper
import breezyweather.domain.weather.wrappers.HalfDayWrapper
import breezyweather.domain.weather.wrappers.HourlyWrapper
import breezyweather.domain.weather.wrappers.TemperatureWrapper
import breezyweather.domain.weather.wrappers.WeatherWrapper
import com.google.maps.android.PolyUtil
import com.google.maps.android.SphericalUtil
import com.google.maps.android.data.geojson.GeoJsonFeature
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon
import com.google.maps.android.data.geojson.GeoJsonParser
import com.google.maps.android.data.geojson.GeoJsonPolygon
import com.google.maps.android.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import org.breezyweather.R
import org.breezyweather.common.exceptions.InvalidLocationException
import org.breezyweather.common.exceptions.WeatherException
import org.breezyweather.common.extensions.code
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.common.extensions.getCalendarMonth
import org.breezyweather.sources.jma.json.JmaAlertResult
import org.breezyweather.sources.jma.json.JmaAmedasResult
import org.breezyweather.sources.jma.json.JmaAreasResult
import org.breezyweather.sources.jma.json.JmaBulletinResult
import org.breezyweather.sources.jma.json.JmaCurrentResult
import org.breezyweather.sources.jma.json.JmaDailyResult
import org.breezyweather.sources.jma.json.JmaForecastAreaResult
import org.breezyweather.sources.jma.json.JmaHourlyResult
import org.breezyweather.sources.jma.json.JmaWeekAreaResult
import org.breezyweather.unit.distance.Distance.Companion.meters
import org.breezyweather.unit.pressure.Pressure.Companion.hectopascals
import org.breezyweather.unit.ratio.Ratio.Companion.percent
import org.breezyweather.unit.speed.Speed.Companion.metersPerSecond
import org.breezyweather.unit.temperature.Temperature.Companion.celsius
import org.json.JSONObject
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.floor
import kotlin.math.max
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

class JmaService @Inject constructor(
    @ApplicationContext context: Context,
    @Named("JsonClient") client: Retrofit.Builder,
    private val okHttpClient: OkHttpClient,
) : JmaServiceStub(context) {

    override val privacyPolicyUrl by lazy {
        if (context.currentLocale.code.startsWith("ja")) {
            "https://www.jma.go.jp/jma/kishou/info/coment.html"
        } else {
            "https://www.jma.go.jp/jma/en/copyright.html"
        }
    }

    private val mApi by lazy {
        client
            .baseUrl(JMA_BASE_URL)
            .build()
            .create(JmaApi::class.java)
    }

    override val attributionLinks = mapOf(
        weatherAttribution to "https://www.jma.go.jp/"
    )

    override fun requestWeather(
        context: Context,
        location: Location,
        requestedFeatures: List<SourceFeature>,
    ): Observable<WeatherWrapper> {
        val parameters = location.parameters.getOrElse(id) { null }
        val class20s = parameters?.getOrElse("class20s") { null }
        val class10s = parameters?.getOrElse("class10s") { null }
        val prefArea = parameters?.getOrElse("prefArea") { null }
        val weekArea05 = parameters?.getOrElse("weekArea05") { null }
        val weekAreaAmedas = parameters?.getOrElse("weekAreaAmedas") { null }
        val forecastAmedas = parameters?.getOrElse("forecastAmedas") { null }
        val currentAmedas = parameters?.getOrElse("currentAmedas") { null }

        if (class20s.isNullOrEmpty() ||
            class10s.isNullOrEmpty() ||
            prefArea.isNullOrEmpty() ||
            weekArea05.isNullOrEmpty() ||
            weekAreaAmedas.isNullOrEmpty() ||
            forecastAmedas.isNullOrEmpty() ||
            currentAmedas.isNullOrEmpty()
        ) {
            return Observable.error(InvalidLocationException())
        }

        // Special case for Amami, Kagoshima Prefecture
        val forecastPrefArea = if (prefArea == "460040") {
            "460100"
        } else {
            prefArea
        }

        val failedFeatures = mutableMapOf<SourceFeature, Throwable>()
        val daily = if (SourceFeature.FORECAST in requestedFeatures) {
            mApi.getDaily(forecastPrefArea).onErrorResumeNext {
                failedFeatures[SourceFeature.FORECAST] = it
                Observable.just(emptyList())
            }
        } else {
            Observable.just(emptyList())
        }
        val hourly = if (SourceFeature.FORECAST in requestedFeatures) {
            mApi.getHourly(class10s).onErrorResumeNext {
                failedFeatures[SourceFeature.FORECAST] = it
                Observable.just(JmaHourlyResult())
            }
        } else {
            Observable.just(JmaHourlyResult())
        }

        // CURRENT
        // Need to first get the correct timestamp for latest observation data.
        val current = if (SourceFeature.CURRENT in requestedFeatures) {
            val request = Request.Builder().url(JMA_BASE_URL + "bosai/amedas/data/latest_time.txt").build()
            val incomingFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
            val outgoingFormatter = SimpleDateFormat("yyyyMMdd_HH", Locale.ENGLISH)
            incomingFormatter.timeZone = TimeZone.getTimeZone("Asia/Tokyo")
            outgoingFormatter.timeZone = TimeZone.getTimeZone("Asia/Tokyo")

            okHttpClient.newCall(request).execute().use { call ->
                if (call.isSuccessful) {
                    val latestTime = incomingFormatter.parse(call.body.string())!!.time

                    // Observation data is recorded in 3-hourly files.
                    val timestamp = (
                        floor(latestTime.toDouble() / 3.hours.inWholeMilliseconds) *
                            3.hours.inWholeMilliseconds
                        ).toLong()
                    mApi.getCurrent(
                        amedas = currentAmedas,
                        timestamp = outgoingFormatter.format(timestamp)
                    ).onErrorResumeNext {
                        failedFeatures[SourceFeature.CURRENT] = it
                        Observable.just(emptyMap())
                    }
                } else {
                    failedFeatures[SourceFeature.CURRENT] = WeatherException()
                    Observable.just(emptyMap())
                }
            }
        } else {
            Observable.just(emptyMap())
        }

        val bulletin = if (SourceFeature.CURRENT in requestedFeatures) {
            mApi.getBulletin(forecastPrefArea).onErrorResumeNext {
                failedFeatures[SourceFeature.CURRENT] = it
                Observable.just(JmaBulletinResult())
            }
        } else {
            Observable.just(JmaBulletinResult())
        }

        // ALERT
        val alert = if (SourceFeature.ALERT in requestedFeatures) {
            mApi.getAlert(prefArea).onErrorResumeNext {
                failedFeatures[SourceFeature.ALERT] = it
                Observable.just(JmaAlertResult())
            }
        } else {
            Observable.just(JmaAlertResult())
        }

        return Observable.zip(current, bulletin, daily, hourly, alert) {
                currentResult: Map<String, JmaCurrentResult>,
                bulletinResult: JmaBulletinResult,
                dailyResult: List<JmaDailyResult>,
                hourlyResult: JmaHourlyResult,
                alertResult: JmaAlertResult,
            ->
            WeatherWrapper(
                dailyForecast = if (SourceFeature.FORECAST in requestedFeatures) {
                    getDailyForecast(context, dailyResult, class10s, weekArea05, weekAreaAmedas, forecastAmedas)
                } else {
                    null
                },
                hourlyForecast = if (SourceFeature.FORECAST in requestedFeatures) {
                    getHourlyForecast(context, hourlyResult)
                } else {
                    null
                },
                current = if (SourceFeature.CURRENT in requestedFeatures) {
                    getCurrent(context, currentResult, bulletinResult)
                } else {
                    null
                },
                alertList = if (SourceFeature.ALERT in requestedFeatures) {
                    getAlertList(context, alertResult, class20s)
                } else {
                    null
                },
                normals = if (SourceFeature.NORMALS in requestedFeatures) {
                    getNormals(dailyResult, weekAreaAmedas)?.let { normals ->
                        mapOf(
                            Date().getCalendarMonth(location) to normals
                        )
                    }
                } else {
                    null
                },
                failedFeatures = failedFeatures
            )
        }
    }

    private fun getCurrent(
        context: Context,
        currentResult: Map<String, JmaCurrentResult>,
        bulletinResult: JmaBulletinResult,
    ): CurrentWrapper? {
        val lastKey = currentResult.keys.sortedDescending()[0]
        val lastHourKey = lastKey.substring(0, 10) + "0000"
        var dailyForecast = bulletinResult.text?.trim()
        if ((dailyForecast ?: "").startsWith("ã€")) {
            dailyForecast = dailyForecast?.substringAfter("ã€‘")?.trim()
        }
        dailyForecast = dailyForecast?.substringBefore("\n")?.trim()
        return currentResult[lastKey]?.let {
            val weather = currentResult.getOrElse(lastHourKey) { null }?.weather?.getOrNull(0)
            CurrentWrapper(
                weatherText = getCurrentWeatherText(context, weather),
                weatherCode = getCurrentWeatherCode(weather),
                temperature = TemperatureWrapper(
                    temperature = it.temp?.getOrNull(0)?.celsius
                ),
                wind = Wind(
                    degree = getWindDirection(it.windDirection?.getOrNull(0)),
                    speed = it.wind?.getOrNull(0)?.metersPerSecond
                ),
                relativeHumidity = it.humidity?.getOrNull(0)?.percent,
                pressure = it.normalPressure?.getOrNull(0)?.hectopascals,
                visibility = it.visibility?.getOrNull(0)?.meters,
                dailyForecast = dailyForecast
            )
        }
    }

    private fun getDailyForecast(
        context: Context,
        dailyResult: List<JmaDailyResult>,
        class10s: String,
        weekArea05: String,
        weekAreaAmedas: String,
        forecastAmedas: String,
    ): List<DailyWrapper> {
        val formatter = SimpleDateFormat("HH", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("Asia/Tokyo")

        val wxMap = mutableMapOf<Long, String?>()
        val maxTMap = mutableMapOf<Long, Double?>()
        val minTMap = mutableMapOf<Long, Double?>()
        val popMap = mutableMapOf<Long, Double>()

        // 7-day weather conditions and daily probabilities of precipitation
        dailyResult.getOrNull(1)?.timeSeries?.getOrNull(0)?.let { series ->
            series.areas
                ?.filter { it.area.code == weekArea05 }
                ?.forEach { area ->
                    series.timeDefines?.forEachIndexed { i, timeDefines ->
                        timeDefines.time.let {
                            wxMap[it] = area.weatherCodes?.getOrNull(i)
                            val pop = area.pops?.getOrNull(i)?.toDoubleOrNull()
                            // fill up popMap from 6am local time rather than midnight,
                            // otherwise the midnight-to-6am PoP will be duplicated in daily charts
                            popMap[it + 6.hours.inWholeMilliseconds] = pop ?: 0.0
                            popMap[it + 12.hours.inWholeMilliseconds] = pop ?: 0.0
                            popMap[it + 18.hours.inWholeMilliseconds] = pop ?: 0.0
                            popMap[it + 24.hours.inWholeMilliseconds] = pop ?: 0.0
                        }
                    }
                }
        }

        // 7-day max and min temperatures
        dailyResult.getOrNull(1)?.timeSeries?.getOrNull(1)?.let { series ->
            series.areas
                ?.filter { it.area.code == weekAreaAmedas }
                ?.forEach { area ->
                    series.timeDefines?.forEachIndexed { i, timeDefines ->
                        timeDefines.time.let {
                            minTMap[it] = area.tempsMin?.getOrNull(i)?.toDoubleOrNull()
                            maxTMap[it] = area.tempsMax?.getOrNull(i)?.toDoubleOrNull()
                        }
                    }
                }
        }

        // 3-day weather conditions
        dailyResult.getOrNull(0)?.timeSeries?.getOrNull(0)?.let { series ->
            series.areas
                ?.filter { it.area.code == class10s }
                ?.forEach { area ->
                    series.timeDefines?.forEachIndexed { i, timeDefines ->
                        timeDefines.time.let {
                            // normalize timestamp to midnight local time
                            val midnight = (
                                floor((it.toDouble() + 9.hours.inWholeMilliseconds) / 1.days.inWholeMilliseconds) *
                                    1.days.inWholeMilliseconds -
                                    9.hours.inWholeMilliseconds
                                ).toLong()
                            wxMap[midnight] = area.weatherCodes?.getOrNull(i)
                        }
                    }
                }
        }

        // 3-day 6-hourly probabilities of precipitation
        dailyResult.getOrNull(0)?.timeSeries?.getOrNull(1)?.let { series ->
            series.areas
                ?.filter { it.area.code == class10s }
                ?.forEach { area ->
                    series.timeDefines?.forEachIndexed { i, timeDefines ->
                        timeDefines.time.let {
                            popMap[it] = area.pops?.getOrNull(i)?.toDoubleOrNull() ?: 0.0
                        }
                    }
                }
        }

        // 3-day max and min temperatures
        val forecastAmedasCodes = forecastAmedas.split(",")
        dailyResult.getOrNull(0)?.timeSeries?.getOrNull(2)?.let { series ->
            series.areas?.forEach { area ->
                forecastAmedasCodes
                    .filter { area.area.code == it }
                    .forEach { code ->
                        series.timeDefines?.forEachIndexed { i, timeDefines ->
                            timeDefines.time.let {
                                val hour = formatter.format(Date(it))
                                if (hour == "00") {
                                    minTMap[it] = area.temps?.getOrNull(i)?.toDoubleOrNull()
                                }
                                if (hour == "09") {
                                    maxTMap[it - 9.hours.inWholeMilliseconds] =
                                        area.temps?.getOrNull(i)?.toDoubleOrNull()
                                }
                            }
                        }
                    }
            }
        }

        return minTMap.keys.sorted().map { key ->
            DailyWrapper(
                date = Date(key),
                day = HalfDayWrapper(
                    weatherText = getDailyWeatherText(
                        context = context,
                        weather = wxMap.getOrElse(key) { null },
                        night = false
                    ),
                    weatherCode = getDailyWeatherCode(
                        weather = wxMap.getOrElse(key) { null },
                        night = false
                    ),
                    temperature = TemperatureWrapper(
                        temperature = maxTMap.getOrElse(key) { null }?.celsius
                    ),
                    precipitationProbability = if (popMap.containsKey(key + 6.hours.inWholeMilliseconds) ||
                        popMap.containsKey(key + 12.hours.inWholeMilliseconds)
                    ) {
                        PrecipitationProbability(
                            total = max(
                                popMap.getOrElse(key + 6.hours.inWholeMilliseconds) { 0.0 },
                                popMap.getOrElse(key + 12.hours.inWholeMilliseconds) { 0.0 }
                            ).percent
                        )
                    } else {
                        null
                    }
                ),
                night = HalfDayWrapper(
                    weatherText = getDailyWeatherText(
                        context = context,
                        weather = wxMap.getOrElse(key) { null },
                        night = true
                    ),
                    weatherCode = getDailyWeatherCode(
                        weather = wxMap.getOrElse(key) { null },
                        night = true
                    ),
                    temperature = TemperatureWrapper(
                        temperature = minTMap.getOrElse(key + 1.days.inWholeMilliseconds) { null }?.celsius
                    ),
                    precipitationProbability = if (popMap.containsKey(key + 6.hours.inWholeMilliseconds) ||
                        popMap.containsKey(key + 12.hours.inWholeMilliseconds)
                    ) {
                        PrecipitationProbability(
                            total = max(
                                popMap.getOrElse(key + 6.hours.inWholeMilliseconds) { 0.0 },
                                popMap.getOrElse(key + 12.hours.inWholeMilliseconds) { 0.0 }
                            ).percent
                        )
                    } else {
                        null
                    }
                )
            )
        }
    }

    private fun getHourlyForecast(
        context: Context,
        hourlyResult: JmaHourlyResult,
    ): List<HourlyWrapper> {
        val wxTextMap = mutableMapOf<Long, String?>()
        val wxCodeMap = mutableMapOf<Long, WeatherCode?>()
        val tMap = mutableMapOf<Long, Double?>()
        val wdMap = mutableMapOf<Long, Double?>()
        val wsMap = mutableMapOf<Long, Double?>()
        hourlyResult.areaTimeSeries?.let { timeSeries ->
            timeSeries.timeDefines?.forEachIndexed { i, timeDefines ->
                timeDefines?.dateTime?.time?.let {
                    wxTextMap[it] = getHourlyWeatherText(context, timeSeries.weather?.getOrNull(i))
                    wxCodeMap[it] = getHourlyWeatherCode(timeSeries.weather?.getOrNull(i))
                    wdMap[it] = getWindDirection(timeSeries.wind?.getOrNull(i)?.direction)
                    wsMap[it] = timeSeries.wind?.getOrNull(i)?.range?.substringAfterLast(" ")?.toDoubleOrNull()
                }
            }
        }
        hourlyResult.pointTimeSeries?.let { timeSeries ->
            timeSeries.timeDefines?.forEachIndexed { i, timeDefines ->
                timeDefines?.dateTime?.time?.let {
                    tMap[it] = timeSeries.temperature?.getOrNull(i)
                }
            }
        }

        return wxTextMap.keys.sorted().map { key ->
            HourlyWrapper(
                date = Date(key),
                weatherText = wxTextMap.getOrElse(key) { null },
                weatherCode = wxCodeMap.getOrElse(key) { null },
                temperature = TemperatureWrapper(
                    temperature = tMap.getOrElse(key) { null }?.celsius
                ),
                wind = Wind(
                    degree = wdMap.getOrElse(key) { null },
                    speed = wsMap.getOrElse(key) { null }?.metersPerSecond
                )
            )
        }
    }

    private fun getNormals(
        dailyResult: List<JmaDailyResult>,
        weekAreaAmedas: String,
    ): Normals? {
        dailyResult.getOrNull(1)?.tempAverage?.areas?.forEach { area ->
            if (area.area.code == weekAreaAmedas) {
                return Normals(
                    daytimeTemperature = area.max?.toDoubleOrNull()?.celsius,
                    nighttimeTemperature = area.min?.toDoubleOrNull()?.celsius
                )
            }
        }
        return null
    }

    private fun getAlertList(
        context: Context,
        alertResult: JmaAlertResult,
        class20s: String,
    ): List<Alert> {
        val alertList = mutableListOf<Alert>()
        var severity: AlertSeverity
        alertResult.areaTypes?.getOrNull(1)?.areas?.forEach { area ->
            if (area.code == class20s) {
                area.warnings?.forEach { warning ->
                    if (warning.status != "ç™ºè¡¨è­¦å ±ãƒ»æ³¨æ„å ±ã¯ãªã—" && warning.status != "è§£é™¤") {
                        severity = getAlertSeverity(warning.code)
                        alertList.add(
                            Alert(
                                alertId = "${warning.code} ${alertResult.reportDatetime?.time}",
                                startDate = alertResult.reportDatetime,
                                headline = getAlertHeadline(context, warning.code),
                                description = alertResult.headlineText?.trim(),
                                source = alertResult.publishingOffice?.trim(),
                                severity = severity,
                                color = getAlertColor(severity)
                            )
                        )
                    }
                }
            }
        }
        return alertList
    }

    private fun getWindDirection(
        direction: Any?,
    ): Double? {
        return when (direction) {
            0 -> -1.0
            1 -> 22.5
            2, "åŒ—æ±" -> 45.0
            3 -> 67.5
            4, "æ±" -> 90.0
            5 -> 112.5
            6, "å—æ±" -> 135.0
            7 -> 157.5
            8, "å—" -> 180.0
            9 -> 202.5
            10, "å—è¥¿" -> 225.0
            11 -> 247.5
            12, "è¥¿" -> 270.0
            13 -> 292.5
            14, "åŒ—è¥¿" -> 315.0
            15 -> 337.5
            16, "åŒ—" -> 0.0
            else -> null
        }
    }

    private fun getHourlyWeatherText(
        context: Context,
        weather: String?,
    ): String? {
        return when (weather) {
            "æ™´ã‚Œ" -> context.getString(R.string.common_weather_text_clear_sky)
            "ãã‚‚ã‚Š" -> context.getString(R.string.common_weather_text_cloudy)
            "é›¨" -> context.getString(R.string.common_weather_text_rain)
            "é›ª" -> context.getString(R.string.common_weather_text_snow)
            "é›¨ã¾ãŸã¯é›ª" -> context.getString(R.string.common_weather_text_rain_snow_mixed)
            else -> null
        }
    }

    private fun getHourlyWeatherCode(
        weather: String?,
    ): WeatherCode? {
        return when (weather) {
            "æ™´ã‚Œ" -> WeatherCode.CLEAR
            "ãã‚‚ã‚Š" -> WeatherCode.CLOUDY
            "é›¨" -> WeatherCode.RAIN
            "é›ª" -> WeatherCode.SNOW
            "é›¨ã¾ãŸã¯é›ª" -> WeatherCode.SLEET
            else -> null
        }
    }

    // There are 118 daily weather codes used by JMA.
    // They have been listed in its own file at JmaConstants.kt.
    private fun getDailyWeatherText(
        context: Context,
        weather: String?,
        night: Boolean = false,
    ): String? {
        val i = if (night) {
            1
        } else {
            0
        }
        val stringId = if (JMA_DAILY_WEATHER_TEXTS.containsKey(weather)) {
            JMA_DAILY_WEATHER_TEXTS[weather]!!.getOrNull(i)
        } else {
            null
        }
        return stringId?.let { context.getString(it) }
    }

    // There are 118 daily weather codes used by JMA.
    // They have been listed in its own file at JmaConstants.kt.
    private fun getDailyWeatherCode(
        weather: String?,
        night: Boolean = false,
    ): WeatherCode? {
        val i = if (night) {
            1
        } else {
            0
        }
        return if (JMA_DAILY_WEATHER_CODES.containsKey(weather)) {
            JMA_DAILY_WEATHER_CODES[weather]!!.getOrNull(i)
        } else {
            null
        }
    }

    private fun getCurrentWeatherText(
        context: Context,
        weather: Int?,
    ): String? {
        return when (weather) {
            0 -> context.getString(R.string.common_weather_text_clear_sky) // æ™´
            1 -> context.getString(R.string.common_weather_text_cloudy) // æ›‡
            2 -> context.getString(R.string.weather_kind_haze) // ç…™éœ§
            3 -> context.getString(R.string.common_weather_text_fog) // éœ§
            4 -> context.getString(R.string.common_weather_text_rain) // é™æ°´ã¾ãŸã¯ã—ã‚…ã†é›¨æ€§ã®é™æ°´
            5 -> context.getString(R.string.common_weather_text_drizzle) // éœ§é›¨
            6 -> context.getString(R.string.common_weather_text_drizzle_freezing) // ç€æ°·æ€§ã®éœ§é›¨
            7 -> context.getString(R.string.common_weather_text_rain) // é›¨
            8 -> context.getString(R.string.common_weather_text_rain_freezing) // ç€æ°·æ€§ã®é›¨
            9 -> context.getString(R.string.common_weather_text_rain_snow_mixed) // ã¿ãžã‚Œ
            10 -> context.getString(R.string.common_weather_text_snow) // é›ª
            11 -> context.getString(R.string.common_weather_text_rain_freezing) // å‡é›¨
            12 -> context.getString(R.string.common_weather_text_snow_grains) // éœ§é›ª
            13 -> context.getString(R.string.common_weather_text_rain_showers) // ã—ã‚…ã†é›¨ã¾ãŸã¯æ­¢ã¿é–“ã®ã‚ã‚‹é›¨
            14 -> context.getString(R.string.common_weather_text_snow_showers) // ã—ã‚…ã†é›ªã¾ãŸã¯æ­¢ã¿é–“ã®ã‚ã‚‹é›ª
            15 -> context.getString(R.string.weather_kind_hail) // ã²ã‚‡ã†
            16 -> context.getString(R.string.weather_kind_thunderstorm) // é›·
            else -> null
        }
    }

    private fun getCurrentWeatherCode(
        weather: Int?,
    ): WeatherCode? {
        return when (weather) {
            0 -> WeatherCode.CLEAR
            1 -> WeatherCode.CLOUDY
            2 -> WeatherCode.HAZE
            3 -> WeatherCode.FOG
            4, 5, 7, 13 -> WeatherCode.RAIN
            6, 8, 9, 11 -> WeatherCode.SLEET
            10, 12, 14 -> WeatherCode.SNOW
            15 -> WeatherCode.HAIL
            16 -> WeatherCode.THUNDERSTORM
            else -> null
        }
    }

    private fun getAlertHeadline(
        context: Context,
        code: String?,
    ): String? {
        return when (code) {
            "33" -> context.getString(R.string.jma_warning_text_heavy_rain_emergency) // å¤§é›¨ç‰¹åˆ¥è­¦å ±
            "03" -> context.getString(R.string.jma_warning_text_heavy_rain_warning) // å¤§é›¨è­¦å ±
            "10" -> context.getString(R.string.jma_warning_text_heavy_rain_advisory) // å¤§é›¨æ³¨æ„å ±
            "04" -> context.getString(R.string.jma_warning_text_flood_warning) // æ´ªæ°´è­¦å ±
            "18" -> context.getString(R.string.jma_warning_text_flood_advisory) // æ´ªæ°´æ³¨æ„å ±
            "35" -> context.getString(R.string.jma_warning_text_storm_emergency) // æš´é¢¨ç‰¹åˆ¥è­¦å ±
            "05" -> context.getString(R.string.jma_warning_text_storm_warning) // æš´é¢¨è­¦å ±
            "15" -> context.getString(R.string.jma_warning_text_gale_advisory) // å¼·é¢¨æ³¨æ„å ±
            "32" -> context.getString(R.string.jma_warning_text_snowstorm_emergency) // æš´é¢¨é›ªç‰¹åˆ¥è­¦å ±
            "02" -> context.getString(R.string.jma_warning_text_snowstorm_warning) // æš´é¢¨é›ªè­¦å ±
            "13" -> context.getString(R.string.jma_warning_text_gale_and_snow_advisory) // é¢¨é›ªæ³¨æ„å ±
            "36" -> context.getString(R.string.jma_warning_text_heavy_snow_emergency) // å¤§é›ªç‰¹åˆ¥è­¦å ±
            "06" -> context.getString(R.string.jma_warning_text_heavy_snow_warning) // å¤§é›ªè­¦å ±
            "12" -> context.getString(R.string.jma_warning_text_heavy_snow_advisory) // å¤§é›ªæ³¨æ„å ±
            "37" -> context.getString(R.string.jma_warning_text_high_wave_emergency) // æ³¢æµªç‰¹åˆ¥è­¦å ±
            "07" -> context.getString(R.string.jma_warning_text_high_wave_warning) // æ³¢æµªè­¦å ±
            "16" -> context.getString(R.string.jma_warning_text_high_wave_advisory) // æ³¢æµªæ³¨æ„å ±
            "38" -> context.getString(R.string.jma_warning_text_storm_surge_emergency) // é«˜æ½®ç‰¹åˆ¥è­¦å ±
            "08" -> context.getString(R.string.jma_warning_text_storm_surge_warning) // é«˜æ½®è­¦å ±
            "19+" -> context.getString(R.string.jma_warning_text_storm_surge_advisory) // é«˜æ½®æ³¨æ„å ±
            "19" -> context.getString(R.string.jma_warning_text_storm_surge_advisory) // é«˜æ½®æ³¨æ„å ±
            "14" -> context.getString(R.string.jma_warning_text_thunderstorm_advisory) // é›·æ³¨æ„å ±
            "17" -> context.getString(R.string.jma_warning_text_snow_melting_advisory) // èžé›ªæ³¨æ„å ±
            "20" -> context.getString(R.string.jma_warning_text_dense_fog_advisory) // æ¿ƒéœ§æ³¨æ„å ±
            "21" -> context.getString(R.string.jma_warning_text_dry_air_advisory) // ä¹¾ç‡¥æ³¨æ„å ±
            "22" -> context.getString(R.string.jma_warning_text_avalanche_advisory) // ãªã ã‚Œæ³¨æ„å ±
            "23" -> context.getString(R.string.jma_warning_text_low_temperature_advisory) // ä½Žæ¸©æ³¨æ„å ±
            "24" -> context.getString(R.string.jma_warning_text_frost_advisory) // éœœæ³¨æ„å ±
            "25" -> context.getString(R.string.jma_warning_text_ice_accretion_advisory) // ç€æ°·æ³¨æ„å ±
            "26" -> context.getString(R.string.jma_warning_text_snow_accretion_advisory) // ç€é›ªæ³¨æ„å ±
            else -> null
        }
    }

    private fun getAlertSeverity(
        code: String?,
    ): AlertSeverity {
        return when (code) {
            "33" -> AlertSeverity.EXTREME
            "35", "32", "36", "37", "38", "08" -> AlertSeverity.SEVERE
            "03", "04", "05", "02", "06", "07", "19+" -> AlertSeverity.MODERATE
            "10", "18", "15", "13", "12", "16", "19", "14", "17",
            "20", "21", "22", "23", "24", "25", "26",
            -> AlertSeverity.MINOR
            else -> AlertSeverity.UNKNOWN
        }
    }

    private fun getAlertColor(
        severity: AlertSeverity,
    ): Int {
        return when (severity) {
            AlertSeverity.EXTREME -> Color.rgb(12, 0, 12)
            AlertSeverity.SEVERE -> Color.rgb(160, 0, 160)
            AlertSeverity.MODERATE -> Color.rgb(255, 40, 0)
            AlertSeverity.MINOR -> Color.rgb(242, 231, 0)
            else -> Alert.colorFromSeverity(AlertSeverity.UNKNOWN)
        }
    }

    // Reverse geocoding
    override fun requestNearestLocation(
        context: Context,
        latitude: Double,
        longitude: Double,
    ): Observable<List<LocationAddressInfo>> {
        val areas = mApi.getAreas()
        val class20s = mApi.getRelm().map { relm ->
            val features = mutableListOf<Any?>()
            relm.forEachIndexed { i, it ->
                if (latitude <= it.ne[0] &&
                    longitude <= it.ne[1] &&
                    latitude >= it.sw[0] &&
                    longitude >= it.sw[1]
                ) {
                    features.addAll(mApi.getClass20s(i).blockingFirst().features)
                }
            }
            features
        }

        return Observable.zip(areas, class20s) {
                areasResult: JmaAreasResult,
                class20sFeatures: List<Any?>,
            ->
            convertLocation(context, latitude, longitude, areasResult, class20sFeatures)
        }
    }

    // Reverse geocoding
    private fun convertLocation(
        context: Context,
        latitude: Double,
        longitude: Double,
        areasResult: JmaAreasResult,
        class20sFeatures: List<Any?>,
    ): List<LocationAddressInfo> {
        val matchingLocations = getMatchingLocations(latitude, longitude, class20sFeatures)
        if (matchingLocations.isEmpty()) {
            throw InvalidLocationException()
        }
        val locationList = mutableListOf<LocationAddressInfo>()
        matchingLocations[0].let {
            val code = it.getProperty("code")
            if (code == null) {
                throw InvalidLocationException()
            }
            var city = if (context.currentLocale.code.startsWith("ja")) {
                areasResult.class20s?.get(code)?.name ?: ""
            } else {
                areasResult.class20s?.get(code)?.enName ?: ""
            }
            var district: String? = null

            // Split the city and district strings if necessary
            if (Regex("""^\d{6}[^0]$""").matches(code)) {
                if (context.currentLocale.code.startsWith("ja")) {
                    val matchResult = Regex("""^(.+[å¸‚ç”ºæ‘])ï¼ˆ?([^ï¼ˆ^ï¼‰]*)ï¼‰?$""").find(city)!!
                    city = matchResult.groups[1]!!.value
                    district = matchResult.groups[2]!!.value
                    if (Regex("""ã‚’é™¤ã$""").matches(district)) {
                        district = null
                    }
                } else {
                    if (city.contains(",")) {
                        district = city.substringBefore(",").trim()
                        city = city.substringAfter(",").trim()
                    } else if (Regex("""^(Northern|Southern|Eastern|Western) """).matches(city)) {
                        district = city.substringBefore("ern ").trim() + "ern"
                        city = city.substringAfter("ern ").trim()
                    } else if (city.contains(" (")) {
                        district = city.substringAfter(" (").substringBefore(")").trim()
                        city = city.substringBefore(" (")
                    }
                }
            }
            locationList.add(
                LocationAddressInfo(
                    timeZoneId = "Asia/Tokyo",
                    countryCode = "JP",
                    admin1 = getPrefecture(context, code),
                    admin1Code = code.substring(0, 2),
                    city = city,
                    district = district
                )
            )
        }
        return locationList
    }

    private fun getPrefecture(
        context: Context,
        code: String,
    ): String? {
        if (context.currentLocale.code.startsWith("ja")) {
            return with(code) {
                when {
                    startsWith("01") -> "åŒ—æµ·é“" // Hokkaido
                    startsWith("02") -> "é’æ£®çœŒ" // Aomori
                    startsWith("03") -> "å²©æ‰‹çœŒ" // Iwate
                    startsWith("04") -> "å®®åŸŽçœŒ" // Miyagi
                    startsWith("05") -> "ç§‹ç”°çœŒ" // Akita
                    startsWith("06") -> "å±±å½¢çœŒ" // Yamagata
                    startsWith("07") -> "ç¦å³¶çœŒ" // Fukushima
                    startsWith("08") -> "èŒ¨åŸŽçœŒ" // Ibaraki
                    startsWith("09") -> "æ ƒæœ¨çœŒ" // Tochigi
                    startsWith("10") -> "ç¾¤é¦¬çœŒ" // Gunma
                    startsWith("11") -> "åŸ¼çŽ‰çœŒ" // Saitama
                    startsWith("12") -> "åƒè‘‰çœŒ" // Chiba
                    startsWith("13") -> "æ±äº¬éƒ½" // TÅkyÅ
                    startsWith("14") -> "ç¥žå¥ˆå·çœŒ" // Kanagawa
                    startsWith("15") -> "æ–°æ½ŸçœŒ" // Niigata
                    startsWith("16") -> "å¯Œå±±çœŒ" // Toyama
                    startsWith("17") -> "çŸ³å·çœŒ" // Ishikawa
                    startsWith("18") -> "ç¦äº•çœŒ" // Fukui
                    startsWith("19") -> "å±±æ¢¨çœŒ" // Yamanashi
                    startsWith("20") -> "é•·é‡ŽçœŒ" // Nagano
                    startsWith("21") -> "å²é˜œçœŒ" // Gifu
                    startsWith("22") -> "é™å²¡çœŒ" // Shizuoka
                    startsWith("23") -> "æ„›çŸ¥çœŒ" // Aichi
                    startsWith("24") -> "ä¸‰é‡çœŒ" // Mie
                    startsWith("25") -> "æ»‹è³€çœŒ" // Shiga
                    startsWith("26") -> "äº¬éƒ½åºœ" // KyÅto
                    startsWith("27") -> "å¤§é˜ªåºœ" // ÅŒsaka
                    startsWith("28") -> "å…µåº«çœŒ" // HyÅgo
                    startsWith("29") -> "å¥ˆè‰¯çœŒ" // Nara
                    startsWith("30") -> "å’Œæ­Œå±±çœŒ" // Wakayama
                    startsWith("31") -> "é³¥å–çœŒ" // Tottori
                    startsWith("32") -> "å³¶æ ¹çœŒ" // Shimane
                    startsWith("33") -> "å²¡å±±çœŒ" // Okayama
                    startsWith("34") -> "åºƒå³¶çœŒ" // Hiroshima
                    startsWith("35") -> "å±±å£çœŒ" // Yamaguchi
                    startsWith("36") -> "å¾³å³¶çœŒ" // Tokushima
                    startsWith("37") -> "é¦™å·çœŒ" // Kagawa
                    startsWith("38") -> "æ„›åª›çœŒ" // Ehime
                    startsWith("39") -> "é«˜çŸ¥çœŒ" // KÅchi
                    startsWith("40") -> "ç¦å²¡çœŒ" // Fukuoka
                    startsWith("41") -> "ä½è³€çœŒ" // Saga
                    startsWith("42") -> "é•·å´ŽçœŒ" // Nagasaki
                    startsWith("43") -> "ç†Šæœ¬çœŒ" // Kumamoto
                    startsWith("44") -> "å¤§åˆ†çœŒ" // ÅŒita
                    startsWith("45") -> "å®®å´ŽçœŒ" // Miyazaki
                    startsWith("46") -> "é¹¿å…å³¶çœŒ" // Kagoshima
                    startsWith("47") -> "æ²–ç¸„çœŒ" // Okinawa
                    else -> null
                }
            }
        } else {
            return with(code) {
                when {
                    startsWith("01") -> "Hokkaido" // åŒ—æµ·é“
                    startsWith("02") -> "Aomori" // é’æ£®çœŒ
                    startsWith("03") -> "Iwate" // å²©æ‰‹çœŒ
                    startsWith("04") -> "Miyagi" // å®®åŸŽçœŒ
                    startsWith("05") -> "Akita" // ç§‹ç”°çœŒ
                    startsWith("06") -> "Yamagata" // å±±å½¢çœŒ
                    startsWith("07") -> "Fukushima" // ç¦å³¶çœŒ
                    startsWith("08") -> "Ibaraki" // èŒ¨åŸŽçœŒ
                    startsWith("09") -> "Tochigi" // æ ƒæœ¨çœŒ
                    startsWith("10") -> "Gunma" // ç¾¤é¦¬çœŒ
                    startsWith("11") -> "Saitama" // åŸ¼çŽ‰çœŒ
                    startsWith("12") -> "Chiba" // åƒè‘‰çœŒ
                    startsWith("13") -> "TÅkyÅ" // æ±äº¬éƒ½
                    startsWith("14") -> "Kanagawa" // ç¥žå¥ˆå·çœŒ
                    startsWith("15") -> "Niigata" // æ–°æ½ŸçœŒ
                    startsWith("16") -> "Toyama" // å¯Œå±±çœŒ
                    startsWith("17") -> "Ishikawa" // çŸ³å·çœŒ
                    startsWith("18") -> "Fukui" // ç¦äº•çœŒ
                    startsWith("19") -> "Yamanashi" // å±±æ¢¨çœŒ
                    startsWith("20") -> "Nagano" // é•·é‡ŽçœŒ
                    startsWith("21") -> "Gifu" // å²é˜œçœŒ
                    startsWith("22") -> "Shizuoka" // é™å²¡çœŒ
                    startsWith("23") -> "Aichi" // æ„›çŸ¥çœŒ
                    startsWith("24") -> "Mie" // ä¸‰é‡çœŒ
                    startsWith("25") -> "Shiga" // æ»‹è³€çœŒ
                    startsWith("26") -> "KyÅto" // äº¬éƒ½åºœ
                    startsWith("27") -> "ÅŒsaka" // å¤§é˜ªåºœ
                    startsWith("28") -> "HyÅgo" // å…µåº«çœŒ
                    startsWith("29") -> "Nara" // å¥ˆè‰¯çœŒ
                    startsWith("30") -> "Wakayama" // å’Œæ­Œå±±çœŒ
                    startsWith("31") -> "Tottori" // é³¥å–çœŒ
                    startsWith("32") -> "Shimane" // å³¶æ ¹çœŒ
                    startsWith("33") -> "Okayama" // å²¡å±±çœŒ
                    startsWith("34") -> "Hiroshima" // åºƒå³¶çœŒ
                    startsWith("35") -> "Yamaguchi" // å±±å£çœŒ
                    startsWith("36") -> "Tokushima" // å¾³å³¶çœŒ
                    startsWith("37") -> "Kagawa" // é¦™å·çœŒ
                    startsWith("38") -> "Ehime" // æ„›åª›çœŒ
                    startsWith("39") -> "KÅchi" // é«˜çŸ¥çœŒ
                    startsWith("40") -> "Fukuoka" // ç¦å²¡çœŒ
                    startsWith("41") -> "Saga" // ä½è³€çœŒ
                    startsWith("42") -> "Nagasaki" // é•·å´ŽçœŒ
                    startsWith("43") -> "Kumamoto" // ç†Šæœ¬çœŒ
                    startsWith("44") -> "ÅŒita" // å¤§åˆ†çœŒ
                    startsWith("45") -> "Miyazaki" // å®®å´ŽçœŒ
                    startsWith("46") -> "Kagoshima" // é¹¿å…å³¶çœŒ
                    startsWith("47") -> "Okinawa" // æ²–ç¸„çœŒ
                    else -> null
                }
            }
        }
    }

    private fun getMatchingLocations(
        latitude: Double,
        longitude: Double,
        class20sFeatures: List<Any?>,
    ): List<GeoJsonFeature> {
        val json = """{"type":"FeatureCollection","features":[${class20sFeatures.joinToString(",")}]}"""
        val geoJsonParser = GeoJsonParser(JSONObject(json))
        return geoJsonParser.features.filter { feature ->
            when (feature.geometry) {
                is GeoJsonPolygon -> (feature.geometry as GeoJsonPolygon).coordinates.any { polygon ->
                    PolyUtil.containsLocation(latitude, longitude, polygon, true)
                }
                is GeoJsonMultiPolygon -> (feature.geometry as GeoJsonMultiPolygon).polygons.any {
                    it.coordinates.any { polygon ->
                        PolyUtil.containsLocation(latitude, longitude, polygon, true)
                    }
                }
                else -> false
            }
        }
    }

    // Location parameters
    internal fun convertLocationParameters(
        location: Location,
        areasResult: JmaAreasResult,
        class20sFeatures: List<Any?>,
        weekAreaResult: Map<String, List<JmaWeekAreaResult>>,
        weekArea05Result: Map<String, List<String>>,
        forecastAreaResult: Map<String, List<JmaForecastAreaResult>>,
        amedasResult: Map<String, JmaAmedasResult>,
    ): Map<String, String> {
        val class20s: String
        val class15s: String
        val class10s: String
        val prefArea: String
        var weekArea05 = ""
        var weekAreaAmedas = ""
        var forecastAmedas = ""
        var currentAmedas = ""

        val matchingLocations = getMatchingLocations(location.latitude, location.longitude, class20sFeatures)
        if (matchingLocations.isEmpty()) {
            throw InvalidLocationException()
        }
        matchingLocations[0].let {
            class20s = it.getProperty("code") ?: ""
            class15s = areasResult.class20s?.get(class20s)?.parent ?: ""
            class10s = areasResult.class15s?.get(class15s)?.parent ?: ""
            prefArea = areasResult.class10s?.get(class10s)?.parent ?: ""
        }

        weekArea05Result.getOrElse(class10s) { null }?.forEach { wa5 ->
            weekAreaResult.getOrElse(prefArea) { null }?.forEach { wa ->
                if (wa.week == wa5) {
                    weekArea05 = wa5
                    weekAreaAmedas = wa.amedas
                }
            }
        }

        forecastAreaResult.getOrElse(prefArea) { null }?.forEach { fa ->
            if (fa.class10 == class10s) {
                forecastAmedas = fa.amedas.joinToString(",")
            }
        }

        var nearestDistance = Double.POSITIVE_INFINITY
        var distance: Double
        amedasResult.keys.forEach { key ->
            amedasResult[key]?.let {
                distance = SphericalUtil.computeDistanceBetween(
                    LatLng(location.latitude, location.longitude),
                    LatLng(
                        it.lat.getOrElse(0) { 0.0 } + it.lat.getOrElse(1) { 0.0 } / 60.0,
                        it.lon.getOrElse(0) { 0.0 } + it.lon.getOrElse(1) { 0.0 } / 60.0
                    )
                )
                if (distance < nearestDistance) {
                    if (it.elems.substring(0, 1) == "1") {
                        nearestDistance = distance
                        currentAmedas = key
                    }
                }
            }
        }

        return mapOf(
            "class20s" to class20s,
            "class10s" to class10s,
            "prefArea" to prefArea,
            "weekArea05" to weekArea05,
            "weekAreaAmedas" to weekAreaAmedas,
            "forecastAmedas" to forecastAmedas,
            "currentAmedas" to currentAmedas
        )
    }

    // Location parameters
    override fun needsLocationParametersRefresh(
        location: Location,
        coordinatesChanged: Boolean,
        features: List<SourceFeature>,
    ): Boolean {
        if (coordinatesChanged) return true

        val parameters = location.parameters.getOrElse(id) { null }

        val class20s = parameters?.getOrElse("class20s") { null }
        val class10s = parameters?.getOrElse("class10s") { null }
        val prefArea = parameters?.getOrElse("prefArea") { null }
        val weekArea05 = parameters?.getOrElse("weekArea05") { null }
        val weekAreaAmedas = parameters?.getOrElse("weekAreaAmedas") { null }
        val forecastAmedas = parameters?.getOrElse("forecastAmedas") { null }
        val currentAmedas = parameters?.getOrElse("currentAmedas") { null }

        return class20s.isNullOrEmpty() ||
            class10s.isNullOrEmpty() ||
            prefArea.isNullOrEmpty() ||
            weekArea05.isNullOrEmpty() ||
            weekAreaAmedas.isNullOrEmpty() ||
            forecastAmedas.isNullOrEmpty() ||
            currentAmedas.isNullOrEmpty()
    }

    override fun requestLocationParameters(
        context: Context,
        location: Location,
    ): Observable<Map<String, String>> {
        val areas = mApi.getAreas()
        val class20s = mApi.getRelm().map { relm ->
            val features = mutableListOf<Any?>()
            relm.forEachIndexed { i, it ->
                if (location.latitude <= it.ne[0] &&
                    location.longitude <= it.ne[1] &&
                    location.latitude >= it.sw[0] &&
                    location.longitude >= it.sw[1]
                ) {
                    features.addAll(mApi.getClass20s(i).blockingFirst().features)
                }
            }
            features
        }
        val weekArea = mApi.getWeekArea()
        val weekArea05 = mApi.getWeekArea05()
        val forecastArea = mApi.getForecastArea()
        val amedas = mApi.getAmedas()

        return Observable.zip(areas, class20s, weekArea, weekArea05, forecastArea, amedas) {
                areasResult: JmaAreasResult,
                class20sFeatures: List<Any?>,
                weekAreaResult: Map<String, List<JmaWeekAreaResult>>,
                weekArea05Result: Map<String, List<String>>,
                forecastAreaResult: Map<String, List<JmaForecastAreaResult>>,
                amedasResult: Map<String, JmaAmedasResult>,
            ->
            convertLocationParameters(
                location = location,
                areasResult = areasResult,
                class20sFeatures = class20sFeatures,
                weekAreaResult = weekAreaResult,
                weekArea05Result = weekArea05Result,
                forecastAreaResult = forecastAreaResult,
                amedasResult = amedasResult
            )
        }
    }

    companion object {
        private const val JMA_BASE_URL = "https://www.jma.go.jp/"
    }
}
