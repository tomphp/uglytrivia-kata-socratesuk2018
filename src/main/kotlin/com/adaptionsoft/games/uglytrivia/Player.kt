package com.adaptionsoft.games.uglytrivia

data class Player(val name: String) {
    private val boardSize = 12

    var place: Int = 0
    var purse: Int = 0
    var inPenaltyBox: Boolean = false

    fun move(roll: Roll) {

        place = (place + roll.value) % boardSize
    }

    fun incrementScore() {
        if(isWinner())
            throw CannotIncrementScore()

        purse++
    }

    fun isWinner(): Boolean = purse == 6
}

class CannotIncrementScore : Exception()