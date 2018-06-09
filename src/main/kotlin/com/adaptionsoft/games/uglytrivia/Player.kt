package com.adaptionsoft.games.uglytrivia

data class Player(val name: String, var place: Int = 0, var purse: Int = 0, var inPenaltyBox: Boolean = false) {
    private val BOARD_SIZE = 12

    fun move(roll: Roll) {
        place = (place + roll.value) % BOARD_SIZE
    }

    fun incrementScore() {
        if(isWinner())
            throw CannotIncrementScore()

        purse++
    }

    fun isWinner(): Boolean = purse == 6
}

class CannotIncrementScore : Exception()