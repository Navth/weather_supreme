package org.breezyweather.sources.lhmt

import android.content.Context
import breezyweather.domain.location.model.Location
import breezyweather.domain.location.model.LocationAddressInfo
import breezyweather.domain.source.SourceFeature
import breezyweather.domain.weather.model.Alert
import breezyweather.domain.weather.model.Precipitation
import breezyweather.domain.weather.model.Wind
import breezyweather.domain.weather.reference.AlertSeverity
import breezyweather.domain.weather.reference.WeatherCode
import breezyweather.domain.weather.wrappers.CurrentWrapper
import breezyweather.domain.weather.wrappers.DailyWrapper
import breezyweather.domain.weather.wrappers.HourlyWrapper
import breezyweather.domain.weather.wrappers.TemperatureWrapper
import breezyweather.domain.weather.wrappers.WeatherWrapper
import com.google.maps.android.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable
import org.breezyweather.R
import org.breezyweather.common.exceptions.InvalidLocationException
import org.breezyweather.common.extensions.code
import org.breezyweather.common.extensions.currentLocale
import org.breezyweather.sources.lhmt.json.LhmtAlertText
import org.breezyweather.sources.lhmt.json.LhmtAlertsResult
import org.breezyweather.sources.lhmt.json.LhmtLocationsResult
import org.breezyweather.sources.lhmt.json.LhmtWeatherResult
import org.breezyweather.unit.precipitation.Precipitation.Companion.millimeters
import org.breezyweather.unit.pressure.Pressure.Companion.hectopascals
import org.breezyweather.unit.ratio.Ratio.Companion.percent
import org.breezyweather.unit.speed.Speed.Companion.metersPerSecond
import org.breezyweather.unit.temperature.Temperature.Companion.celsius
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject
import javax.inject.Named

