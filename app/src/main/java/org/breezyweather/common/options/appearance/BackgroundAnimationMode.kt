package org.breezyweather.common.options.appearance

import android.content.Context
import org.breezyweather.R
import org.breezyweather.common.options.BaseEnum
import org.breezyweather.common.utils.UnitUtils

enum class BackgroundAnimationMode(
    override val id: String,
) : BaseEnum {

    SYSTEM("system"),
    ENABLED("enabled"),
    DISABLED("disabled"),
    ;

    companion object {

        fun getInstance(
            value: String,
        ) = entries.firstOrNull {
            it.id == value
        } ?: SYSTEM
    }

    override val valueArrayId = R.array.background_animation_values
    override val nameArrayId = R.array.background_animation

    override fun getName(context: Context) = UnitUtils.getName(context, this)
}
