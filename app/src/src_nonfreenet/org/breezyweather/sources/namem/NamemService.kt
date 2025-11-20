package org.breezyweather.sources.namem

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.location.model.LocationAddressInfo
import breezyweather.domain.source.SourceFeature
import breezyweather.domain.weather.model.AirQuality
import breezyweather.domain.weather.model.Normals
import breezyweather.domain.weather.model.Precipitation
import breezyweather.domain.weather.model.PrecipitationProbability
import breezyweather.domain.weather.model.Wind
import breezyweather.domain.weather.reference.WeatherCode
import breezyweather.domain.weather.wrappers.AirQualityWrapper
import breezyweather.domain.weather.wrappers.CurrentWrapper
import breezyweather.domain.weather.wrappers.DailyWrapper
import breezyweather.domain.weather.wrappers.HalfDayWrapper
import breezyweather.domain.weather.wrappers.HourlyWrapper
import breezyweather.domain.weather.wrappers.TemperatureWrapper
import breezyweather.domain.weather.wrappers.WeatherWrapper
import com.google.maps.android.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.breezyweather.R
import org.breezyweather.common.exceptions.InvalidLocationException
import org.breezyweather.common.extensions.getCalendarMonth
import org.breezyweather.domain.weather.index.PollutantIndex
import org.breezyweather.sources.namem.json.NamemAirQualityResult
import org.breezyweather.sources.namem.json.NamemCurrentResult
import org.breezyweather.sources.namem.json.NamemDailyResult
import org.breezyweather.sources.namem.json.NamemHourlyResult
import org.breezyweather.sources.namem.json.NamemNormalsResult
import org.breezyweather.sources.namem.json.NamemStation
import org.breezyweather.unit.pollutant.PollutantConcentration.Companion.microgramsPerCubicMeter
import org.breezyweather.unit.pollutant.PollutantConcentration.Companion.milligramsPerCubicMeter
import org.breezyweather.unit.precipitation.Precipitation.Companion.millimeters
import org.breezyweather.unit.pressure.Pressure.Companion.hectopascals
import org.breezyweather.unit.ratio.Ratio.Companion.percent
import org.breezyweather.unit.speed.Speed.Companion.metersPerSecond
import org.breezyweather.unit.temperature.Temperature.Companion.celsius
import retrofit2.Retrofit
import java.util.Date
import javax.inject.Inject
import javax.inject.Named

