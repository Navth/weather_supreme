package org.breezyweather.common.extensions

import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat

/**
 * Source: Android Developers, Chris Banes
 * https://medium.com/androiddevelopers/windowinsets-listeners-to-layouts-8f9ccc8fa4d1
 */

/**
 * Apply window insets (system bars and display cutouts) for a view.
 */
fun View.doOnApplyWindowInsets(f: (View, Insets) -> Unit) {
    // Set an actual OnApplyWindowInsetsListener which proxies to the given lambda
    setOnApplyWindowInsetsListener(this) { v, insets ->
        val i = insets.getInsets(
            WindowInsetsCompat.Type.systemBars() + WindowInsetsCompat.Type.displayCutout()
        )
        f(v, i)
        // Always return the insets, so that children can also use them
        insets
    }
    // request some insets
    requestApplyInsetsWhenAttached()
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        // We're already attached, just request as normal
        requestApplyInsets()
    } else {
        // We're not attached to the hierarchy, add a listener to request when we are
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}
