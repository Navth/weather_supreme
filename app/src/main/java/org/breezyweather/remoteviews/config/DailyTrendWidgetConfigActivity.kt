package org.breezyweather.remoteviews.config

import android.view.View
import android.widget.RemoteViews
import breezyweather.data.location.LocationRepository
import breezyweather.data.weather.WeatherRepository
import breezyweather.domain.location.model.Location
import dagger.hilt.android.AndroidEntryPoint
import org.breezyweather.R
import org.breezyweather.remoteviews.presenters.DailyTrendWidgetIMP
import javax.inject.Inject

/**
 * Daily trend widget config activity.
 */
@AndroidEntryPoint
class DailyTrendWidgetConfigActivity : AbstractWidgetConfigActivity() {
    var locationNow: Location? = null
        private set

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository

    override suspend fun initLocations() {
        val location = locationRepository.getFirstLocation(withParameters = false)
        locationNow = location?.copy(
            weather = weatherRepository.getWeatherByLocationId(
                location.formattedId,
                withDaily = true,
                withHourly = false,
                withMinutely = false,
                withAlerts = false,
                withNormals = true // Threshold lines
            )
        )
    }

    override fun initData() {
        super.initData()
        val cardStyles = resources.getStringArray(R.array.widget_card_styles)
        val cardStyleValues = resources.getStringArray(R.array.widget_card_style_values)
        cardStyleValueNow = "auto"
        this.cardStyles = arrayOf(cardStyles[1], cardStyles[2], cardStyles[3])
        this.cardStyleValues = arrayOf(cardStyleValues[1], cardStyleValues[2], cardStyleValues[3])
    }

    override fun initView() {
        super.initView()
        mCardStyleContainer?.visibility = View.VISIBLE
        mCardAlphaContainer?.visibility = View.VISIBLE
    }

    override fun updateWidgetView() {
        DailyTrendWidgetIMP.updateWidgetView(this, locationNow)
    }

    override val remoteViews: RemoteViews
        get() {
            return DailyTrendWidgetIMP.getRemoteViews(
                this,
                locationNow,
                resources.displayMetrics.widthPixels,
                cardStyleValueNow,
                cardAlpha
            )
        }

    override val configStoreName: String
        get() {
            return getString(R.string.sp_widget_daily_trend_setting)
        }
}
