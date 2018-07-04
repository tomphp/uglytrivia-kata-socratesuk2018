package com.adaptionsoft.games.uglytrivia

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class PenaltyBoxTest {
    private val ui = mock(UI::class.java)
    private val penaltyBox: PenaltyBox = PenaltyBox(ui)

    @Test
    fun `a player is not in the penalty box by default`() {
        assertFalse(penaltyBox.contains(Player("Tom")))
    }

    @Test
    fun `a player can be added to the penalty box`() {
        penaltyBox.add(Player("Dick"))

        assertTrue(penaltyBox.contains(Player("Dick")))
    }

    @Test
    fun `the penalty box differentiates between players`() {
        penaltyBox.add(Player("Francine"))

        assertFalse(penaltyBox.contains(Player("Harry")))
    }

    @Test
    fun `roll of 1 does escape the penalty box`() {
        val gloria = Player("Gloria")
        penaltyBox.add(gloria)

        penaltyBox.attemptEscape(gloria, Roll(1))

        assertFalse(penaltyBox.contains(gloria))
    }

    @Test
    fun `roll of 2 does not escape the penalty box`() {
        val cuthbert = Player("Cuthbert")
        penaltyBox.add(cuthbert)

        penaltyBox.attemptEscape(cuthbert, Roll(2))

        assertTrue(penaltyBox.contains(cuthbert))
    }

    @Test
    fun `roll of 3 does escape the penalty box`() {
        val margret = Player("Margret")
        penaltyBox.add(margret)

        penaltyBox.attemptEscape(margret, Roll(3))

        assertFalse(penaltyBox.contains(margret))
    }

    @Test
    fun `roll of 4 does not escape the penalty box`() {
        val kevin = Player("Kevin")
        penaltyBox.add(kevin)

        penaltyBox.attemptEscape(kevin, Roll(4))

        assertTrue(penaltyBox.contains(kevin))
    }

    @Test
    fun `roll of 5 does escape the penalty box`() {
        val margret = Player("Bob")
        penaltyBox.add(margret)

        penaltyBox.attemptEscape(margret, Roll(5))

        assertFalse(penaltyBox.contains(margret))
    }
    @Test
    fun `roll of 6 does not escape the penalty box`() {
        val mildrid = Player("Mildrid")
        penaltyBox.add(mildrid)

        penaltyBox.attemptEscape(mildrid, Roll(6))

        assertTrue(penaltyBox.contains(mildrid))
    }

    @Test
    fun `prints a message when the player escapes the penalty box`() {
        val gloria = Player("Gloria")
        penaltyBox.add(gloria)

        penaltyBox.attemptEscape(gloria, Roll(1))

        verify(ui).gettingOutOfPenaltyBoxMessage(gloria)
    }

    @Test
    fun `prints a message when the player fails to escape the penalty box`() {
        val cuthbert = Player("Cuthbert")
        penaltyBox.add(cuthbert)

        penaltyBox.attemptEscape(cuthbert, Roll(2))

        verify(ui).stuckInPenaltyBoxMessage(cuthbert)
    }
}