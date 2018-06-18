package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.*
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

//    @Test
//    fun `knows current player`() {
//        players.add(Player("Joe"))
//
//        assertEquals(players.getCurrentPlayer(), Player("Joe"))
//    }
//
//    @Test
//    fun `moves to next player`() {
//        players.add(Player("Joe"))
//        players.add(Player("Mike"))
//        players.nextPlayer()
//
//        assertEquals(players.getCurrentPlayer(), Player("Mike"))
//    }
//
//    @Test
//    fun `moves back to initial player`() {
//        players.add(Player("Joe"))
//        players.add(Player("Mike"))
//        players.nextPlayer()
//        players.nextPlayer()
//
//        assertEquals(players.getCurrentPlayer(), Player("Joe"))
//    }

    @Test
    fun `playTurn plays the first player`() {
        players.add(Player("Joe"))
        players.add(Player("Mike"))

        var firstPlayer: Player? = null
        players.playTurn { firstPlayer = it; true }

        assertEquals(firstPlayer, Player("Joe"))
    }

    @Test
    fun `playTurn moves to the next player`() {
        players.add(Player("Joe"))
        players.add(Player("Mike"))

        players.playTurn { true }

        var secondPlayer: Player? = null
        players.playTurn { secondPlayer = it; true }

        assertEquals(secondPlayer, Player("Mike"))
    }

    @Test
    fun `playTurn loops back to the first player`() {
        players.add(Player("Joe"))
        players.add(Player("Mike"))

        players.playTurn { true }
        players.playTurn { true }

        var thirdPlayer: Player? = null
        players.playTurn { thirdPlayer = it; true }

        assertEquals(thirdPlayer, Player("Joe"))
    }

    @Test
    fun `playTurn returns true if the turn lamda returns true`() {
        players.add(Player("Joe"))

        assertTrue(players.playTurn { true })
    }


    @Test
    fun `playTurn returns false if the turn lamda returns false`() {
        players.add(Player("Joe"))

        assertFalse(players.playTurn { false })
    }
}