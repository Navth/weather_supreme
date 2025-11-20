package org.breezyweather.common.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import org.breezyweather.BreezyWeather
import org.breezyweather.common.extensions.isDarkMode
import org.breezyweather.common.extensions.setSystemBarStyle
import org.breezyweather.common.snackbar.SnackbarContainer

abstract class BreezyActivity : AppCompatActivity() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            window.setSystemBarStyle(!isDarkMode)
        }

        BreezyWeather.instance.addActivity(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        BreezyWeather.instance.setTopActivity(this)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        BreezyWeather.instance.setTopActivity(this)
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        BreezyWeather.instance.checkToCleanTopActivity(this)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        BreezyWeather.instance.removeActivity(this)
    }

    fun updateLocalNightMode(expectedLightTheme: Boolean) {
        getDelegate().localNightMode = if (expectedLightTheme) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
    }

    open val snackbarContainer: SnackbarContainer
        get() = SnackbarContainer(
            this,
            findViewById<ViewGroup>(android.R.id.content).getChildAt(0) as ViewGroup,
            true
        )

    fun provideSnackbarContainer(): SnackbarContainer = snackbarContainer

    val isActivityCreated: Boolean
        get() = lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)
    val isActivityStarted: Boolean
        get() = lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    val isActivityResumed: Boolean
        get() = lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)
}
