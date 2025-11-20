package org.breezyweather.ui.theme.resource.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.annotation.AnyRes

object ResourceUtils {
    @AnyRes
    fun getResId(context: Context, resName: String, type: String): Int {
        return try {
            context.resources.getIdentifier(resName, type, context.packageName)
        } catch (ignored: Exception) {
            0
        }
    }

    fun getDrawableUri(pkgName: String, resType: String, resName: String): Uri {
        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(pkgName)
            .appendPath(resType)
            .appendPath(resName)
            .build()
    }
}
