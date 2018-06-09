package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.*
import org.junit.Test

class PlayerTest {
    @Test
    fun playerStartsInPlaceZero() {
        val player = Player("Who Cares")

        assertEquals(0, player.place)
    }

    @Test
    fun moveMovesThePlayerForwardFromSpaceZero() {
        val player = Player("Who Cares")

        player.move(Roll(3))

        assertEquals(3, player.place)
    }

    @Test
    fun moveMovesThePlayerFrowardFromNonZeroPlace() {
        val player = Player("Who Cares")

        player.move(Roll(3))
        player.move(Roll(2))

        assertEquals(5, player.place)
    }

    @Test
    fun moveWrapsAroundTheBoard() {
        val player = Player("Who Cares")

        player.move(Roll(6))
        player.move(Roll(5))
        player.move(Roll(3))

        assertEquals(2, player.place)
    }
}