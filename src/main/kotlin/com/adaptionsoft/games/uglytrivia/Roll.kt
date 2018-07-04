package com.adaptionsoft.games.uglytrivia

data class Roll(val value:Int) {
    fun isEven(): Boolean = (value % 2) == 0
}