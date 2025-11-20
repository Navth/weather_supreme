package org.breezyweather.sources.accu.preferences

import android.content.Context
import org.breezyweather.R
import org.breezyweather.common.options.BaseEnum
import org.breezyweather.common.utils.UnitUtils

enum class AccuDaysPreference(
    override val id: String,
) : BaseEnum {

    ONE("1"),
    FIVE("5"),
    TEN("10"),
    FIFTEEN("15"),
    ;

    companion object {

        fun getInstance(
            value: String,
        ) = AccuDaysPreference.entries.firstOrNull {
            it.id == value
        } ?: FIFTEEN
    }

    override val valueArrayId = R.array.accu_preference_day_values
    override val nameArrayId = R.array.accu_preference_days

    override fun getName(context: Context) = UnitUtils.getName(context, this)
}
