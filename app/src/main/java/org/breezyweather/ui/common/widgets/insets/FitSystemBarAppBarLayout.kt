package org.breezyweather.ui.common.widgets.insets

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.updatePadding
import com.google.android.material.appbar.AppBarLayout
import org.breezyweather.common.extensions.doOnApplyWindowInsets
import org.breezyweather.common.extensions.getThemeColor
import org.breezyweather.common.utils.ColorUtils

class FitSystemBarAppBarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppBarLayout(context, attrs, defStyleAttr) {

    init {
        this.doOnApplyWindowInsets { view, insets ->
            view.updatePadding(
                top = insets.top,
                left = insets.left,
                right = insets.right
            )
        }
    }

    fun injectDefaultSurfaceTintColor() {
        setBackgroundColor(
            ColorUtils.getWidgetSurfaceColor(
                6f,
                context.getThemeColor(androidx.appcompat.R.attr.colorPrimary),
                context.getThemeColor(com.google.android.material.R.attr.colorSurface)
            )
        )
    }
}
