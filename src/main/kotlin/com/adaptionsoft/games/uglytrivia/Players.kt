package com.adaptionsoft.games.uglytrivia

class Players {
    private var index = 0
    private val players = ArrayList<Player>()

    fun count(): Int = players.size

    fun add(player: Player) {
        players.add(player)
    }


    fun getCurrentPlayer(): Player {
        return players[index];
    }

    fun nextPlayer() {
        index++
        if (index == count())
            index = 0
    }


}