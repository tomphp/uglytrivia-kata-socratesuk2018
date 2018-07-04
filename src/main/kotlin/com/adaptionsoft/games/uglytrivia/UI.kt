package com.adaptionsoft.games.uglytrivia

interface UI {
    fun playerRolledMessage(currentPlayer: Player, roll: Roll)

    fun playerAnsweredIncorrectlyMessage(player: Player)

    fun playerAnsweredCorrectlyMessage(player: Player)

    fun playerMovedMessage(currentPlayer: Player, currentCategory: QuestionCategory)

    fun gettingOutOfPenaltyBoxMessage(player: Player)

    fun stuckInPenaltyBoxMessage(player: Player)

    fun readCard(card: String)
}