package com.adaptionsoft.games.uglytrivia

class Game {
    var players = Players()

    val popCategory = QuestionCategory("Pop")
    val scienceCategory = QuestionCategory("Science")
    val sportsCategory = QuestionCategory("Sports")
    val rockCategory = QuestionCategory("Rock")

    var isGettingOutOfPenaltyBox: Boolean = false

    fun add(playerName: String): Boolean {
        players.add(Player(playerName))
        println(playerName + " was added")
        println("They are player number " + players.count())
        return true
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        println(players.getCurrentPlayer().name + " is the current player")
        println("They have rolled a " + roll.value)

        if (players.getCurrentPlayer().inPenaltyBox) {
            if (roll.isEven()) {
                handleEvenRollWhenInPenaltyBox()
            } else {
                handleOddRollWhenInPenaltyBox(roll)
            }
        } else {
            move(roll)
        }
    }

    private fun handleEvenRollWhenInPenaltyBox() {
        println(players.getCurrentPlayer().name + " is not getting out of the penalty box")
        isGettingOutOfPenaltyBox = false
    }

    private fun handleOddRollWhenInPenaltyBox(roll: Roll) {
        isGettingOutOfPenaltyBox = true

        println(players.getCurrentPlayer().name + " is getting out of the penalty box")
        move(roll)
    }

    private fun move(roll: Roll) {
        val currentPlayer = players.getCurrentPlayer()
        currentPlayer.move(roll)

        println("${currentPlayer.name}'s new location is ${currentPlayer.place}")
        println("The category is " + currentCategory().name)
        askQuestion()
    }

    private fun askQuestion() {
        println(currentCategory().questions.removeFirst())
    }

    private fun currentCategory(): QuestionCategory = when (players.getCurrentPlayer().place) {
        0, 4, 8 -> popCategory
        1, 5, 9 -> scienceCategory
        2, 6, 10 -> sportsCategory
        else -> rockCategory
    }

    fun wasCorrectlyAnswered(): Boolean {
        return if (players.getCurrentPlayer().inPenaltyBox && !isGettingOutOfPenaltyBox) {
            players.nextPlayer()
            true
        } else {
            handleCorrectAnswer()
        }
    }

    private fun handleCorrectAnswer(): Boolean {
        val player = players.getCurrentPlayer()

        println("Answer was correct!!!!")
        player.incrementScore()
        println("${player.name} now has ${player.purse} Gold Coins.")

        players.nextPlayer()

        return !player.isWinner()
    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println(players.getCurrentPlayer().name + " was sent to the penalty box")
        players.getCurrentPlayer().inPenaltyBox = true

        players.nextPlayer()
        return true
    }

}