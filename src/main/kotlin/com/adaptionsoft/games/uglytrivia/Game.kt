package com.adaptionsoft.games.uglytrivia

class Game {
    var players = Players()

    private val popCategory = QuestionCategory("Pop")
    private val scienceCategory = QuestionCategory("Science")
    private val sportsCategory = QuestionCategory("Sports")
    private val rockCategory = QuestionCategory("Rock")

    var isGettingOutOfPenaltyBox: Boolean = false

    fun add(playerName: String): Boolean {
        players.add(Player(playerName))
        println(playerName + " was added")
        println("They are player number " + players.count())
        return true
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        val currentPlayer = players.getCurrentPlayer()
        println(currentPlayer.name + " is the current player")
        println("They have rolled a " + roll.value)

        if (stuckInPenaltyBox(currentPlayer, roll)) {
            println("${currentPlayer.name} is not getting out of the penalty box")
            isGettingOutOfPenaltyBox = false
            return
        }
        if (currentPlayer.inPenaltyBox) {
            println("${currentPlayer.name} is getting out of the penalty box")
            isGettingOutOfPenaltyBox = true
        }

        currentPlayer.move(roll)
        println("${currentPlayer.name}'s new location is ${currentPlayer.place}")
        println("The category is " + currentCategory().name)
        askQuestion()
    }

    fun wasCorrectlyAnswered(): Boolean {
        val player = players.getCurrentPlayer()
        return if (player.inPenaltyBox && !isGettingOutOfPenaltyBox) {
            players.nextPlayer()
            true
        } else {
            println("Answer was correct!!!!")
            player.incrementScore()
            println("${player.name} now has ${player.purse} Gold Coins.")
            players.nextPlayer()
            !player.isWinner()
        }
    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println(players.getCurrentPlayer().name + " was sent to the penalty box")
        players.getCurrentPlayer().inPenaltyBox = true

        players.nextPlayer()
        return true
    }

    private fun stuckInPenaltyBox(player: Player, roll: Roll): Boolean {
        return player.inPenaltyBox && roll.isEven()
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
}