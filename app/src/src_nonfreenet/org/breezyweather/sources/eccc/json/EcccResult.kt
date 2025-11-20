package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccResult(
    val displayName: String?,
    val alert: EcccAlerts?,
    val observation: EcccObservation?,
    val hourlyFcst: EcccHourlyFcst?,
    val dailyFcst: EcccDailyFcst?,
    /**
     * Not used as it is unclear to which day each rise data applies to
     * Especially around midnight
     * Or when you have something like that:
     *  {
     *      "set": {
     *          "time12h": "",
     *          "epochTimeRounded": "",
     *          "time": ""
     *      },
     *      "sunStatus": "alwaysUp",
     *      "timeZone": "CDT",
     *      "rise": {
     *          "time12h": "",
     *          "epochTimeRounded": "",
     *          "time": ""
     *      }
     *  }
     */
    // val riseSet: EcccRiseSet?,
    // val riseSetNextDay: EcccRiseSet?,
    // val riseData: List<EcccRiseSet>?,
)
