package org.breezyweather.ui.theme.resource

import android.content.Context
import org.breezyweather.BreezyWeather
import org.breezyweather.domain.settings.SettingsManager
import org.breezyweather.ui.theme.resource.providers.ChronusResourceProvider
import org.breezyweather.ui.theme.resource.providers.DefaultResourceProvider
import org.breezyweather.ui.theme.resource.providers.IconPackResourcesProvider
import org.breezyweather.ui.theme.resource.providers.ResourceProvider

object ResourcesProviderFactory {
    val newInstance: ResourceProvider
        get() = getNewInstance(SettingsManager.getInstance(BreezyWeather.instance).iconProvider)

    fun getNewInstance(packageName: String?): ResourceProvider {
        val context: Context = BreezyWeather.instance
        val defaultProvider = DefaultResourceProvider()
        if (packageName == null || DefaultResourceProvider.isDefaultIconProvider(packageName)) {
            return defaultProvider
        }
        if (IconPackResourcesProvider.isIconPackIconProvider(context, packageName)) {
            return IconPackResourcesProvider(context, packageName, defaultProvider)
        }
        return if (ChronusResourceProvider.isChronusIconProvider(context, packageName)) {
            ChronusResourceProvider(context, packageName, defaultProvider)
        } else {
            IconPackResourcesProvider(
                context,
                packageName,
                defaultProvider
            )
        }
    }

    fun getProviderList(context: Context): List<ResourceProvider> {
        val providerList = mutableListOf<ResourceProvider>()
        val defaultProvider = DefaultResourceProvider()
        providerList.add(defaultProvider)

        // Breezy Weather + Geometric Weather icon providers
        providerList.addAll(IconPackResourcesProvider.getProviderList(context, defaultProvider))

        // chronus icon pack.
        val chronusIconPackList = ChronusResourceProvider.getProviderList(context, defaultProvider).toMutableList()
        for (i in chronusIconPackList.indices.reversed()) {
            for (resourceProvider in providerList) {
                if (chronusIconPackList[i] == resourceProvider) {
                    chronusIconPackList.removeAt(i)
                    break
                }
            }
        }
        providerList.addAll(chronusIconPackList)
        return providerList
    }
}
