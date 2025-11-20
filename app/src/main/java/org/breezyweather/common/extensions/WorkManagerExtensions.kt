package org.breezyweather.common.extensions

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.delay
import org.breezyweather.common.utils.helpers.LogHelper

/**
 * Taken from Mihon
 * Apache License, Version 2.0
 *
 * https://github.com/mihonapp/mihon/blob/aa498360db90350f2642e6320dc55e7d474df1fd/app/src/main/java/eu/kanade/tachiyomi/util/system/WorkManagerExtensions.kt
 */

val Context.workManager: WorkManager
    get() = WorkManager.getInstance(this)

/**
 * Makes this worker run in the context of a foreground service.
 *
 * Note that this function is a no-op if the process is subject to foreground
 * service restrictions.
 *
 * Moving to foreground service context requires the worker to run a bit longer,
 * allowing Service.startForeground() to be called and avoiding system crash.
 */
suspend fun CoroutineWorker.setForegroundSafely() {
    try {
        setForeground(getForegroundInfo())
        delay(500)
    } catch (e: IllegalStateException) {
        LogHelper.log(msg = "Not allowed to set foreground job")
    }
}
fun WorkManager.isRunning(tag: String): Boolean {
    val list = getWorkInfosByTag(tag).get()
    return list.any { it.state == WorkInfo.State.RUNNING }
}
