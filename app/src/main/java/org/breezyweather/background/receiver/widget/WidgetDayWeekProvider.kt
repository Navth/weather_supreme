package org.breezyweather.background.receiver.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import breezyweather.data.location.LocationRepository
import breezyweather.data.weather.WeatherRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.breezyweather.remoteviews.presenters.DayWeekWidgetIMP
import org.breezyweather.sources.SourceManager
import javax.inject.Inject

/**
 * Widget day week provider.
 */
@AndroidEntryPoint
class WidgetDayWeekProvider : AppWidgetProvider() {

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository

    @Inject
    lateinit var sourceManager: SourceManager

    @OptIn(DelicateCoroutinesApi::class)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        if (DayWeekWidgetIMP.isInUse(context)) {
            GlobalScope.launch(Dispatchers.IO) {
                val location = locationRepository.getFirstLocation(withParameters = false)
                DayWeekWidgetIMP.updateWidgetView(
                    context,
                    location?.copy(
                        weather = weatherRepository.getWeatherByLocationId(
                            location.formattedId,
                            withDaily = true,
                            withHourly = false,
                            withMinutely = false,
                            withAlerts = true, // Custom subtitle
                            withNormals = false
                        )
                    ),
                    location?.let { locationNow ->
                        sourceManager.getPollenIndexSource(
                            (locationNow.pollenSource ?: "").ifEmpty { locationNow.forecastSource }
                        )
                    }
                )
            }
        }
    }
}
