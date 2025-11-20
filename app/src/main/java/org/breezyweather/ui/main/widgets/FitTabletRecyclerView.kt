package org.breezyweather.ui.main.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import org.breezyweather.R

class FitTabletRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val paddingHorizontal = resources.getDimensionPixelSize(R.dimen.normal_margin).div(2)
        setPadding(paddingHorizontal, paddingTop, paddingHorizontal, paddingBottom)
    }
}
