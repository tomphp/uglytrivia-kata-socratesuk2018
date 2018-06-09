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

        player.move(3)

        assertEquals(3, player.place)
    }

    @Test
    fun moveMovesThePlayerFrowardFromNonZeroPlace() {
        val player = Player("Who Cares")

        player.move(3)
        player.move(2)

        assertEquals(5, player.place)
    }

    @Test
    fun moveWrapsAroundTheBoard() {
        val player = Player("Who Cares")

        player.move(6)
        player.move(5)
        player.move(3)

        assertEquals(2, player.place)
    }
}