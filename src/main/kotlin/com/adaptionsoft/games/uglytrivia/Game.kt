package com.adaptionsoft.games.uglytrivia

class Game {
    var players = Players()

    private var play = Play(Roll(1))
    private val gameEngine = GameEngine(players)

    class Play(val roll: Roll, var answeredCorrectly: Boolean = false)

    fun add(playerName: String): Boolean {
        players.add(Player(playerName))
        playerAddedMessage(playerName)
        return true
    }

    fun roll(roll: Int) = roll(Roll(roll))

    private fun roll(roll: Roll) {
        this.play = Play(roll)
    }

    fun wasCorrectlyAnswered(): Boolean {
        play.answeredCorrectly = true

        return gameEngine.answer(play)
    }

    fun wrongAnswer(): Boolean {
        play.answeredCorrectly = false

        return gameEngine.answer(play)
    }

    private fun playerAddedMessage(playerName: String) {
        println(playerName + " was added")
        println("They are player number " + players.count())
    }
}
