package com.adaptionsoft.games.uglytrivia

import java.util.*

class QuestionCategory (val name: String){

    var questions = LinkedList<String>()

    init {
        generateDummyQuestions()
    }

    fun addQuestion(question: String) {
        questions.addLast(question)
    }

    fun generateDummyQuestions() {
        for (i in 0..49) {
            addQuestion("$name Question $i")
        }
    }

    fun takeCard(): String {
        return questions.removeFirst()
    }
}
