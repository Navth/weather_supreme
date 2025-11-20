package org.breezyweather.ui.settings.compose

sealed class SettingsScreenRouter(val route: String) {
    object Root : SettingsScreenRouter("org.breezyweather.ui.settings.root")
    object BackgroundUpdates : SettingsScreenRouter("org.breezyweather.ui.settings.background")
    object Location : SettingsScreenRouter("org.breezyweather.ui.settings.location")
    object WeatherProviders : SettingsScreenRouter("org.breezyweather.ui.settings.providers")
    object Appearance : SettingsScreenRouter("org.breezyweather.ui.settings.appearance")
    object MainScreen : SettingsScreenRouter("org.breezyweather.ui.settings.main")
    object Notifications : SettingsScreenRouter("org.breezyweather.ui.settings.notifications")
    object Unit : SettingsScreenRouter("org.breezyweather.ui.settings.unit")
    object Widgets : SettingsScreenRouter("org.breezyweather.ui.settings.widgets")
    object Debug : SettingsScreenRouter("org.breezyweather.ui.settings.debug")
}
