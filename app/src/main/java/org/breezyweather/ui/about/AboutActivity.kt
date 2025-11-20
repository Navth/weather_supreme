
package org.breezyweather.ui.about

import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.breezyweather.common.activities.BreezyActivity
import org.breezyweather.ui.theme.compose.BreezyWeatherTheme

@AndroidEntryPoint
class AboutActivity : BreezyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BreezyWeatherTheme {
                AboutScreen()
            }
        }
    }
}
