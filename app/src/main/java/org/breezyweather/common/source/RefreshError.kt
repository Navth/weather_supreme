package org.breezyweather.common.source

import android.content.Context
import breezyweather.domain.source.SourceFeature
import org.breezyweather.R
import org.breezyweather.domain.source.resourceName
import org.breezyweather.sources.SourceManager
import org.breezyweather.ui.main.utils.RefreshErrorType

class RefreshError(
    val error: RefreshErrorType,
    val source: String? = null,
    val feature: SourceFeature? = null,
) {
    fun getSourceWithOptionalFeature(context: Context, sourceManager: SourceManager): String? {
        return if (!source.isNullOrEmpty()) {
            val sourceName = sourceManager.getSource(source)?.name ?: source
            if (feature != null) {
                context.getString(R.string.parenthesis, sourceName, context.getString(feature.resourceName))
            } else {
                sourceName
            }
        } else {
            null
        }
    }

    fun getMessage(context: Context, sourceManager: SourceManager): String {
        return if (!source.isNullOrEmpty()) {
            "${getSourceWithOptionalFeature(context, sourceManager)}${context.getString(
                R.string.colon_separator
            )}${context.getString(error.shortMessage)}"
        } else {
            context.getString(error.shortMessage)
        }
    }
}
