package org.breezyweather.sources.accu.preferences

import android.content.Context
import org.breezyweather.R
import org.breezyweather.common.options.BaseEnum
import org.breezyweather.common.utils.UnitUtils

enum class AccuHoursPreference(
    override val id: String,
) : BaseEnum {

    ONE("1"),
    TWELVE("12"),
    TWENTY_FOUR("24"),
    SEVENTY_TWO("72"),
    HUNDRED_TWENTY("120"),
    TWO_HUNDRED_FORTY("240"),
    ;

    companion object {

        fun getInstance(
            value: String,
        ) = AccuHoursPreference.entries.firstOrNull {
            it.id == value
        } ?: TWO_HUNDRED_FORTY
    }

    override val valueArrayId = R.array.accu_preference_hour_values
    override val nameArrayId = R.array.accu_preference_hours

    override fun getName(context: Context) = UnitUtils.getName(context, this)
}
