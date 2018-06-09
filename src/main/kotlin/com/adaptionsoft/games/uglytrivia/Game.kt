package com.adaptionsoft.games.uglytrivia

import java.util.*
import kotlin.collections.ArrayList

class Game {
    var players = ArrayList<Player>()

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
            rockQuestions.addLast(createRockQuestion(i))
        }
    }

    fun createRockQuestion(index: Int): String {
        return "Rock Question " + index
    }

    fun add(playerName: String): Boolean {
        players.add(Player(playerName))
        println(playerName + " was added")
        println("They are player number " + howManyPlayers())
        return true
    }

    fun howManyPlayers(): Int {
        return players.size
    }

    fun roll(roll: Int) {
        val r = Roll(roll)

        println(players[currentPlayer].name + " is the current player")
        println("They have rolled a " + r.value)

        if (players[currentPlayer].inPenaltyBox) {
            if (!r.isEven()) {
                handleOddRollWhenInPenaltyBox(r)
            } else {
                handleEvenRollWhenInPenaltyBox()
            }
        } else {
            move(r)
        }
    }

    private fun handleEvenRollWhenInPenaltyBox() {
        println(players[currentPlayer].name + " is not getting out of the penalty box")
        isGettingOutOfPenaltyBox = false
    }

    private fun handleOddRollWhenInPenaltyBox(roll: Roll) {
        isGettingOutOfPenaltyBox = true

        println(players[currentPlayer].name + " is getting out of the penalty box")
        move(roll)
    }

    private fun move(roll: Roll) {
        players[currentPlayer].move(roll)

        println(players[currentPlayer].name
                + "'s new location is "
                + players[currentPlayer].place)
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

    private fun currentCategory(): String = when (players[currentPlayer].place) {
        0, 4, 8 -> "Pop"
        1, 5, 9 -> "Science"
        2, 6, 10 -> "Sports"
        else -> "Rock"
    }

    fun wasCorrectlyAnswered(): Boolean {
        return if (players[currentPlayer].inPenaltyBox && !isGettingOutOfPenaltyBox) {
            nextPlayer()
            true
        } else {
            handleCorrectAnswer()
        }
    }

    private fun handleCorrectAnswer(): Boolean {
        println("Answer was correct!!!!")
        players[currentPlayer].purse++
        println(players[currentPlayer].name
                + " now has "
                + players[currentPlayer].purse
                + " Gold Coins.")

        val winner = didPlayerWin()
        nextPlayer()

        return winner
    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println(players[currentPlayer].name + " was sent to the penalty box")
        players[currentPlayer].inPenaltyBox = true

        nextPlayer()
        return true
    }

    private fun nextPlayer() {
        currentPlayer++
        if (currentPlayer == howManyPlayers())
            currentPlayer = 0
    }

    private fun didPlayerWin(): Boolean {
        val winningNUmberOfCoins = 6
        return players[currentPlayer].purse != winningNUmberOfCoins
    }
}