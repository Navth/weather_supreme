package org.breezyweather.remoteviews.presenters.notification

import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import breezyweather.domain.location.model.Location
import org.breezyweather.R
import org.breezyweather.common.extensions.formatMeasure
import org.breezyweather.common.extensions.getFormattedMediumDayAndMonthInAdditionalCalendar
import org.breezyweather.common.extensions.getFormattedTime
import org.breezyweather.common.extensions.is12Hour
import org.breezyweather.common.extensions.notificationBuilder
import org.breezyweather.common.extensions.notify
import org.breezyweather.common.extensions.toBitmap
import org.breezyweather.common.options.appearance.CalendarHelper
import org.breezyweather.domain.location.model.getPlace
import org.breezyweather.domain.settings.SettingsManager
import org.breezyweather.domain.weather.model.getName
import org.breezyweather.domain.weather.model.getStrength
import org.breezyweather.remoteviews.Notifications
import org.breezyweather.remoteviews.presenters.AbstractRemoteViewsPresenter
import org.breezyweather.ui.theme.resource.ResourceHelper
import org.breezyweather.ui.theme.resource.ResourcesProviderFactory
import java.util.Date

object NativeWidgetNotificationIMP : AbstractRemoteViewsPresenter() {
    fun buildNotificationAndSendIt(
        context: Context,
        location: Location,
        daytime: Boolean,
        tempIcon: Boolean,
        persistent: Boolean,
    ) {
        val current = location.weather?.current ?: return
        val provider = ResourcesProviderFactory.newInstance
        val settings = SettingsManager.getInstance(context)
        val temperatureUnit = settings.getTemperatureUnit(context)

        val tempFeelsLikeOrAir = if (settings.isWidgetNotificationUsingFeelsLike) {
            current.temperature?.feelsLikeTemperature ?: current.temperature?.temperature
        } else {
            current.temperature?.temperature
        }
        val temperature = if (tempIcon) tempFeelsLikeOrAir else null

        val subtitle = StringBuilder()
        subtitle.append(location.getPlace(context))
        if (CalendarHelper.getAlternateCalendarSetting(context) != null) {
            subtitle.append(context.getString(org.breezyweather.unit.R.string.locale_separator))
                .append(Date().getFormattedMediumDayAndMonthInAdditionalCalendar(location, context))
        } else {
            location.weather!!.base.refreshTime?.let {
                subtitle.append(context.getString(org.breezyweather.unit.R.string.locale_separator))
                    .append(context.getString(R.string.notification_refreshed_at))
                    .append(" ")
                    .append(it.getFormattedTime(location, context, context.is12Hour))
            }
        }

        val contentTitle = StringBuilder()
        if (!tempIcon && tempFeelsLikeOrAir != null) {
            contentTitle.append(tempFeelsLikeOrAir.formatMeasure(context, temperatureUnit))
        }
        if (!current.weatherText.isNullOrEmpty()) {
            if (contentTitle.toString().isNotEmpty()) contentTitle.append(" â€“ ")
            contentTitle.append(current.weatherText)
        }

        val notification = context.notificationBuilder(Notifications.CHANNEL_WIDGET).apply {
            priority = NotificationCompat.PRIORITY_MAX
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            if (temperature != null) {
                setSmallIcon(
                    IconCompat.createWithBitmap(
                        ResourceHelper.createTempBitmap(context, temperature, temperatureUnit)
                    )
                )
            } else {
                setSmallIcon(
                    ResourceHelper.getDefaultMinimalXmlIconId(current.weatherCode, daytime)
                )
            }
            current.weatherCode?.let { weatherCode ->
                setLargeIcon(
                    ResourceHelper.getWidgetNotificationIcon(
                        provider,
                        weatherCode,
                        daytime,
                        minimal = false,
                        darkText = false
                    ).toBitmap()
                )
            }
            setSubText(subtitle.toString())
            setContentTitle(contentTitle.toString())
            if (current.airQuality?.isIndexValid == true) {
                setContentText(context.getString(R.string.air_quality) + " â€“ " + current.airQuality!!.getName(context))
            } else {
                current.wind?.getStrength(context)?.let { strength ->
                    setContentText(context.getString(R.string.wind) + " â€“ " + strength)
                }
            }
            setOngoing(persistent)
            setOnlyAlertOnce(true)
            setContentIntent(getWeatherPendingIntent(context, null, Notifications.ID_WIDGET))
        }.build()

        if (!tempIcon) {
            current.weatherCode?.let { weatherCode ->
                try {
                    notification.javaClass
                        .getMethod("setSmallIcon", Icon::class.java)
                        .invoke(
                            notification,
                            ResourceHelper.getMinimalIcon(provider, weatherCode, daytime)
                        )
                } catch (ignore: Exception) {
                    // do nothing.
                }
            }
        }

        context.notify(Notifications.ID_WIDGET, notification)
    }
}
