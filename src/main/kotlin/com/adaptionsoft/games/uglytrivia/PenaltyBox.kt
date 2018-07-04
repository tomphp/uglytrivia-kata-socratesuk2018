package com.adaptionsoft.games.uglytrivia

class PenaltyBox(private val ui: UI) {
    val players = mutableSetOf<Player>()

    fun contains(player: Player): Boolean = players.contains(player)

    fun add(player: Player) = players.add(player)

    fun attemptEscape(player: Player, roll: Roll) =
        if (roll.isEven()) {
            ui.stuckInPenaltyBoxMessage(player)
        } else {
            players.remove(player)
            ui.gettingOutOfPenaltyBoxMessage(player)
        }
}