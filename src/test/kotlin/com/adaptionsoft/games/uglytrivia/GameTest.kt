package com.adaptionsoft.games.uglytrivia

import com.adaptionsoft.games.trivia.runner.GameRunner
import com.adaptionsoft.games.trivia.runner.main
import org.junit.Assert.*
import org.junit.Test
import java.io.*
import java.util.*


fun run() {
    val aGame = Game()

    aGame.add("Chet")
    aGame.add("Pat")
    aGame.add("Sue")

    val rand = Random(1)

    do {

        aGame.roll(rand.nextInt(5) + 1)

        if (rand.nextInt(9) == 7) {
            GameRunner.notAWinner = aGame.wrongAnswer()
        } else {
            GameRunner.notAWinner = aGame.wasCorrectlyAnswered()
        }


    } while (GameRunner.notAWinner)

}


class GameTest {
    @Test
    fun golden_master() {
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        val old = System.out
        System.setOut(ps)

        run()

        val actual = baos.toString()

        System.out.flush()
        System.setOut(old)

        val inputStream: InputStream = File("out.txt").inputStream()
        val expected = inputStream.bufferedReader().use { it.readText() }

        assertEquals(expected, actual)

    }
}