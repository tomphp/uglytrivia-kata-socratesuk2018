package com.adaptionsoft.games.uglytrivia

class GameEngine(val players: Players)
{
    private val popCategory = QuestionCategory("Pop")
    private val scienceCategory = QuestionCategory("Science")
    private val sportsCategory = QuestionCategory("Sports")
    private val rockCategory = QuestionCategory("Rock")

    private var isGettingOutOfPenaltyBox: Boolean = false

    fun answer(play: Game.Play): Boolean {
        val currentPlayer = players.getCurrentPlayer()
        playerRolledMessage(currentPlayer, play.roll)

        if (currentPlayer.inPenaltyBox && play.roll.isEven()) {
            stuckInPenaltyBoxMessage(currentPlayer)
            isGettingOutOfPenaltyBox = false
        } else {

            if (currentPlayer.inPenaltyBox) {
                gettingOutOfPenaltyBoxMessage(currentPlayer)
                isGettingOutOfPenaltyBox = true
            }

            currentPlayer.move(play.roll)
            playerMovedMessage(currentPlayer)
            askQuestion()
        }

        if (play.answeredCorrectly) {
            val player = players.getCurrentPlayer()

            if (player.inPenaltyBox && !isGettingOutOfPenaltyBox) {
                players.nextPlayer()
                return true
            }

            player.incrementScore()
            playerAnsweredCorrectlyMessage(player)
            players.nextPlayer()
            return !player.isWinner()
        } else {
            playerAnsweredIncorrectlyMessage()
            players.getCurrentPlayer().inPenaltyBox = true

            players.nextPlayer()
            return true
        }
    }

    private fun askQuestion() {
        println(currentCategory().takeCard())
    }

    private fun currentCategory(): QuestionCategory = when (players.getCurrentPlayer().place) {
        0, 4, 8 -> popCategory
        1, 5, 9 -> scienceCategory
        2, 6, 10 -> sportsCategory
        else -> rockCategory
    }

    private fun playerRolledMessage(currentPlayer: Player, roll: Roll) {
        println(currentPlayer.name + " is the current player")
        println("They have rolled a " + roll.value)
    }

    private fun playerAnsweredIncorrectlyMessage() {
        println("Question was incorrectly answered")
        println(players.getCurrentPlayer().name + " was sent to the penalty box")
    }

    private fun playerAnsweredCorrectlyMessage(player: Player) {
        println("Answer was correct!!!!")
        println("${player.name} now has ${player.purse} Gold Coins.")
    }

    private fun playerMovedMessage(currentPlayer: Player) {
        println("${currentPlayer.name}'s new location is ${currentPlayer.place}")
        println("The category is " + currentCategory().name)
    }

    private fun gettingOutOfPenaltyBoxMessage(currentPlayer: Player) {
        println("${currentPlayer.name} is getting out of the penalty box")
    }

    private fun stuckInPenaltyBoxMessage(currentPlayer: Player) {
        println("${currentPlayer.name} is not getting out of the penalty box")
    }
}
