package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.assertEquals
import org.junit.Test

class PlayersTest {
    @Test
    fun `has no players by default`() {
        val players = Players()

        assertEquals(0, players.numberOfPlayers())
    }
}