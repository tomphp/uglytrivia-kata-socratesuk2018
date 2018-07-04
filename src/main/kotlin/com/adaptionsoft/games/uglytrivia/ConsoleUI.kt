package com.adaptionsoft.games.uglytrivia

class ConsoleUI : UI {
    override fun playerRolledMessage(currentPlayer: Player, roll: Roll) {
        println(currentPlayer.name + " is the current player")
        println("They have rolled a " + roll.value)
    }

    override fun playerAnsweredIncorrectlyMessage(player: Player) {
        println("Question was incorrectly answered")
        println("${player.name} was sent to the penalty box")
    }

    override fun playerAnsweredCorrectlyMessage(player: Player) {
        println("Answer was correct!!!!")
        println("${player.name} now has ${player.purse} Gold Coins.")
    }

    override fun playerMovedMessage(currentPlayer: Player, currentCategory: QuestionCategory) {
        println("${currentPlayer.name}'s new location is ${currentPlayer.place}")
        println("The category is ${currentCategory.name}")
    }

    override fun gettingOutOfPenaltyBoxMessage(currentPlayer: Player) {
        println("${currentPlayer.name} is getting out of the penalty box")
    }

    override fun stuckInPenaltyBoxMessage(currentPlayer: Player) {
        println("${currentPlayer.name} is not getting out of the penalty box")
    }
}