package com.adaptionsoft.games.uglytrivia

class GameCore()
{
    private val popCategory = QuestionCategory("Pop")
    private val scienceCategory = QuestionCategory("Science")
    private val sportsCategory = QuestionCategory("Sports")
    private val rockCategory = QuestionCategory("Rock")

    fun playTurn(player: Player, play: Game.Play): Boolean {
        playerRolledMessage(player, play.roll)

        var inPenaltyBox = player.inPenaltyBox

        if (inPenaltyBox) {
            if (play.roll.isEven()) {
                stuckInPenaltyBoxMessage(player)
                return false
            }

            gettingOutOfPenaltyBoxMessage(player)
            inPenaltyBox = false
        }

        player.move(play.roll)
        playerMovedMessage(player)

        askQuestion(player)

        if (play.answeredCorrectly) {
            if (!inPenaltyBox) {
                player.incrementScore()
                playerAnsweredCorrectlyMessage(player)
            }
        } else {
            playerAnsweredIncorrectlyMessage(player)
            player.inPenaltyBox = true
        }

        return player.isWinner()
    }

    private fun askQuestion(player: Player) {
        println(currentCategory(player).takeCard())
    }

    private fun currentCategory(player: Player): QuestionCategory = when (player.place) {
        0, 4, 8 -> popCategory
        1, 5, 9 -> scienceCategory
        2, 6, 10 -> sportsCategory
        else -> rockCategory
    }

    private fun playerRolledMessage(currentPlayer: Player, roll: Roll) {
        println(currentPlayer.name + " is the current player")
        println("They have rolled a " + roll.value)
    }

    private fun playerAnsweredIncorrectlyMessage(player: Player) {
        println("Question was incorrectly answered")
        println("${player.name} was sent to the penalty box")
    }

    private fun playerAnsweredCorrectlyMessage(player: Player) {
        println("Answer was correct!!!!")
        println("${player.name} now has ${player.purse} Gold Coins.")
    }

    private fun playerMovedMessage(currentPlayer: Player) {
        println("${currentPlayer.name}'s new location is ${currentPlayer.place}")
        println("The category is " + currentCategory(currentPlayer).name)
    }

    private fun gettingOutOfPenaltyBoxMessage(currentPlayer: Player) {
        println("${currentPlayer.name} is getting out of the penalty box")
    }

    private fun stuckInPenaltyBoxMessage(currentPlayer: Player) {
        println("${currentPlayer.name} is not getting out of the penalty box")
    }
}