class LhmtService @Inject constructor(
    @ApplicationContext context: Context,
    @Named("JsonClient") client: Retrofit.Builder,
) : LhmtServiceStub(context) {

    override val privacyPolicyUrl = "https://www.meteo.lt/istaiga/asmens-duomenu-apsauga/privatumo-politika/"

    private val mApi by lazy {
        client.baseUrl(LHMT_BASE_URL)
            .build()
            .create(LhmtApi::class.java)
    }

    private val mWwwApi by lazy {
        client.baseUrl(LHMT_WWW_BASE_URL)
            .build()
            .create(LhmtWwwApi::class.java)
    }

    override val attributionLinks = mapOf(
        weatherAttribution to LHMT_WWW_BASE_URL
    )

    override fun requestWeather(
        context: Context,
        location: Location,
        requestedFeatures: List<SourceFeature>,
    ): Observable<WeatherWrapper> {
        val forecastLocation = location.parameters.getOrElse(id) { null }?.getOrElse("forecastLocation") { null }
        val currentLocation = location.parameters.getOrElse(id) { null }?.getOrElse("currentLocation") { null }
        val municipality = location.parameters.getOrElse(id) { null }?.getOrElse("municipality") { null }
        val county = location.parameters.getOrElse(id) { null }?.getOrElse("county") { null }
        if (forecastLocation.isNullOrEmpty() ||
            currentLocation.isNullOrEmpty() ||
            municipality.isNullOrEmpty() ||
            county.isNullOrEmpty()
        ) {
            return Observable.error(InvalidLocationException())
        }

        val failedFeatures = mutableMapOf<SourceFeature, Throwable>()

        val forecast = if (SourceFeature.FORECAST in requestedFeatures) {
            mApi.getForecast(forecastLocation).onErrorResumeNext {
                failedFeatures[SourceFeature.FORECAST] = it
                Observable.just(LhmtWeatherResult())
            }
        } else {
            Observable.just(LhmtWeatherResult())
        }
        val current = if (SourceFeature.CURRENT in requestedFeatures) {
            mApi.getCurrent(currentLocation).onErrorResumeNext {
                failedFeatures[SourceFeature.CURRENT] = it
                Observable.just(LhmtWeatherResult())
            }
        } else {
            Observable.just(LhmtWeatherResult())
        }

        val alerts = if (SourceFeature.ALERT in requestedFeatures) {
            mWwwApi.getAlertList().map { list ->
                val path = list.first().substringAfter(LHMT_WWW_BASE_URL)
                mWwwApi.getAlerts(path).onErrorResumeNext {
                    failedFeatures[SourceFeature.ALERT] = it
                    Observable.just(LhmtAlertsResult())
                }.blockingFirst()
            }.onErrorResumeNext {
                failedFeatures[SourceFeature.ALERT] = it
                Observable.just(LhmtAlertsResult())
            }
        } else {
            Observable.just(LhmtAlertsResult())
        }

        return Observable.zip(current, forecast, alerts) {
                currentResult: LhmtWeatherResult,
                forecastResult: LhmtWeatherResult,
                alertsResult: LhmtAlertsResult,
            ->
            val hourlyForecast = if (SourceFeature.FORECAST in requestedFeatures) {
                getHourlyForecast(context, forecastResult)
            } else {
                null
            }
            WeatherWrapper(
                dailyForecast = if (SourceFeature.FORECAST in requestedFeatures) {
                    getDailyForecast(hourlyForecast)
                } else {
                    null
                },
                hourlyForecast = hourlyForecast,
                current = if (SourceFeature.CURRENT in requestedFeatures) {
                    getCurrent(context, currentResult)
                } else {
                    null
                },
                alertList = if (SourceFeature.ALERT in requestedFeatures) {
                    getAlertList(context, location, alertsResult)
                } else {
                    null
                },
                failedFeatures = failedFeatures
            )
        }
    }

    private fun getCurrent(
        context: Context,
        currentResult: LhmtWeatherResult,
    ): CurrentWrapper? {
        return currentResult.observations?.last()?.let {
            CurrentWrapper(
                weatherText = getWeatherText(context, it.conditionCode),
                weatherCode = getWeatherCode(it.conditionCode),
                temperature = TemperatureWrapper(
                    temperature = it.airTemperature?.celsius,
                    feelsLike = it.feelsLikeTemperature?.celsius
                ),
                wind = Wind(
                    degree = it.windDirection,
                    speed = it.windSpeed?.metersPerSecond,
                    gusts = it.windGust?.metersPerSecond
                ),
                relativeHumidity = it.relativeHumidity?.percent,
                pressure = it.seaLevelPressure?.hectopascals,
                cloudCover = it.cloudCover?.percent
            )
        }
    }

    private fun getDailyForecast(
        hourlyForecast: List<HourlyWrapper>?,
    ): List<DailyWrapper> {
        if (hourlyForecast.isNullOrEmpty()) return emptyList()

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        formatter.timeZone = TimeZone.getTimeZone("Europe/Vilnius")
        val hourlyListDates = hourlyForecast.groupBy { formatter.format(it.date) }.keys

        return hourlyListDates.map {
            DailyWrapper(
                date = formatter.parse(it)!!
            )
        }.dropLast(1) // Remove last (incomplete) daily item
    }

    private fun getHourlyForecast(
        context: Context,
        forecastResult: LhmtWeatherResult,
    ): List<HourlyWrapper> {
        val hourlyList = mutableListOf<HourlyWrapper>()
        forecastResult.forecastTimestamps?.forEach {
            if (it.forecastTimeUtc != null) {
                hourlyList.add(
                    HourlyWrapper(
                        date = it.forecastTimeUtc,
                        weatherText = getWeatherText(context, it.conditionCode),
                        weatherCode = getWeatherCode(it.conditionCode),
                        temperature = TemperatureWrapper(
                            temperature = it.airTemperature?.celsius,
                            feelsLike = it.feelsLikeTemperature?.celsius
                        ),
                        precipitation = Precipitation(
                            total = it.totalPrecipitation?.millimeters
                        ),
                        wind = Wind(
                            degree = it.windDirection,
                            speed = it.windSpeed?.metersPerSecond,
                            gusts = it.windGust?.metersPerSecond
                        ),
                        relativeHumidity = it.relativeHumidity?.percent,
                        pressure = it.seaLevelPressure?.hectopascals,
                        cloudCover = it.cloudCover?.percent
                    )
                )
            }
        }
        return hourlyList
    }

    private fun getAlertList(
        context: Context,
        location: Location,
        alertsResult: LhmtAlertsResult,
    ): List<Alert> {
        val id = "lhmt"
        val municipality = location.parameters.getOrElse(id) { null }?.getOrElse("municipality") { null }
        val county = location.parameters.getOrElse(id) { null }?.getOrElse("county") { null }
        if (municipality.isNullOrEmpty() || county.isNullOrEmpty()) {
            throw InvalidLocationException()
        }

        val alertList = mutableListOf<Alert>()
        var severity: AlertSeverity
        var active: Boolean
        alertsResult.phenomenonGroups?.forEach { phenomenonGroup ->
            phenomenonGroup.areaGroups?.forEach { areaGroup ->
                active = false
                areaGroup.areas?.forEach { area ->
                    active = active || (area.id.endsWith(municipality) || area.id.endsWith(county))
                }
                if (active) {
                    areaGroup.singleAlerts?.forEach { singleAlert ->
                        if (singleAlert.responseType?.none != true) {
                            severity = getAlertSeverity(singleAlert.severity)
                            alertList.add(
                                Alert(
                                    alertId = singleAlert.phenomenon +
                                        " " +
                                        singleAlert.severity +
                                        " " +
                                        singleAlert.tFrom?.time.toString(),
                                    startDate = singleAlert.tFrom,
                                    endDate = singleAlert.tTo,
                                    headline = getAlertText(context, singleAlert.headline),
                                    description = getAlertText(context, singleAlert.description),
                                    instruction = getAlertText(context, singleAlert.instruction),
                                    source = "Lietuvos hidrometeorologijos tarnyba",
                                    severity = severity,
                                    color = Alert.colorFromSeverity(severity)
                                )
                            )
                        }
                    }
                }
            }
        }
        return alertList
    }

    private fun getWeatherText(
        context: Context,
        code: String?,
    ): String? {
        return when (code) {
            "clear" -> context.getString(R.string.common_weather_text_clear_sky)
            "partly-cloudy" -> context.getString(R.string.common_weather_text_partly_cloudy)
            "variable-cloudiness" -> context.getString(R.string.common_weather_text_partly_cloudy)
            "cloudy-with-sunny-intervals" -> context.getString(R.string.common_weather_text_partly_cloudy)
            "cloudy" -> context.getString(R.string.common_weather_text_partly_cloudy)
            "rain-showers" -> context.getString(R.string.common_weather_text_rain_showers)
            "light-rain-at-times" -> context.getString(R.string.common_weather_text_rain_light)
            "rain-at-times" -> context.getString(R.string.common_weather_text_rain)
            "light-rain" -> context.getString(R.string.common_weather_text_rain_light)
            "rain" -> context.getString(R.string.common_weather_text_rain)
            "heavy-rain" -> context.getString(R.string.common_weather_text_rain_heavy)
            "thunder" -> context.getString(R.string.weather_kind_thunder)
            "isolated-thunderstorms" -> context.getString(R.string.weather_kind_thunderstorm)
            "thunderstorms" -> context.getString(R.string.weather_kind_thunderstorm)
            "heavy-rain-with-thunderstorms" -> context.getString(R.string.weather_kind_thunderstorm)
            "sleet-showers" -> context.getString(R.string.common_weather_text_rain_snow_mixed_showers)
            "sleet-at-times" -> context.getString(R.string.common_weather_text_rain_snow_mixed)
            "light-sleet" -> context.getString(R.string.common_weather_text_rain_snow_mixed_light)
            "sleet" -> context.getString(R.string.common_weather_text_rain_snow_mixed)
            "freezing-rain" -> context.getString(R.string.common_weather_text_rain_freezing)
            "hail" -> context.getString(R.string.weather_kind_hail)
            "snow-showers" -> context.getString(R.string.common_weather_text_snow_showers)
            "light-snow-at-times" -> context.getString(R.string.common_weather_text_snow_light)
            "snow-at-times" -> context.getString(R.string.common_weather_text_snow)
            "light-snow" -> context.getString(R.string.common_weather_text_snow_light)
            "snow" -> context.getString(R.string.common_weather_text_snow)
            "heavy-snow" -> context.getString(R.string.common_weather_text_snow_heavy)
            "snowstorm" -> context.getString(R.string.common_weather_text_snow_heavy)
            "fog" -> context.getString(R.string.common_weather_text_fog)
            "squall" -> context.getString(R.string.common_weather_text_squall)
            else -> null
        }
    }

    private fun getWeatherCode(
        code: String?,
    ): WeatherCode? {
        return when (code) {
            "clear" -> WeatherCode.CLEAR
            "partly-cloudy" -> WeatherCode.PARTLY_CLOUDY
            "variable-cloudiness" -> WeatherCode.PARTLY_CLOUDY
            "cloudy-with-sunny-intervals" -> WeatherCode.PARTLY_CLOUDY
            "cloudy" -> WeatherCode.CLOUDY
            "rain-showers" -> WeatherCode.RAIN
            "light-rain-at-times" -> WeatherCode.RAIN
            "rain-at-times" -> WeatherCode.RAIN
            "light-rain" -> WeatherCode.RAIN
            "rain" -> WeatherCode.RAIN
            "heavy-rain" -> WeatherCode.RAIN
            "thunder" -> WeatherCode.THUNDER
            "isolated-thunderstorms" -> WeatherCode.THUNDERSTORM
            "thunderstorms" -> WeatherCode.THUNDERSTORM
            "heavy-rain-with-thunderstorms" -> WeatherCode.THUNDERSTORM
            "sleet-showers" -> WeatherCode.SLEET
            "sleet-at-times" -> WeatherCode.SLEET
            "light-sleet" -> WeatherCode.SLEET
            "sleet" -> WeatherCode.SLEET
            "freezing-rain" -> WeatherCode.SLEET
            "hail" -> WeatherCode.HAIL
            "snow-showers" -> WeatherCode.SNOW
            "light-snow-at-times" -> WeatherCode.SNOW
            "snow-at-times" -> WeatherCode.SNOW
            "light-snow" -> WeatherCode.SNOW
            "snow" -> WeatherCode.SNOW
            "heavy-snow" -> WeatherCode.SNOW
            "snowstorm" -> WeatherCode.SNOW
            "fog" -> WeatherCode.FOG
            "squall" -> WeatherCode.WIND
            else -> null
        }
    }

    private fun getAlertText(
        context: Context,
        text: LhmtAlertText?,
    ): String? {
        return if (context.currentLocale.code.startsWith("lt")) text?.lt else text?.en
    }

    private fun getAlertSeverity(
        severity: String?,
    ): AlertSeverity {
        return with(severity) {
            when {
                equals("Extreme", ignoreCase = true) -> AlertSeverity.EXTREME
                equals("Severe", ignoreCase = true) -> AlertSeverity.SEVERE
                equals("Moderate", ignoreCase = true) -> AlertSeverity.MODERATE
                equals("Minor", ignoreCase = true) -> AlertSeverity.MINOR
                else -> AlertSeverity.UNKNOWN
            }
        }
    }

    override fun requestNearestLocation(
        context: Context,
        latitude: Double,
        longitude: Double,
    ): Observable<List<LocationAddressInfo>> {
        return mApi.getForecastLocations().map {
            convertLocation(latitude, longitude, it)
        }
    }

    // reverse geocoding
    private fun convertLocation(
        latitude: Double,
        longitude: Double,
        forecastLocations: List<LhmtLocationsResult>,
    ): List<LocationAddressInfo> {
        val locationList = mutableListOf<LocationAddressInfo>()
        val forecastLocationMap = forecastLocations
            .filter { it.countryCode == null || it.countryCode.equals("LT", ignoreCase = true) }
            .associate { it.code to LatLng(it.coordinates.latitude, it.coordinates.longitude) }
        val forecastLocation = LatLng(latitude, longitude)
            .getNearestLocation(forecastLocationMap, 50000.0)
        forecastLocations.firstOrNull { it.code == forecastLocation }?.let {
            val municipalityName = it.administrativeDivision
            val municipalityCode = MUNICIPALITIES.firstOrNull { pair ->
                pair.second == municipalityName
            }?.first
            val countyCode = COUNTIES_MUNICIPALITIES.firstOrNull { pair ->
                pair.second == municipalityCode
            }?.first
            val countyName = COUNTIES.firstOrNull { pair ->
                pair.first == countyCode
            }?.second
            locationList.add(
                LocationAddressInfo(
                    timeZoneId = "Europe/Vilnius",
                    countryCode = "LT",
                    admin1 = countyName,
                    admin1Code = countyCode,
                    admin2 = municipalityName,
                    admin2Code = municipalityCode,
                    city = it.name
                )
            )
        }
        return locationList
    }

    override fun needsLocationParametersRefresh(
        location: Location,
        coordinatesChanged: Boolean,
        features: List<SourceFeature>,
    ): Boolean {
        if (coordinatesChanged) return true

        val forecastLocation = location.parameters.getOrElse(id) { null }?.getOrElse("forecastLocation") { null }
        val currentLocation = location.parameters.getOrElse(id) { null }?.getOrElse("currentLocation") { null }
        val municipality = location.parameters.getOrElse(id) { null }?.getOrElse("municipality") { null }
        val county = location.parameters.getOrElse(id) { null }?.getOrElse("county") { null }

        return forecastLocation.isNullOrEmpty() ||
            currentLocation.isNullOrEmpty() ||
            municipality.isNullOrEmpty() ||
            county.isNullOrEmpty()
    }

    override fun requestLocationParameters(
        context: Context,
        location: Location,
    ): Observable<Map<String, String>> {
        val forecastLocations = mApi.getForecastLocations()
        val currentLocations = mApi.getCurrentLocations()
        return Observable.zip(forecastLocations, currentLocations) {
                forecastLocationsResult: List<LhmtLocationsResult>,
                currentLocationsResult: List<LhmtLocationsResult>,
            ->
            convert(location, forecastLocationsResult, currentLocationsResult)
        }
    }

    // location parameters
    private fun convert(
        location: Location,
        forecastLocations: List<LhmtLocationsResult>,
        currentLocations: List<LhmtLocationsResult>,
    ): Map<String, String> {
        val forecastLocationMap = forecastLocations
            .filter { it.countryCode == null || it.countryCode.equals("LT", ignoreCase = true) }
            .associate { it.code to LatLng(it.coordinates.latitude, it.coordinates.longitude) }
        val forecastLocation = LatLng(location.latitude, location.longitude)
            .getNearestLocation(forecastLocationMap, 50000.0)

        val currentLocationMap = currentLocations
            .filter { it.countryCode == null || it.countryCode.equals("LT", ignoreCase = true) }
            .associate { it.code to LatLng(it.coordinates.latitude, it.coordinates.longitude) }
        val currentLocation = LatLng(location.latitude, location.longitude)
            .getNearestLocation(currentLocationMap, 50000.0)

        val municipalityName = forecastLocations.firstOrNull { it.code == forecastLocation }?.administrativeDivision
        val municipalityCode = MUNICIPALITIES.firstOrNull { pair ->
            pair.second == municipalityName
        }?.first
        val countyCode = COUNTIES_MUNICIPALITIES.firstOrNull { pair ->
            pair.second == municipalityCode
        }?.first

        if (forecastLocation == null || currentLocation == null || municipalityCode == null || countyCode == null) {
            throw InvalidLocationException()
        }

        return mapOf(
            "forecastLocation" to forecastLocation,
            "currentLocation" to currentLocation,
            "municipality" to municipalityCode,
            "county" to countyCode
        )
    }

    companion object {
        private const val LHMT_BASE_URL = "https://api.meteo.lt/"
        private const val LHMT_WWW_BASE_URL = "https://www.meteo.lt/"

        // The municipality codes used by LHMT is obtained from any of their warning files,
        // e.g. https://www.meteo.lt/meteo_jobs/pavojingi_met_reisk_ibl/20240910115424-00000280
        //
        // The codes are then matched to the names given by this endpoint:
        // https://api.meteo.lt/v1/places
        //
        // We use the long names from that endpoint rather than the shortened names in the warning file,
        // so that we can directly assign location parameters without further manipulation of the output.
        //
        // These codes are used only by LHMT for identifying whether an alert applies to a municipality.
        // They are not related to Lithuania's ISO 3166-2 subdivision codes.
        private val MUNICIPALITIES = listOf(
            Pair("LT032", "AkmenÄ—s rajono savivaldybÄ—"),
            Pair("LT011", "Alytaus miesto savivaldybÄ—"),
            Pair("LT033", "Alytaus rajono savivaldybÄ—"),
            Pair("LT034", "AnykÅ¡ÄiÅ³ rajono savivaldybÄ—"),
            Pair("LT012", "BirÅ¡tono savivaldybÄ—"),
            Pair("LT036", "BirÅ¾Å³ rajono savivaldybÄ—"),
            Pair("LT015", "DruskininkÅ³ savivaldybÄ—"),
            Pair("LT042", "ElektrÄ—nÅ³ savivaldybÄ—"),
            Pair("LT045", "Ignalinos rajono savivaldybÄ—"),
            Pair("LT046", "Jonavos rajono savivaldybÄ—"),
            Pair("LT047", "JoniÅ¡kio rajono savivaldybÄ—"),
            Pair("LT094", "Jurbarko rajono savivaldybÄ—"),
            Pair("LT049", "KaiÅ¡iadoriÅ³ rajono savivaldybÄ—"),
            Pair("LT048", "Kalvarijos savivaldybÄ—"),
            Pair("LT019", "Kauno miesto savivaldybÄ—"),
            Pair("LT052", "Kauno rajono savivaldybÄ—"),
            Pair("LT058", "KazlÅ³ RÅ«dos savivaldybÄ—"),
            Pair("LT053", "KelmÄ—s rajono savivaldybÄ—"),
            Pair("LT054", "KlaipÄ—dos miesto savivaldybÄ—"),
            Pair("LT021", "KlaipÄ—dos rajono savivaldybÄ—"),
            Pair("LT055", "Kretingos rajono savivaldybÄ—"),
            Pair("LT056", "KupiÅ¡kio rajono savivaldybÄ—"),
            Pair("LT057", "KÄ—dainiÅ³ rajono savivaldybÄ—"),
            Pair("LT059", "LazdijÅ³ rajono savivaldybÄ—"),
            Pair("LT018", "MarijampolÄ—s savivaldybÄ—"),
            Pair("LT061", "MaÅ¾eikiÅ³ rajono savivaldybÄ—"),
            Pair("LT062", "MolÄ—tÅ³ rajono savivaldybÄ—"),
            Pair("LT023", "Neringos miesto savivaldybÄ—"),
            Pair("LT063", "PagÄ—giÅ³ savivaldybÄ—"),
            Pair("LT065", "Pakruojo rajono savivaldybÄ—"),
            Pair("LT025", "Palangos miesto savivaldybÄ—"),
            Pair("LT027", "PanevÄ—Å¾io miesto savivaldybÄ—"),
            Pair("LT066", "PanevÄ—Å¾io rajono savivaldybÄ—"),
            Pair("LT067", "Pasvalio rajono savivaldybÄ—"),
            Pair("LT068", "PlungÄ—s rajono savivaldybÄ—"),
            Pair("LT069", "PrienÅ³ rajono savivaldybÄ—"),
            Pair("LT071", "RadviliÅ¡kio rajono savivaldybÄ—"),
            Pair("LT072", "RaseiniÅ³ rajono savivaldybÄ—"),
            Pair("LT074", "Rietavo savivaldybÄ—"),
            Pair("LT073", "RokiÅ¡kio rajono savivaldybÄ—"),
            Pair("LT084", "Å akiÅ³ rajono savivaldybÄ—"),
            Pair("LT085", "Å alÄininkÅ³ rajono savivaldybÄ—"),
            Pair("LT029", "Å iauliÅ³ miesto savivaldybÄ—"),
            Pair("LT091", "Å iauliÅ³ rajono savivaldybÄ—"),
            Pair("LT087", "Å ilalÄ—s rajono savivaldybÄ—"),
            Pair("LT088", "Å ilutÄ—s rajono savivaldybÄ—"),
            Pair("LT089", "Å irvintÅ³ rajono savivaldybÄ—"),
            Pair("LT075", "Skuodo rajono savivaldybÄ—"),
            Pair("LT086", "Å venÄioniÅ³ rajono savivaldybÄ—"),
            Pair("LT077", "TauragÄ—s rajono savivaldybÄ—"),
            Pair("LT078", "TelÅ¡iÅ³ rajono savivaldybÄ—"),
            Pair("LT079", "TrakÅ³ rajono savivaldybÄ—"),
            Pair("LT081", "UkmergÄ—s rajono savivaldybÄ—"),
            Pair("LT082", "Utenos rajono savivaldybÄ—"),
            Pair("LT038", "VarÄ—nos rajono savivaldybÄ—"),
            Pair("LT039", "VilkaviÅ¡kio rajono savivaldybÄ—"),
            Pair("LT013", "Vilniaus miesto savivaldybÄ—"),
            Pair("LT041", "Vilniaus rajono savivaldybÄ—"),
            Pair("LT030", "Visagino savivaldybÄ—"),
            Pair("LT043", "ZarasÅ³ rajono savivaldybÄ—")
        )

        // The county codes used by LHMT is obtained from any of their warning files,
        // e.g. https://www.meteo.lt/meteo_jobs/pavojingi_met_reisk_ibl/20240910115424-00000280
        //
        // These codes are used only by LHMT for identifying whether an alert applies to a municipality.
        // They are not related to Lithuania's ISO 3166-2 subdivision codes.
        private val COUNTIES = listOf(
            Pair("LT001", "Alytaus apskritis"),
            Pair("LT002", "Kauno apskritis"),
            Pair("LT003", "KlaipÄ—dos apskritis"),
            Pair("LT004", "MarijampolÄ—s apskritis"),
            Pair("LT005", "PanevÄ—Å¾io apskritis"),
            Pair("LT006", "Å iauliÅ³ apskritis"),
            Pair("LT007", "TauragÄ—s apskritis"),
            Pair("LT008", "TelÅ¡iÅ³ apskritis"),
            Pair("LT009", "Utenos apskritis"),
            Pair("LT010", "Vilniaus apskritis")
        )

        // The above LHMT county and municipality codes are matched according to this table:
        // https://en.wikipedia.org/wiki/Municipalities_of_Lithuania#Municipalities
        private val COUNTIES_MUNICIPALITIES = listOf(
            Pair("LT006", "LT032"), // Å iauliÅ³ apskritis -> AkmenÄ—s rajono savivaldybÄ—
            Pair("LT001", "LT011"), // Alytaus apskritis -> Alytaus miesto savivaldybÄ—
            Pair("LT001", "LT033"), // Alytaus apskritis -> Alytaus rajono savivaldybÄ—
            Pair("LT009", "LT034"), // Utenos apskritis -> AnykÅ¡ÄiÅ³ rajono savivaldybÄ—
            Pair("LT002", "LT012"), // Kauno apskritis -> BirÅ¡tono savivaldybÄ—
            Pair("LT005", "LT036"), // PanevÄ—Å¾io apskritis -> BirÅ¾Å³ rajono savivaldybÄ—
            Pair("LT001", "LT015"), // Alytaus apskritis -> DruskininkÅ³ savivaldybÄ—
            Pair("LT010", "LT042"), // Vilniaus apskriti -> ElektrÄ—nÅ³ savivaldybÄ—
            Pair("LT009", "LT045"), // Utenos apskritis -> Ignalinos rajono savivaldybÄ—
            Pair("LT002", "LT046"), // Kauno apskritis -> Jonavos rajono savivaldybÄ—
            Pair("LT006", "LT047"), // Å iauliÅ³ apskritis -> JoniÅ¡kio rajono savivaldybÄ—
            Pair("LT007", "LT094"), // TauragÄ—s apskritis -> Jurbarko rajono savivaldybÄ—
            Pair("LT002", "LT049"), // Kauno apskritis -> KaiÅ¡iadoriÅ³ rajono savivaldybÄ—
            Pair("LT004", "LT048"), // MarijampolÄ—s apskritis -> Kalvarijos savivaldybÄ—
            Pair("LT002", "LT019"), // Kauno apskritis -> Kauno miesto savivaldybÄ—
            Pair("LT002", "LT052"), // Kauno apskritis -> Kauno rajono savivaldybÄ—
            Pair("LT004", "LT058"), // MarijampolÄ—s apskritis -> KazlÅ³ RÅ«dos savivaldybÄ—
            Pair("LT006", "LT053"), // Å iauliÅ³ apskritis -> KelmÄ—s rajono savivaldybÄ—
            Pair("LT003", "LT054"), // KlaipÄ—dos apskritis -> KlaipÄ—dos miesto savivaldybÄ—
            Pair("LT003", "LT021"), // KlaipÄ—dos apskritis -> KlaipÄ—dos rajono savivaldybÄ—
            Pair("LT003", "LT055"), // KlaipÄ—dos apskritis -> Kretingos rajono savivaldybÄ—
            Pair("LT005", "LT056"), // PanevÄ—Å¾io apskritis -> KupiÅ¡kio rajono savivaldybÄ—
            Pair("LT002", "LT057"), // Kauno apskritis -> KÄ—dainiÅ³ rajono savivaldybÄ—
            Pair("LT001", "LT059"), // Alytaus apskritis -> LazdijÅ³ rajono savivaldybÄ—
            Pair("LT004", "LT018"), // MarijampolÄ—s apskritis -> MarijampolÄ—s savivaldybÄ—
            Pair("LT008", "LT061"), // TelÅ¡iÅ³ apskritis -> MaÅ¾eikiÅ³ rajono savivaldybÄ—
            Pair("LT009", "LT062"), // Utenos apskritis -> MolÄ—tÅ³ rajono savivaldybÄ—
            Pair("LT003", "LT023"), // KlaipÄ—dos apskritis -> Neringos miesto savivaldybÄ—
            Pair("LT007", "LT063"), // TauragÄ—s apskritis -> PagÄ—giÅ³ savivaldybÄ—
            Pair("LT006", "LT065"), // Å iauliÅ³ apskritis -> Pakruojo rajono savivaldybÄ—
            Pair("LT003", "LT025"), // KlaipÄ—dos apskritis -> Palangos miesto savivaldybÄ—
            Pair("LT005", "LT027"), // PanevÄ—Å¾io apskritis -> PanevÄ—Å¾io miesto savivaldybÄ—
            Pair("LT005", "LT066"), // PanevÄ—Å¾io apskritis -> PanevÄ—Å¾io rajono savivaldybÄ—
            Pair("LT005", "LT067"), // PanevÄ—Å¾io apskritis -> Pasvalio rajono savivaldybÄ—
            Pair("LT008", "LT068"), // TelÅ¡iÅ³ apskritis -> PlungÄ—s rajono savivaldybÄ—
            Pair("LT002", "LT069"), // Kauno apskritis -> PrienÅ³ rajono savivaldybÄ—
            Pair("LT006", "LT071"), // Å iauliÅ³ apskritis -> RadviliÅ¡kio rajono savivaldybÄ—
            Pair("LT002", "LT072"), // Kauno apskritis -> RaseiniÅ³ rajono savivaldybÄ—
            Pair("LT008", "LT074"), // TelÅ¡iÅ³ apskritis -> Rietavo savivaldybÄ—
            Pair("LT005", "LT073"), // PanevÄ—Å¾io apskritis -> RokiÅ¡kio rajono savivaldybÄ—
            Pair("LT004", "LT084"), // MarijampolÄ—s apskritis -> Å akiÅ³ rajono savivaldybÄ—
            Pair("LT010", "LT085"), // Vilniaus apskriti -> Å alÄininkÅ³ rajono savivaldybÄ—
            Pair("LT006", "LT029"), // Å iauliÅ³ apskritis -> Å iauliÅ³ miesto savivaldybÄ—
            Pair("LT006", "LT091"), // Å iauliÅ³ apskritis -> Å iauliÅ³ rajono savivaldybÄ—
            Pair("LT007", "LT087"), // TauragÄ—s apskritis -> Å ilalÄ—s rajono savivaldybÄ—
            Pair("LT003", "LT088"), // KlaipÄ—dos apskritis -> Å ilutÄ—s rajono savivaldybÄ—
            Pair("LT010", "LT089"), // Vilniaus apskriti -> Å irvintÅ³ rajono savivaldybÄ—
            Pair("LT003", "LT075"), // KlaipÄ—dos apskritis -> Skuodo rajono savivaldybÄ—
            Pair("LT010", "LT086"), // Vilniaus apskriti -> Å venÄioniÅ³ rajono savivaldybÄ—
            Pair("LT007", "LT077"), // TauragÄ—s apskritis -> TauragÄ—s rajono savivaldybÄ—
            Pair("LT008", "LT078"), // TelÅ¡iÅ³ apskritis -> TelÅ¡iÅ³ rajono savivaldybÄ—
            Pair("LT010", "LT079"), // Vilniaus apskriti -> TrakÅ³ rajono savivaldybÄ—
            Pair("LT010", "LT081"), // Vilniaus apskriti -> UkmergÄ—s rajono savivaldybÄ—
            Pair("LT009", "LT082"), // Utenos apskritis -> Utenos rajono savivaldybÄ—
            Pair("LT001", "LT038"), // Alytaus apskritis -> VarÄ—nos rajono savivaldybÄ—
            Pair("LT004", "LT039"), // MarijampolÄ—s apskritis -> VilkaviÅ¡kio rajono savivaldybÄ—
            Pair("LT010", "LT013"), // Vilniaus apskriti -> Vilniaus miesto savivaldybÄ—
            Pair("LT010", "LT041"), // Vilniaus apskriti -> Vilniaus rajono savivaldybÄ—
            Pair("LT009", "LT030"), // Utenos apskritis -> Visagino savivaldybÄ—
            Pair("LT009", "LT043") // Utenos apskritis -> ZarasÅ³ rajono savivaldyb
        )
    }
}
