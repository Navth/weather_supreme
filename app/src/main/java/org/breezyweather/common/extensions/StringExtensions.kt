package org.breezyweather.common.extensions

/**
 * Allow to split a string while keeping delimiters
 */
fun String.splitKeeping(str: String): List<String> {
    return split(str).flatMap { listOf(it, str) }.dropLast(1).filterNot { it.isEmpty() }
}

fun String.splitKeeping(vararg strs: String): List<String> {
    var res = listOf(this)
    strs.forEach { str ->
        res = res.flatMap { it.splitKeeping(str) }
    }
    return res
}
