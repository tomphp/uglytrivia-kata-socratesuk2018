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

    @Test
    fun `knows current player`() {
        players.add(Player("Joe"))

        assertEquals(players.getCurrentPlayer(), Player("Joe"))
    }

    @Test
    fun `moves to next player`() {
        players.add(Player("Joe"))
        players.add(Player("Mike"))
        players.nextPlayer()

        assertEquals(players.getCurrentPlayer(), Player("Mike"))
    }

    @Test
    fun `moves back to initial player`() {
        players.add(Player("Joe"))
        players.add(Player("Mike"))
        players.nextPlayer()
        players.nextPlayer()

        assertEquals(players.getCurrentPlayer(), Player("Joe"))
    }
}