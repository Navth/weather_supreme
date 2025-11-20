package org.breezyweather.ui.main.adapters.main.holder

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.CallSuper
import breezyweather.domain.location.model.Location
import com.google.android.material.card.MaterialCardView
import org.breezyweather.R
import org.breezyweather.common.activities.BreezyActivity
import org.breezyweather.common.extensions.dpToPx
import org.breezyweather.ui.theme.resource.providers.ResourceProvider

@SuppressLint("ObjectAnimatorBinding")
abstract class AbstractMainCardViewHolder(
    view: View,
) : AbstractMainViewHolder(view) {
    protected var mLocation: Location? = null

    @CallSuper
    open fun onBindView(
        activity: BreezyActivity,
        location: Location,
        provider: ResourceProvider,
        listAnimationEnabled: Boolean,
        itemAnimationEnabled: Boolean,
    ) {
        super.onBindView(activity, location, provider, listAnimationEnabled, itemAnimationEnabled)
        mLocation = location
        if (itemView is MaterialCardView) {
            (itemView as MaterialCardView).apply {
                elevation = context.dpToPx(2f)
            }
        }
        val params = itemView.layoutParams as MarginLayoutParams
        params.setMargins(
            context.resources.getDimensionPixelSize(R.dimen.small_margin),
            context.resources.getDimensionPixelSize(R.dimen.small_margin),
            context.resources.getDimensionPixelSize(R.dimen.small_margin),
            context.resources.getDimensionPixelSize(R.dimen.small_margin)
        )
        itemView.layoutParams = params
    }

    @CallSuper
    open fun onBindView(
        activity: BreezyActivity,
        location: Location,
        provider: ResourceProvider,
        listAnimationEnabled: Boolean,
        itemAnimationEnabled: Boolean,
        selectedTab: String?,
        setSelectedTab: (String?) -> Unit,
    ) {
        onBindView(activity, location, provider, listAnimationEnabled, itemAnimationEnabled)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBindView(
        context: Context,
        location: Location,
        provider: ResourceProvider,
        listAnimationEnabled: Boolean,
        itemAnimationEnabled: Boolean,
    ) {
        throw RuntimeException("Deprecated method.")
    }
}
