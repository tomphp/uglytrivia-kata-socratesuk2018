package com.adaptionsoft.games.uglytrivia

class GameCore(val players: Players)
{
    private val popCategory = QuestionCategory("Pop")
    private val scienceCategory = QuestionCategory("Science")
    private val sportsCategory = QuestionCategory("Sports")
    private val rockCategory = QuestionCategory("Rock")

    fun playRound(play: Game.Play): Boolean {
        val player = players.getCurrentPlayer()
        playerRolledMessage(player, play.roll)

        playRound(player, play)

        val isWinner = player.isWinner()

        players.nextPlayer()

        return !isWinner
    }

    private fun playRound(player: Player, play: Game.Play) {
        var inPenaltyBox = player.inPenaltyBox

        if (inPenaltyBox) {
            if (play.roll.isEven()) {
                stuckInPenaltyBoxMessage(player)
                return
            }

            gettingOutOfPenaltyBoxMessage(player)
            inPenaltyBox = false
        }

        player.move(play.roll)
        playerMovedMessage(player)

        askQuestion()

        if (play.answeredCorrectly) {
            if (!inPenaltyBox) {
                player.incrementScore()
                playerAnsweredCorrectlyMessage(player)
            }
        } else {
            playerAnsweredIncorrectlyMessage()
            players.getCurrentPlayer().inPenaltyBox = true
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
