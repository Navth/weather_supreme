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
import org.breezyweather.remoteviews.presenters.MultiCityWidgetIMP
import javax.inject.Inject

/**
 * Widget multi city provider.
 */
@AndroidEntryPoint
class WidgetMultiCityProvider : AppWidgetProvider() {

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
        if (MultiCityWidgetIMP.isInUse(context)) {
            GlobalScope.launch(Dispatchers.IO) {
                val locationList = locationRepository.getXLocations(3, withParameters = false).toMutableList()
                for (i in locationList.indices) {
                    locationList[i] = locationList[i].copy(
                        weather = weatherRepository.getWeatherByLocationId(
                            locationList[i].formattedId,
                            withDaily = true,
                            withHourly = false,
                            withMinutely = false,
                            withAlerts = false,
                            withNormals = false
                        )
                    )
                }
                MultiCityWidgetIMP.updateWidgetView(context, locationList)
            }
        }
    }
}
