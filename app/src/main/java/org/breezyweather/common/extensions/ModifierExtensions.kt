package org.breezyweather.common.extensions

import androidx.compose.ui.Modifier

/**
 * Source: ProAndroidDev
 * https://proandroiddev.com/jetpack-compose-tricks-conditionally-applying-modifiers-for-dynamic-uis-e3fe5a119f45
 */
inline fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this },
): Modifier = if (condition) {
    then(ifTrue(Modifier))
} else {
    then(ifFalse(Modifier))
}
