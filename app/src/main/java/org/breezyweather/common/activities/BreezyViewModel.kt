package org.breezyweather.common.activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel

// TODO: Issue with getter on application when converted to Kotlin
open class BreezyViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private var mNewInstance = true
    fun checkIsNewInstance(): Boolean {
        val result = mNewInstance
        mNewInstance = false
        return result
    }
}
