package breezyweather.domain.source

enum class SourceContinent(
    val id: String,
) {
    WORLDWIDE("worldwide"),
    AFRICA("africa"),
    ASIA("asia"),
    EUROPE("europe"),
    NORTH_AMERICA("north_america"),
    OCEANIA("oceania"),
    SOUTH_AMERICA("south_america"),
    ;

    companion object {

        fun getInstance(
            value: String,
        ) = SourceContinent.entries.firstOrNull {
            it.id == value
        }
    }
}
