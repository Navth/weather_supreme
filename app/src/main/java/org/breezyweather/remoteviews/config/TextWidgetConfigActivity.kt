package org.breezyweather.remoteviews.config

import android.view.View
import android.widget.RemoteViews
import breezyweather.data.location.LocationRepository
import breezyweather.data.weather.WeatherRepository
import breezyweather.domain.location.model.Location
import dagger.hilt.android.AndroidEntryPoint
import org.breezyweather.R
import org.breezyweather.remoteviews.presenters.TextWidgetIMP
import org.breezyweather.remoteviews.presenters.TextWidgetIMP.getRemoteViews
import org.breezyweather.sources.SourceManager
import javax.inject.Inject

/**
 * Text widget config activity.
 */
@AndroidEntryPoint
class TextWidgetConfigActivity : AbstractWidgetConfigActivity() {
    var locationNow: Location? = null
        private set

    @Inject
    lateinit var locationRepository: LocationRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository

    @Inject
    lateinit var sourceManager: SourceManager

    override suspend fun initLocations() {
        val location = locationRepository.getFirstLocation(withParameters = false)
        locationNow = location?.copy(
            weather = weatherRepository.getWeatherByLocationId(
                location.formattedId,
                withDaily = true, // isDaylight
                withHourly = false,
                withMinutely = false,
                withAlerts = true, // Custom subtitle
                withNormals = false
            )
        )
    }

    override fun initView() {
        super.initView()
        mTextColorContainer?.visibility = View.VISIBLE
        mTextSizeContainer?.visibility = View.VISIBLE
        mAlignEndContainer?.visibility = View.VISIBLE
        mHideSubtitleContainer?.visibility = View.VISIBLE
        mHideSubtitleTitle?.text = getString(R.string.widget_label_hide_header)
        mSubtitleDataContainer?.visibility = View.VISIBLE
    }

    override fun updateWidgetView() {
        TextWidgetIMP.updateWidgetView(
            this,
            locationNow,
            locationNow?.let { location ->
                sourceManager.getPollenIndexSource(
                    (location.pollenSource ?: "").ifEmpty { location.forecastSource }
                )
            }
        )
    }

    override val remoteViews: RemoteViews
        get() {
            return getRemoteViews(
                this,
                locationNow,
                textColorValueNow,
                textSize,
                alignEnd,
                hideSubtitle,
                subtitleDataValueNow,
                locationNow?.let { location ->
                    sourceManager.getPollenIndexSource(
                        (location.pollenSource ?: "").ifEmpty { location.forecastSource }
                    )
                }
            )
        }

    override val configStoreName: String
        get() {
            return getString(R.string.sp_widget_text_setting)
        }
}
