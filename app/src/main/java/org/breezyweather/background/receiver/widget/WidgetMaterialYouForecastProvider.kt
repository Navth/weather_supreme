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
import org.breezyweather.remoteviews.presenters.MaterialYouForecastWidgetIMP
import javax.inject.Inject

@AndroidEntryPoint
class WidgetMaterialYouForecastProvider : AppWidgetProvider() {

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
        if (MaterialYouForecastWidgetIMP.isEnabled(context)) {
            GlobalScope.launch(Dispatchers.IO) {
                val location = locationRepository.getFirstLocation(withParameters = false)
                MaterialYouForecastWidgetIMP.updateWidgetView(
                    context,
                    location?.copy(
                        weather = weatherRepository.getWeatherByLocationId(
                            location.formattedId,
                            withDaily = true,
                            withHourly = true,
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
