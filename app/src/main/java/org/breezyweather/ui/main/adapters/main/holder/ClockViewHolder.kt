package org.breezyweather.ui.main.adapters.main.holder

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AnalogClock
import android.widget.TextClock
import breezyweather.domain.location.model.Location
import org.breezyweather.R
import org.breezyweather.common.activities.BreezyActivity
import org.breezyweather.common.extensions.getFormattedTime
import org.breezyweather.common.extensions.is12Hour
import org.breezyweather.common.options.appearance.DetailScreen
import org.breezyweather.common.utils.helpers.IntentHelper
import org.breezyweather.ui.theme.resource.providers.ResourceProvider
import java.util.Date
import java.util.TimeZone

class ClockViewHolder(parent: ViewGroup) : AbstractMainCardViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.container_main_clock, parent, false)
) {
    private val clockAnalogView: AnalogClock = itemView.findViewById(R.id.clock_analog)
    private val clockTextHourView: TextClock = itemView.findViewById(R.id.clock_text_hour)
    private val clockTextMinuteView: TextClock = itemView.findViewById(R.id.clock_text_minute)

    override fun onBindView(
        activity: BreezyActivity,
        location: Location,
        provider: ResourceProvider,
        listAnimationEnabled: Boolean,
        itemAnimationEnabled: Boolean,
    ) {
        super.onBindView(activity, location, provider, listAnimationEnabled, itemAnimationEnabled)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            clockAnalogView.visibility = View.VISIBLE
            clockAnalogView.timeZone = location.timeZone.id
        } else if (location.timeZone == TimeZone.getDefault()) {
            clockAnalogView.visibility = View.VISIBLE
        } else {
            clockAnalogView.visibility = View.GONE
        }

        clockTextHourView.timeZone = location.timeZone.id
        clockTextMinuteView.timeZone = location.timeZone.id

        val talkBackBuilder = StringBuilder(context.getString(R.string.clock))
        talkBackBuilder.append(context.getString(R.string.colon_separator))
        talkBackBuilder.append(Date().getFormattedTime(location, activity, context.is12Hour))

        itemView.contentDescription = talkBackBuilder.toString()
        itemView.setOnClickListener {
            IntentHelper.startDailyWeatherActivity(
                context as BreezyActivity,
                location.formattedId,
                location.weather!!.todayIndex,
                DetailScreen.TAG_CONDITIONS
            )
        }
    }
}
