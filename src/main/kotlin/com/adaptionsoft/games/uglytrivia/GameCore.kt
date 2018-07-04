package com.adaptionsoft.games.uglytrivia

class GameCore : UI
{
    private val popCategory = QuestionCategory("Pop")
    private val scienceCategory = QuestionCategory("Science")
    private val sportsCategory = QuestionCategory("Sports")
    private val rockCategory = QuestionCategory("Rock")

    private val penaltyBox = PenaltyBox(this)

    fun playTurn(player: Player, play: Game.Play): Boolean {
        playerRolledMessage(player, play.roll)

        // This is a bug in the logic
        if (player.hasEvenBeenInThePenaltyBox) {
            penaltyBox.add(player)
        }

        if (penaltyBox.contains(player)) {
            penaltyBox.attemptEscape(player, play.roll)
        }

        if (penaltyBox.contains(player)) {
            return false
        }

        player.move(play.roll)
        playerMovedMessage(player)

        askQuestion(player)

        if (play.answeredCorrectly) {
            if (!penaltyBox.contains(player)) {
                player.incrementScore()
                playerAnsweredCorrectlyMessage(player)
            }
        } else {
            playerAnsweredIncorrectlyMessage(player)
            player.hasEvenBeenInThePenaltyBox = true
            penaltyBox.add(player)
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

    override fun gettingOutOfPenaltyBoxMessage(currentPlayer: Player) {
        println("${currentPlayer.name} is getting out of the penalty box")
    }

    override fun stuckInPenaltyBoxMessage(currentPlayer: Player) {
        println("${currentPlayer.name} is not getting out of the penalty box")
    }
}
