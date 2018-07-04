package com.adaptionsoft.games.uglytrivia

class GameCore(private val ui : UI)
{
    private val popCategory = QuestionCategory("Pop")
    private val scienceCategory = QuestionCategory("Science")
    private val sportsCategory = QuestionCategory("Sports")
    private val rockCategory = QuestionCategory("Rock")

    private val penaltyBox = PenaltyBox(ui)

    fun playTurn(player: Player, play: Game.Play): Boolean {
        ui.playerRolledMessage(player, play.roll)

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
        ui.playerMovedMessage(player, currentCategory(player))

        askQuestion(player)

        if (play.answeredCorrectly) {
            if (!penaltyBox.contains(player)) {
                player.incrementScore()
                ui.playerAnsweredCorrectlyMessage(player)
            }
        } else {
            ui.playerAnsweredIncorrectlyMessage(player)
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
}
