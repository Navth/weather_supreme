package org.breezyweather.background.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.WorkInfo
import androidx.work.WorkQuery
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.breezyweather.common.extensions.workManager
import org.breezyweather.remoteviews.presenters.notification.WidgetNotificationIMP
import org.breezyweather.sources.RefreshHelper
import javax.inject.Inject

/**
 * Receiver to force app to autostart on boot
 * Does nothing, itâ€™s just that some OEM do not respect Android policy to keep scheduled workers
 * regardless of if the app is started or not
 */
@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var refreshHelper: RefreshHelper

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action.isNullOrEmpty()) return
        when (action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                /**
                 * We donâ€™t use the return value, but querying the work manager might help bringing back
                 * scheduled workers after the app has been killed/shutdown on some devices
                 */
                context.workManager.getWorkInfosFlow(WorkQuery.fromStates(WorkInfo.State.ENQUEUED))

                // Bring back notification-widget if necessary
                if (WidgetNotificationIMP.isEnabled(context)) {
                    GlobalScope.launch(Dispatchers.IO) {
                        refreshHelper.updateNotificationIfNecessary(context)
                    }
                }
            }
        }
    }
}
