package com.adaptionsoft.games.uglytrivia

class Players {
    private var index = 0
    private val players = ArrayList<Player>()

    fun count(): Int = players.size

    fun add(player: Player) {
        players.add(player)
    }

    fun playTurn(turn: (Player) -> Boolean) : Boolean {
        val isWinner = turn(getCurrentPlayer())
        nextPlayer()
        return isWinner
    }

    private fun getCurrentPlayer(): Player {
        return players[index];
    }

    private fun nextPlayer() {
        index = (index + 1) % count()
    }
}