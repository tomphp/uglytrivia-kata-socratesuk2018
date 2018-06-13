package com.adaptionsoft.games.uglytrivia

import com.adaptionsoft.games.trivia.runner.GameRunner
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.io.PrintStream
import java.util.*

class GameTest {
    val game = Game()

    @Test
    fun `the golden master`() {
        val actual = captureStdout { run() }
        val expected = readFileContents("out.txt")

        assertEquals(expected, actual)

    }

    private fun readFileContents(filename: String): String {
        val inputStream: InputStream = File(filename).inputStream()

        return inputStream.bufferedReader().use { it.readText() }
    }

    private fun run() {
        game.add("Chet")
        game.add("Pat")
        game.add("Sue")

        val rand = Random(1)

        do {
            game.roll(rand.nextInt(5) + 1)

            if (rand.nextInt(9) == 7) {
                GameRunner.notAWinner = game.wrongAnswer()
            } else {
                GameRunner.notAWinner = game.wasCorrectlyAnswered()
            }
        } while (GameRunner.notAWinner)
    }

    private fun captureStdout(command: () -> Unit): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val printStream = PrintStream(byteArrayOutputStream)
        val stdout = System.out
        System.setOut(printStream)

        command()

        val output = byteArrayOutputStream.toString()

        System.out.flush()
        System.setOut(stdout)

        return output
    }
}