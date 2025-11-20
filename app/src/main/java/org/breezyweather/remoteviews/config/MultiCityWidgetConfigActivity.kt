package org.breezyweather.remoteviews.config

import android.view.View
import android.widget.RemoteViews
import breezyweather.data.location.LocationRepository
import breezyweather.data.weather.WeatherRepository
import breezyweather.domain.location.model.Location
import dagger.hilt.android.AndroidEntryPoint
import org.breezyweather.R
import org.breezyweather.remoteviews.presenters.MultiCityWidgetIMP
import javax.inject.Inject

/**
 * Multi city widget config activity.
 */
@AndroidEntryPoint
class MultiCityWidgetConfigActivity : AbstractWidgetConfigActivity() {
    var locationList = mutableListOf<Location>()
        private set

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository

    override suspend fun initLocations() {
        locationList = locationRepository.getXLocations(3, withParameters = false).toMutableList()
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
    }

    override fun initView() {
        super.initView()
        mCardStyleContainer?.visibility = View.VISIBLE
        mCardAlphaContainer?.visibility = View.VISIBLE
        mTextColorContainer?.visibility = View.VISIBLE
        mTextSizeContainer?.visibility = View.VISIBLE
    }

    override fun updateWidgetView() {
        MultiCityWidgetIMP.updateWidgetView(this, locationList)
    }

    override val remoteViews: RemoteViews
        get() {
            return MultiCityWidgetIMP.getRemoteViews(
                this,
                locationList,
                cardStyleValueNow,
                cardAlpha,
                textColorValueNow,
                textSize
            )
        }

    override val configStoreName: String
        get() {
            return getString(R.string.sp_widget_multi_city)
        }
}
