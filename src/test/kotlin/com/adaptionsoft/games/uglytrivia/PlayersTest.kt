package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.assertEquals
import org.junit.Test

class PlayersTest {
    private val players = Players()

    @Test
    fun `has no players by default`() {
        assertEquals(0, players.count())
    }

    @Test
    fun `can add a player`() {
        players.add(Player("Joe"))

        assertEquals(1, players.count())
    }
}