package com.adaptionsoft.games.uglytrivia

class Roll(val value:Int) {
    fun isEven(): Boolean = (value % 2) == 0
}