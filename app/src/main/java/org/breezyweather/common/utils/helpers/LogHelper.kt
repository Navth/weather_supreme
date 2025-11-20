package org.breezyweather.common.utils.helpers

import android.util.Log

object LogHelper {
    private const val TAG = "BreezyWeather"

    fun log(tag: String? = TAG, msg: String) {
        Log.d(tag, msg)
    }
}
