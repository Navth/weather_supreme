package org.breezyweather.common.preference

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType

class EditTextPreference(
    @StringRes override val titleId: Int,
    val summary: ((Context, String) -> String?)? = null,
    val content: String?,
    val placeholder: String? = null,
    val regex: Regex? = null,
    val regexError: String? = null,
    val keyboardType: KeyboardType? = null,
    val onValueChanged: (String) -> Unit,
) : Preference {

    companion object {
        val URL_REGEX = Regex(
            "^https://(www[.])?[-a-zA-Z0-9@:%._+~#=]{1,256}[.][a-zA-Z0-9()]{1,6}([-a-zA-Z0-9()!@:%_+.~#?&//=]*)/$"
        )
    }
}
