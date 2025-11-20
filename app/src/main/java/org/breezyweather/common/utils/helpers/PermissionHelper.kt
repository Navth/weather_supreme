package org.breezyweather.common.utils.helpers

import android.app.Activity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import org.breezyweather.ui.main.utils.StatementManager

object PermissionHelper {

    /**
     * Requests a permission via the default permission dialog and falls back to a custom action if the default
     * dialog cannot be launched because it was previously denied by the user.
     *
     * Source: https://stackoverflow.com/a/50639402
     */
    fun requestPermissionWithFallback(
        activity: Activity,
        permission: String,
        requestCode: Int = 0,
        fallback: () -> Unit,
    ) {
        val statementManager = StatementManager(activity)
        val showRationale = shouldShowRequestPermissionRationale(activity, permission)
        val permissionDenied = statementManager.isPermissionDenied(permission)

        if (!showRationale && permissionDenied) {
            fallback()
        } else {
            requestPermissions(
                activity,
                arrayOf(permission),
                requestCode
            )

            if (showRationale && !permissionDenied) {
                statementManager.setPermissionDenied(permission)
            }
        }
    }
}
