package org.breezyweather.ui.alert

import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.breezyweather.common.activities.BreezyActivity

@AndroidEntryPoint
class AlertActivity : BreezyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AlertScreen(
                onBackPressed = {
                    finish()
                }
            )
        }
    }

    companion object {
        const val KEY_FORMATTED_ID = "formatted_id"
        const val KEY_ALERT_ID = "alert_id"
    }
}
