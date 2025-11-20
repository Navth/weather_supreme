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
import org.breezyweather.remoteviews.presenters.ClockDayWeekWidgetIMP
import javax.inject.Inject

/**
 * Widget clock day week provider.
 */
@AndroidEntryPoint
class WidgetClockDayWeekProvider : AppWidgetProvider() {

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository

    @OptIn(DelicateCoroutinesApi::class)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        if (ClockDayWeekWidgetIMP.isInUse(context)) {
            GlobalScope.launch(Dispatchers.IO) {
                val location = locationRepository.getFirstLocation(withParameters = false)
                ClockDayWeekWidgetIMP.updateWidgetView(
                    context,
                    location?.copy(
                        weather = weatherRepository.getWeatherByLocationId(
                            location.formattedId,
                            withDaily = true,
                            withHourly = false,
                            withMinutely = false,
                            withAlerts = false,
                            withNormals = false
                        )
                    )
                )
            }
        }
    }
}
