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
import org.breezyweather.remoteviews.presenters.DailyTrendWidgetIMP
import javax.inject.Inject

/**
 * Widget trend daily provider.
 */
@AndroidEntryPoint
class WidgetTrendDailyProvider : AppWidgetProvider() {

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
        if (DailyTrendWidgetIMP.isInUse(context)) {
            GlobalScope.launch(Dispatchers.IO) {
                val location = locationRepository.getFirstLocation(withParameters = false)
                DailyTrendWidgetIMP.updateWidgetView(
                    context,
                    location?.copy(
                        weather = weatherRepository.getWeatherByLocationId(
                            location.formattedId,
                            withDaily = true,
                            withHourly = false,
                            withMinutely = false,
                            withAlerts = false,
                            withNormals = true // Threshold lines
                        )
                    )
                )
            }
        }
    }
}
