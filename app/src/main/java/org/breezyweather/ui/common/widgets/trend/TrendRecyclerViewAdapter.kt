package org.breezyweather.ui.common.widgets.trend

import androidx.recyclerview.widget.RecyclerView
import breezyweather.domain.location.model.Location

abstract class TrendRecyclerViewAdapter<VH : RecyclerView.ViewHolder>(
    private var mLocation: Location,
) : RecyclerView.Adapter<VH>() {
    var location: Location
        get() = mLocation
        set(location) {
            mLocation = location
            notifyDataSetChanged()
        }
}
