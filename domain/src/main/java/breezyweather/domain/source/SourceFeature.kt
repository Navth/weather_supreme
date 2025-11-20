package breezyweather.domain.source

enum class SourceFeature(
    val id: String,
) {
    FORECAST("forecast"),
    CURRENT("current"),
    AIR_QUALITY("airQuality"),
    POLLEN("pollen"),
    MINUTELY("minutely"),
    ALERT("alert"),
    NORMALS("normals"),
    REVERSE_GEOCODING("reverseGeocoding"),
    ;

    companion object {

        fun getInstance(
            value: String,
        ) = SourceFeature.entries.firstOrNull {
            it.id == value
        }
    }
}
