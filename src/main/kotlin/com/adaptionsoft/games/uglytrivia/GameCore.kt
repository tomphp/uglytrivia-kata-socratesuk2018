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

        bugAddPlayerToThePenaltyBoxIfTheyHaveEverBeenThere(player)

        if (!penaltyBox.contains(player)) {
            return playActualTurn(player, play)
        }

        penaltyBox.attemptEscape(player, play.roll)

        if (penaltyBox.contains(player)) {
            return false
        }

        return playActualTurn(player, play)
    }

    private fun playActualTurn(player: Player, play: Game.Play): Boolean {
        move(player, play)

        takeQuestionCard(player)

        answerQuestion(play.answeredCorrectly, player)

        return player.isWinner()
    }

    private fun answerQuestion(answeredCorrectly: Boolean, player: Player) =
        if (answeredCorrectly) {
            answerCorrectly(player)
        } else {
            answerIncorrectly(player)
        }

    private fun takeQuestionCard(player: Player) {
        val card = currentCategory(player).takeCard()
        ui.askQuestion(card)
    }

    private fun move(player: Player, play: Game.Play) {
        player.move(play.roll)
        ui.playerMovedMessage(player, currentCategory(player))
    }

    private fun answerIncorrectly(player: Player) {
        ui.playerAnsweredIncorrectlyMessage(player)
        player.hasEvenBeenInThePenaltyBox = true
        penaltyBox.add(player)
    }

    private fun answerCorrectly(player: Player) {
        player.incrementScore()
        ui.playerAnsweredCorrectlyMessage(player)
    }

    private fun bugAddPlayerToThePenaltyBoxIfTheyHaveEverBeenThere(player: Player) {
        if (player.hasEvenBeenInThePenaltyBox) {
            penaltyBox.add(player)
        }
    }

    private fun currentCategory(player: Player): QuestionCategory = when (player.place) {
        0, 4, 8 -> popCategory
        1, 5, 9 -> scienceCategory
        2, 6, 10 -> sportsCategory
        else -> rockCategory
    }
}
