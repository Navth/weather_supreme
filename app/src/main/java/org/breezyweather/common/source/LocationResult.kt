package org.breezyweather.common.source

import breezyweather.domain.location.model.Location

class LocationResult(
    val location: Location,
    val errors: List<RefreshError> = emptyList(),
)
