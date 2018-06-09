package com.adaptionsoft.games.uglytrivia

class Players {
    val players = ArrayList<Player>()

    fun count(): Int = players.size

    fun add(player: Player) {
        players.add(player)
    }
}