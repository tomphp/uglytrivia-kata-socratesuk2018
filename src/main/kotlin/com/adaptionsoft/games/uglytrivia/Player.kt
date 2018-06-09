package com.adaptionsoft.games.uglytrivia

data class Player(val name: String, var place: Int = 0, var purse: Int = 0, var inPenaltyBox: Boolean = false) {
    private val BOARD_SIZE = 12

    fun move(roll: Int) {
        place = (place + roll) % BOARD_SIZE
    }
}