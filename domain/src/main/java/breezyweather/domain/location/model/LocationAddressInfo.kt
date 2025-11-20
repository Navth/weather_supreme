package breezyweather.domain.location.model

data class LocationAddressInfo(
    /**
     * Mandatory when used in the location search process
     * In the reverse geocoding process, if provided, will throw an error if the returned location is too far away
     * from the originally provided coordinates
     */
    val latitude: Double? = null,
    /**
     * Mandatory when used in the location search process
     * In the reverse geocoding process, if provided, will throw an error if the returned location is too far away
     * from the originally provided coordinates
     */
    val longitude: Double? = null,
    /**
     * Time zone of the location in the TZ identifier format
     * Examples: America/New_York, Europe/Paris
     *
     * The list of accepted time zones can be found here:
     * https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
     */
    val timeZoneId: String? = null,

    /**
     * Leave null or empty to let the system translates the country name from the countryCode
     */
    val country: String? = null,
    /**
     * Contrary to its name, this code represents not just countries, but also dependent territories, and special areas
     *  of geographical interest
     * Must be a valid ISO 3166-1 alpha-2 code
     * https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
     *
     * We don't support locations in the middle of the ocean, so ensure this is non empty.
     *
     * Will throw an error if it is not a valid 2 alpha letter code
     */
    val countryCode: String,
    val admin1: String? = null,
    /**
     * Can be an ISO 3166-2 code, or the internal code used by the country
     */
    val admin1Code: String? = null,
    val admin2: String? = null,
    /**
     * Can be an ISO 3166-2 code, or the internal code used by the country
     */
    val admin2Code: String? = null,
    val admin3: String? = null,
    /**
     * Can be an ISO 3166-2 code, or the internal code used by the country
     */
    val admin3Code: String? = null,
    val admin4: String? = null,
    /**
     * Can be an ISO 3166-2 code, or the internal code used by the country
     */
    val admin4Code: String? = null,
    val city: String? = null,
    val cityCode: String? = null,
    val district: String? = null,
)
