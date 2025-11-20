package org.breezyweather.ui.main.adapters.main.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import breezyweather.domain.location.model.Location
import org.breezyweather.R
import org.breezyweather.common.activities.BreezyActivity
import org.breezyweather.common.extensions.formatMeasure
import org.breezyweather.common.extensions.getVisibilityDescription
import org.breezyweather.common.options.appearance.DetailScreen
import org.breezyweather.common.utils.UnitUtils
import org.breezyweather.common.utils.helpers.IntentHelper
import org.breezyweather.ui.theme.resource.providers.ResourceProvider
import org.breezyweather.unit.formatting.UnitWidth

class VisibilityViewHolder(parent: ViewGroup) : AbstractMainCardViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.container_main_visibility, parent, false)
) {
    private val visibilityValueView: TextView = itemView.findViewById(R.id.visibility_value)
    private val visibilityDescriptionView: TextView = itemView.findViewById(R.id.visibility_description)

    override fun onBindView(
        activity: BreezyActivity,
        location: Location,
        provider: ResourceProvider,
        listAnimationEnabled: Boolean,
        itemAnimationEnabled: Boolean,
    ) {
        super.onBindView(activity, location, provider, listAnimationEnabled, itemAnimationEnabled)

        val talkBackBuilder = StringBuilder(context.getString(R.string.visibility))

        location.weather!!.current?.visibility?.let { visibility ->
            visibilityValueView.text = UnitUtils.formatUnitsHalfSize(
                visibility.formatMeasure(context)
            )
            visibilityDescriptionView.text = visibility.getVisibilityDescription(context)

            talkBackBuilder.append(context.getString(R.string.colon_separator))
            talkBackBuilder.append(visibility.formatMeasure(context, unitWidth = UnitWidth.LONG))
            talkBackBuilder.append(context.getString(org.breezyweather.unit.R.string.locale_separator))
            talkBackBuilder.append(visibilityValueView.text)
        }

        itemView.contentDescription = talkBackBuilder.toString()
        itemView.setOnClickListener {
            IntentHelper.startDailyWeatherActivity(
                context as BreezyActivity,
                location.formattedId,
                location.weather!!.todayIndex,
                DetailScreen.TAG_VISIBILITY
            )
        }
    }
}