class NamemService @Inject constructor(
    @ApplicationContext context: Context,
    @Named("JsonClient") client: Retrofit.Builder,
) : NamemServiceStub(context) {

    private val mApi by lazy {
        client
            .baseUrl(NAMEM_BASE_URL)
            .build()
            .create(NamemApi::class.java)
    }

    override val attributionLinks
        get() = mapOf(
            weatherAttribution to NAMEM_BASE_URL
        )

    override fun requestWeather(
        context: Context,
        location: Location,
        requestedFeatures: List<SourceFeature>,
    ): Observable<WeatherWrapper> {
        val stationId = location.parameters.getOrElse(id) { null }?.getOrElse("stationId") { null }?.toLongOrNull()
        if (stationId == null) {
            return Observable.error(InvalidLocationException())
        }
        val body = """{"sid":$stationId}"""

        val failedFeatures = mutableMapOf<SourceFeature, Throwable>()
        val current = if (SourceFeature.CURRENT in requestedFeatures) {
            mApi.getCurrent(
                body = body.toRequestBody("application/json".toMediaTypeOrNull())
            ).onErrorResumeNext {
                failedFeatures[SourceFeature.CURRENT] = it
                Observable.just(NamemCurrentResult())
            }
        } else {
            Observable.just(NamemCurrentResult())
        }

        val hourly = if (SourceFeature.FORECAST in requestedFeatures) {
            mApi.getHourly(
                body = body.toRequestBody("application/json".toMediaTypeOrNull())
            ).onErrorResumeNext {
                failedFeatures[SourceFeature.FORECAST] = it
                Observable.just(NamemHourlyResult())
            }
        } else {
            Observable.just(NamemHourlyResult())
        }

        val daily = if (SourceFeature.FORECAST in requestedFeatures) {
            mApi.getDaily(
                body = body.toRequestBody("application/json".toMediaTypeOrNull())
            ).onErrorResumeNext {
                failedFeatures[SourceFeature.FORECAST] = it
                Observable.just(NamemDailyResult())
            }
        } else {
            Observable.just(NamemDailyResult())
        }

        val normals = if (SourceFeature.NORMALS in requestedFeatures) {
            mApi.getNormals(
                body = body.toRequestBody("application/json".toMediaTypeOrNull())
            ).onErrorResumeNext {
                failedFeatures[SourceFeature.NORMALS] = it
                Observable.just(NamemNormalsResult())
            }
        } else {
            Observable.just(NamemNormalsResult())
        }

        val airQuality = if (SourceFeature.AIR_QUALITY in requestedFeatures) {
            mApi.getAirQuality().onErrorResumeNext {
                failedFeatures[SourceFeature.AIR_QUALITY] = it
                Observable.just(NamemAirQualityResult())
            }
        } else {
            Observable.just(NamemAirQualityResult())
        }

        return Observable.zip(current, daily, hourly, normals, airQuality) {
                currentResult: NamemCurrentResult,
                dailyResult: NamemDailyResult,
                hourlyResult: NamemHourlyResult,
                normalsResult: NamemNormalsResult,
                airQualityResult: NamemAirQualityResult,
            ->
            WeatherWrapper(
                dailyForecast = if (SourceFeature.FORECAST in requestedFeatures) {
                    getDailyForecast(context, dailyResult)
                } else {
                    null
                },
                hourlyForecast = if (SourceFeature.FORECAST in requestedFeatures) {
                    getHourlyForecast(hourlyResult)
                } else {
                    null
                },
                current = if (SourceFeature.CURRENT in requestedFeatures) {
                    getCurrent(context, currentResult)
                } else {
                    null
                },
                airQuality = if (SourceFeature.AIR_QUALITY in requestedFeatures) {
                    AirQualityWrapper(
                        current = getAirQuality(location, airQualityResult)
                    )
                } else {
                    null
                },
                normals = if (SourceFeature.NORMALS in requestedFeatures) {
                    getNormals(normalsResult)?.let {
                        mapOf(
                            Date().getCalendarMonth(location) to it
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
        currentResult: NamemCurrentResult,
    ): CurrentWrapper {
        val current = currentResult.aws?.getOrNull(0)
        return CurrentWrapper(
            weatherText = getWeatherText(context, current?.nh),
            weatherCode = getWeatherCode(current?.nh),
            temperature = TemperatureWrapper(
                temperature = current?.ttt?.celsius,
                feelsLike = current?.tttFeels?.celsius
            ),
            wind = Wind(
                degree = current?.windDir,
                speed = current?.windSpeed?.metersPerSecond
            ),
            relativeHumidity = current?.ff?.percent,
            pressure = current?.pslp?.hectopascals
        )
    }

    private fun getAirQuality(
        location: Location,
        airQualityResult: NamemAirQualityResult,
    ): AirQuality {
        val stationMap = airQualityResult.data?.filter {
            it.lat != null && it.lon != null && (it.sid != null || it.id != null)
        }?.associate {
            it.sid.toString() to LatLng(it.lat!!, it.lon!!)
        }
        val station = airQualityResult.data?.firstOrNull {
            it.sid.toString() == LatLng(location.latitude, location.longitude).getNearestLocation(stationMap, 50000.0)
        }
        var pM25: Double? = null
        var pM10: Double? = null
        var sO2: Double? = null
        var nO2: Double? = null
        var o3: Double? = null
        var cO: Double? = null
        station?.elementList?.forEach {
            if (it.unit == "ÐÐ§Ð˜") {
                when (it.id) {
                    "pm25" -> pM25 = convertAqi(PollutantIndex.PM25, it.current)
                    "pm10" -> pM10 = convertAqi(PollutantIndex.PM10, it.current)
                    "so2" -> sO2 = convertAqi(PollutantIndex.SO2, it.current)
                    "no2" -> nO2 = convertAqi(PollutantIndex.NO2, it.current)
                    "o3" -> o3 = convertAqi(PollutantIndex.O3, it.current)
                    "co" -> cO = convertAqi(PollutantIndex.CO, it.current)
                }
            }
        }
        return AirQuality(
            pM25 = pM25?.microgramsPerCubicMeter,
            pM10 = pM10?.microgramsPerCubicMeter,
            sO2 = sO2?.microgramsPerCubicMeter,
            nO2 = nO2?.microgramsPerCubicMeter,
            o3 = o3?.microgramsPerCubicMeter,
            cO = cO?.milligramsPerCubicMeter
        )
    }

    private fun getNormals(
        normalsResult: NamemNormalsResult,
    ): Normals? {
        return normalsResult.foreMonthly?.lastOrNull { it.obsDate != null && it.obsDate < Date() }?.let {
            Normals(
                daytimeTemperature = it.ttMaxAve?.celsius,
                nighttimeTemperature = it.ttMinAve?.celsius
            )
        }
    }

    private fun getDailyForecast(
        context: Context,
        dailyResult: NamemDailyResult,
    ): List<DailyWrapper> {
        val dailyList = mutableListOf<DailyWrapper>()
        var date: Date
        dailyResult.fore5Day?.forEachIndexed { i, forecast ->
            // account for western provinces which are 1 hour behind
            date = Date(forecast.foreDate!!.time.plus(3600000))
            if (i == 0) {
                dailyList.add(
                    DailyWrapper(
                        date = Date(date.time.minus(86400000)),
                        night = HalfDayWrapper(
                            weatherText = getWeatherText(context, forecast.wwN),
                            weatherCode = getWeatherCode(forecast.wwN),
                            temperature = TemperatureWrapper(
                                temperature = forecast.temN?.celsius,
                                feelsLike = forecast.temNFeel?.celsius
                            ),
                            precipitationProbability = PrecipitationProbability(
                                total = forecast.wwNPer?.percent
                            ),
                            wind = Wind(
                                speed = forecast.wndN?.metersPerSecond
                            )
                        )
                    )
                )
            }
            dailyList.add(
                DailyWrapper(
                    date = date,
                    day = HalfDayWrapper(
                        weatherText = getWeatherText(context, forecast.wwD),
                        weatherCode = getWeatherCode(forecast.wwD),
                        temperature = TemperatureWrapper(
                            temperature = forecast.temD?.celsius,
                            feelsLike = forecast.temDFeel?.celsius
                        ),
                        precipitationProbability = PrecipitationProbability(
                            total = forecast.wwDPer?.percent
                        ),
                        wind = Wind(
                            speed = forecast.wndD?.metersPerSecond
                        )
                    ),
                    night = dailyResult.fore5Day.getOrNull(i + 1)?.let {
                        HalfDayWrapper(
                            weatherText = getWeatherText(context, it.wwN),
                            weatherCode = getWeatherCode(it.wwN),
                            temperature = TemperatureWrapper(
                                temperature = it.temN?.celsius,
                                feelsLike = it.temNFeel?.celsius
                            ),
                            precipitationProbability = PrecipitationProbability(
                                total = it.wwNPer?.percent
                            ),
                            wind = Wind(
                                speed = it.wndN?.metersPerSecond
                            )
                        )
                    }
                )
            )
        }
        return dailyList
    }

    private fun getHourlyForecast(
        hourlyResult: NamemHourlyResult,
    ): List<HourlyWrapper> {
        val hourlyList = mutableListOf<HourlyWrapper>()
        hourlyResult.fore3Hours?.forEach {
            if (it.fdate !== null) {
                hourlyList.add(
                    HourlyWrapper(
                        date = it.fdate,
                        temperature = TemperatureWrapper(
                            temperature = it.tem?.toDoubleOrNull()?.celsius
                        ),
                        precipitation = Precipitation(
                            total = it.pre?.toDoubleOrNull()?.millimeters
                        ),
                        precipitationProbability = PrecipitationProbability(
                            total = it.preProb?.toDoubleOrNull()?.percent
                        ),
                        wind = Wind(
                            speed = it.wnd?.toDoubleOrNull()?.metersPerSecond
                        )
                    )
                )
            }
        }
        return hourlyList
    }

    private fun getWeatherText(
        context: Context,
        weather: Int?,
    ): String? {
        // TODO: Check with a Mongolian speaker (???) for correct interpretation of the text
        return when (weather) {
            2 -> context.getString(R.string.common_weather_text_clear_sky) // "Ð¦ÑÐ»Ð¼ÑÐ³"
            3 -> context.getString(R.string.common_weather_text_overcast) // "Ò®Ò¯Ð»ÑÑ€Ñ…ÑÐ³"
            5, 7 -> context.getString(R.string.common_weather_text_mostly_clear) // "Ð‘Ð°Ð³Ð°Ð²Ñ‚Ð°Ñ€ Ò¯Ò¯Ð»Ñ‚ÑÐ¹"
            9, 10 -> context.getString(R.string.common_weather_text_cloudy) // "Ò®Ò¯Ð»ÑˆÐ¸Ð½Ñ"
            20 -> context.getString(R.string.common_weather_text_mostly_clear) // "Ò®Ò¯Ð» Ð±Ð°Ð³Ð°ÑÐ½Ð°"
            23, 24 -> context.getString(R.string.common_weather_text_snow_light) // "Ð¯Ð»Ð¸Ð¼Ð³Ò¯Ð¹ Ñ†Ð°Ñ"
            // "Ð¯Ð»Ð¸Ð¼Ð³Ò¯Ð¹ Ñ…ÑƒÑ€ Ñ‚ÑƒÐ½Ð°Ð´Ð°Ñ":
            27, 28 -> context.getString(R.string.common_weather_text_rain_snow_mixed_showers_light)
            60 -> context.getString(R.string.common_weather_text_rain_light) // "Ð‘Ð°Ð³Ð° Ð·ÑÑ€Ð³Ð¸Ð¹Ð½ Ð±Ð¾Ñ€Ð¾Ð¾"
            61 -> context.getString(R.string.common_weather_text_rain) // "Ð‘Ð¾Ñ€Ð¾Ð¾"
            63 -> context.getString(R.string.common_weather_text_rain_heavy) // "Ð˜Ñ… Ð±Ð¾Ñ€Ð¾Ð¾"
            65 -> context.getString(R.string.common_weather_text_rain_snow_mixed) // "Ð¥ÑƒÑ€ Ñ‚ÑƒÐ½Ð°Ð´Ð°Ñ"
            66 -> context.getString(R.string.common_weather_text_rain_snow_mixed_heavy) // "Ð˜Ñ… Ñ…ÑƒÑ€ Ñ‚ÑƒÐ½Ð°Ð´Ð°Ñ"
            67 -> context.getString(R.string.common_weather_text_rain_snow_mixed_heavy) // "ÐÐ°Ð´Ð°Ñ€ Ð¸Ñ… Ñ…ÑƒÑ€ Ñ‚ÑƒÐ½Ð°Ð´Ð°Ñ"
            68 -> context.getString(R.string.common_weather_text_rain_heavy) // "Ð˜Ñ… ÑƒÑÐ°Ñ€Ñ…Ð°Ð³ Ð±Ð¾Ñ€Ð¾Ð¾"
            71 -> context.getString(R.string.common_weather_text_snow) // "Ð¦Ð°Ñ"
            73 -> context.getString(R.string.common_weather_text_snow_heavy) // "Ð˜Ñ… Ñ†Ð°Ñ"
            75 -> context.getString(R.string.common_weather_text_snow_heavy) // "ÐÐ°Ð´Ð°Ñ€ Ð¸Ñ… Ñ†Ð°Ñ"
            80, 81 -> context.getString(R.string.common_weather_text_rain_showers_light) // "Ð‘Ð°Ð³Ð° Ð·ÑÑ€Ð³Ð¸Ð¹Ð½ Ð°Ð°Ð´Ð°Ñ€"
            82, 83 -> context.getString(R.string.common_weather_text_rain_showers) // "ÐÐ°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            84, 85 -> context.getString(R.string.common_weather_text_rain_showers_heavy) // "Ð£ÑÐ°Ñ€Ñ…Ð°Ð³ Ð°Ð°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            90, 91 -> context.getString(R.string.weather_kind_thunderstorm) // "Ð”ÑƒÑƒ.Ð¦Ð°Ñ… Ð±Ð°Ð³Ð° Ð·ÑÑ€Ð³Ð¸Ð¹Ð½ Ð°Ð°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            92, 93 -> context.getString(R.string.weather_kind_thunderstorm) // "Ð”ÑƒÑƒ.Ð¦Ð°Ñ… Ð°Ð°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            94, 95 -> context.getString(R.string.weather_kind_thunderstorm) // "Ð”ÑƒÑƒ.Ð¦Ð°Ñ… ÑƒÑÐ°Ñ€Ñ…Ð°Ð³ Ð°Ð°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            else -> null
        }
    }

    private fun getWeatherCode(
        weather: Int?,
    ): WeatherCode? {
        return when (weather) {
            2 -> WeatherCode.CLEAR // "Ð¦ÑÐ»Ð¼ÑÐ³"
            3 -> WeatherCode.CLOUDY // "Ò®Ò¯Ð»ÑÑ€Ñ…ÑÐ³"
            5, 7 -> WeatherCode.PARTLY_CLOUDY // "Ð‘Ð°Ð³Ð°Ð²Ñ‚Ð°Ñ€ Ò¯Ò¯Ð»Ñ‚ÑÐ¹"
            9, 10 -> WeatherCode.CLOUDY // "Ò®Ò¯Ð»ÑˆÐ¸Ð½Ñ"
            20 -> WeatherCode.PARTLY_CLOUDY // "Ò®Ò¯Ð» Ð±Ð°Ð³Ð°ÑÐ½Ð°"
            23, 24 -> WeatherCode.SNOW // "Ð¯Ð»Ð¸Ð¼Ð³Ò¯Ð¹ Ñ†Ð°Ñ"
            27, 28 -> WeatherCode.SLEET // "Ð¯Ð»Ð¸Ð¼Ð³Ò¯Ð¹ Ñ…ÑƒÑ€ Ñ‚ÑƒÐ½Ð°Ð´Ð°Ñ"
            60 -> WeatherCode.RAIN // "Ð‘Ð°Ð³Ð° Ð·ÑÑ€Ð³Ð¸Ð¹Ð½ Ð±Ð¾Ñ€Ð¾Ð¾"
            61 -> WeatherCode.RAIN // "Ð‘Ð¾Ñ€Ð¾Ð¾"
            63 -> WeatherCode.RAIN // "Ð˜Ñ… Ð±Ð¾Ñ€Ð¾Ð¾"
            65 -> WeatherCode.SLEET // "Ð¥ÑƒÑ€ Ñ‚ÑƒÐ½Ð°Ð´Ð°Ñ"
            66 -> WeatherCode.SLEET // "Ð˜Ñ… Ñ…ÑƒÑ€ Ñ‚ÑƒÐ½Ð°Ð´Ð°Ñ"
            67 -> WeatherCode.SLEET // "ÐÐ°Ð´Ð°Ñ€ Ð¸Ñ… Ñ…ÑƒÑ€ Ñ‚ÑƒÐ½Ð°Ð´Ð°Ñ"
            68 -> WeatherCode.RAIN // "Ð˜Ñ… ÑƒÑÐ°Ñ€Ñ…Ð°Ð³ Ð±Ð¾Ñ€Ð¾Ð¾"
            71 -> WeatherCode.SNOW // "Ð¦Ð°Ñ"
            73 -> WeatherCode.SNOW // "Ð˜Ñ… Ñ†Ð°Ñ"
            75 -> WeatherCode.SNOW // "ÐÐ°Ð´Ð°Ñ€ Ð¸Ñ… Ñ†Ð°Ñ"
            80, 81 -> WeatherCode.RAIN // "Ð‘Ð°Ð³Ð° Ð·ÑÑ€Ð³Ð¸Ð¹Ð½ Ð°Ð°Ð´Ð°Ñ€"
            82, 83 -> WeatherCode.RAIN // "ÐÐ°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            84, 85 -> WeatherCode.RAIN // "Ð£ÑÐ°Ñ€Ñ…Ð°Ð³ Ð°Ð°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            90, 91 -> WeatherCode.THUNDERSTORM // "Ð”ÑƒÑƒ.Ð¦Ð°Ñ… Ð±Ð°Ð³Ð° Ð·ÑÑ€Ð³Ð¸Ð¹Ð½ Ð°Ð°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            92, 93 -> WeatherCode.THUNDERSTORM // "Ð”ÑƒÑƒ.Ð¦Ð°Ñ… Ð°Ð°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            94, 95 -> WeatherCode.THUNDERSTORM // "Ð”ÑƒÑƒ.Ð¦Ð°Ñ… ÑƒÑÐ°Ñ€Ñ…Ð°Ð³ Ð°Ð°Ð´Ð°Ñ€ Ð±Ð¾Ñ€Ð¾Ð¾"
            else -> null
        }
    }

    // Convert Mongolian AQI to pollutant concentration
    // SO2, NO2, PM10, PM2.5, O3 in Âµg/mÂ³
    // CO in mg/mÂ³
    //
    // Breakpoint source: http://agaar.mn/article-view/692
    private fun convertAqi(
        pollutant: PollutantIndex,
        aqi: Double?,
    ): Double? {
        if (aqi == null || aqi < 0.0) return null
        if (aqi == 0.0) return 0.0

        val thresholds = listOf(0.0, 50.0, 100.0, 200.0, 300.0, 400.0, 500.0)
        val breakpoints = mapOf<PollutantIndex, List<Double>>(
            PollutantIndex.SO2 to listOf(0.0, 100.0, 300.0, 800.0, 1600.0, 2100.0, 2620.0),
            PollutantIndex.NO2 to listOf(0.0, 100.0, 200.0, 700.0, 2000.0, 3500.0, 3840.0),
            PollutantIndex.PM10 to listOf(0.0, 50.0, 100.0, 300.0, 420.0, 500.0, 600.0),
            PollutantIndex.PM25 to listOf(0.0, 35.0, 50.0, 150.0, 250.0, 350.0, 500.0),
            PollutantIndex.CO to listOf(0.0, 10.0, 30.0, 60.0, 90.0, 120.0, 150.0),
            PollutantIndex.O3 to listOf(0.0, 50.0, 100.0, 265.0, 800.0, 1000.0, 1200.0)
        )
        // AQI less than 500
        for (i in 1..<thresholds.size) {
            if (aqi > thresholds[i - 1] && aqi <= thresholds[i]) {
                return (aqi - thresholds[i - 1]) /
                    (thresholds[i] - thresholds[i - 1]) *
                    (breakpoints[pollutant]!![i] - breakpoints[pollutant]!![i - 1]) +
                    (breakpoints[pollutant]!![i - 1])
            }
        }
        // AQI more than 500
        // linear extrapolation from the 400-500 range
        return (aqi - thresholds[5]) /
            (thresholds[6] - thresholds[5]) *
            (breakpoints[pollutant]!![6] - breakpoints[pollutant]!![5]) +
            (breakpoints[pollutant]!![5])
    }

    // Reverse geocoding
    override fun requestNearestLocation(
        context: Context,
        latitude: Double,
        longitude: Double,
    ): Observable<List<LocationAddressInfo>> {
        return mApi.getStations().map {
            convertLocation(latitude, longitude, it.locations)
        }
    }

    // Reverse geocoding
    internal fun convertLocation(
        latitude: Double,
        longitude: Double,
        stations: List<NamemStation>?,
    ): List<LocationAddressInfo> {
        val stationMap = stations?.filter {
            it.lat != null && it.lon != null && (it.sid != null || it.id != null)
        }?.associate {
            it.sid.toString() to LatLng(it.lat!!, it.lon!!)
        }
        val station = stations?.firstOrNull {
            it.sid.toString() == LatLng(latitude, longitude).getNearestLocation(stationMap, 200000.0)
        }

        if (station?.lat == null || station.lon == null) {
            throw InvalidLocationException()
        }
        return listOf(
            LocationAddressInfo(
                timeZoneId = when (station.provinceName) {
                    "Ð‘Ð°ÑÐ½-Ó¨Ð»Ð³Ð¸Ð¹", // Bayan-Ã–lgii
                    "Ð“Ð¾Ð²ÑŒ-ÐÐ»Ñ‚Ð°Ð¹", // Govi-Altai
                    "Ð¥Ð¾Ð²Ð´", // Khovd
                    "Ð£Ð²Ñ", // Uvs
                    "Ð—Ð°Ð²Ñ…Ð°Ð½", // Zavkhan
                    -> "Asia/Hovd"
                    else -> "Asia/Ulaanbaatar"
                },
                countryCode = "MN",
                admin1 = station.provinceName,
                admin2 = station.districtName,
                city = station.districtName,
                district = if (station.stationName != station.districtName) {
                    station.stationName
                } else {
                    null
                }
            )
        )
    }

    // Location parameters
    override fun needsLocationParametersRefresh(
        location: Location,
        coordinatesChanged: Boolean,
        features: List<SourceFeature>,
    ): Boolean {
        if (coordinatesChanged) return true

        val sid = location.parameters.getOrElse(id) { null }?.getOrElse("stationId") { null }
        return sid.isNullOrEmpty()
    }

    override fun requestLocationParameters(
        context: Context,
        location: Location,
    ): Observable<Map<String, String>> {
        return mApi.getStations().map {
            getLocationParameters(location, it.locations)
        }
    }

    // Location parameters
    private fun getLocationParameters(
        location: Location,
        stations: List<NamemStation>?,
    ): Map<String, String> {
        val stationMap = stations?.filter {
            it.lat != null && it.lon != null && (it.sid != null || it.id != null)
        }?.associate {
            it.sid.toString() to LatLng(it.lat!!, it.lon!!)
        }
        val nearestStation = LatLng(location.latitude, location.longitude).getNearestLocation(stationMap, 200000.0)
        if (nearestStation == null) {
            throw InvalidLocationException()
        }
        return mapOf(
            "stationId" to nearestStation
        )
    }

    companion object {
        private const val NAMEM_BASE_URL = "https://weather.gov.mn/"
    }
}
