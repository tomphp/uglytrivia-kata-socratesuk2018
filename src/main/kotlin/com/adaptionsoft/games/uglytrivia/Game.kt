package com.adaptionsoft.games.uglytrivia

import java.util.*

class Game {

    var players = Players()

    var popQuestions = LinkedList<Any>()
    var scienceQuestions = LinkedList<Any>()
    var sportsQuestions = LinkedList<Any>()
    var rockQuestions = LinkedList<Any>()

    var currentPlayer = 0
    var isGettingOutOfPenaltyBox: Boolean = false

    init {
        for (i in 0..49) {
            popQuestions.addLast("Pop Question " + i)
            scienceQuestions.addLast("Science Question " + i)
            sportsQuestions.addLast("Sports Question " + i)
            rockQuestions.addLast("Rock Question " + i)
        }
    }

    fun add(playerName: String): Boolean {
        players.add(Player(playerName))
        println(playerName + " was added")
        println("They are player number " + players.count())
        return true
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        println(players.players[currentPlayer].name + " is the current player")
        println("They have rolled a " + roll.value)

        if (players.players[currentPlayer].inPenaltyBox) {
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
        println(players.players[currentPlayer].name + " is not getting out of the penalty box")
        isGettingOutOfPenaltyBox = false
    }

    private fun handleOddRollWhenInPenaltyBox(roll: Roll) {
        isGettingOutOfPenaltyBox = true

        println(players.players[currentPlayer].name + " is getting out of the penalty box")
        move(roll)
    }

    private fun move(roll: Roll) {
        players.players[currentPlayer].move(roll)

        println(players.players[currentPlayer].name
                + "'s new location is "
                + players.players[currentPlayer].place)
        println("The category is " + currentCategory())
        askQuestion()
    }

    private fun askQuestion() {
        if (currentCategory() === "Pop")
            println(popQuestions.removeFirst())
        if (currentCategory() === "Science")
            println(scienceQuestions.removeFirst())
        if (currentCategory() === "Sports")
            println(sportsQuestions.removeFirst())
        if (currentCategory() === "Rock")
            println(rockQuestions.removeFirst())
    }

    private fun currentCategory(): String = when (players.players[currentPlayer].place) {
        0, 4, 8 -> "Pop"
        1, 5, 9 -> "Science"
        2, 6, 10 -> "Sports"
        else -> "Rock"
    }

    fun wasCorrectlyAnswered(): Boolean {
        return if (players.players[currentPlayer].inPenaltyBox && !isGettingOutOfPenaltyBox) {
            nextPlayer()
            true
        } else {
            handleCorrectAnswer()
        }
    }

    private fun handleCorrectAnswer(): Boolean {
        val player = players.players[currentPlayer]

        println("Answer was correct!!!!")
        player.incrementScore()
        println("${player.name} now has ${player.purse} Gold Coins.")

        nextPlayer()

        return !player.isWinner()
    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println(players.players[currentPlayer].name + " was sent to the penalty box")
        players.players[currentPlayer].inPenaltyBox = true

        nextPlayer()
        return true
    }

    private fun nextPlayer() {
        currentPlayer++
        if (currentPlayer == players.count())
            currentPlayer = 0
    }
}