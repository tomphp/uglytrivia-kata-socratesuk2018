package com.adaptionsoft.games.uglytrivia

class Game {
    var players = Players()

    private var roll = Roll(1) // Rubbish default
    private val gameEngine = GameCore()

    class Play(val roll: Roll, val answeredCorrectly: Boolean = false)

    fun add(playerName: String): Boolean {
        players.add(Player(playerName))
        playerAddedMessage(playerName)
        return true
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        this.roll = roll
    }

    fun wasCorrectlyAnswered(): Boolean {
        return !players.playTurn { gameEngine.playTurn(it, Play(roll, true)) }
    }

    fun wrongAnswer(): Boolean {
        return !players.playTurn { gameEngine.playTurn(it, Play(roll, false)) }
    }

    private fun playerAddedMessage(playerName: String) {
        println(playerName + " was added")
        println("They are player number " + players.count())
    }
}
