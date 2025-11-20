package org.breezyweather

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class MatchTest {

    @Test
    fun split() = runTest {
        val text = "dadasd dsad   dad"
        println(text.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().contentToString())
    }
}
