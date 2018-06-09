package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.*
import org.junit.Test

class RollTest {
    @Test
    fun `isEven is false for odd value`() {
        assertFalse(Roll(1).isEven())
        assertFalse(Roll(3).isEven())
    }

    @Test
    fun `isEven is true for even value`() {
        assertTrue(Roll(2).isEven())
    }
}