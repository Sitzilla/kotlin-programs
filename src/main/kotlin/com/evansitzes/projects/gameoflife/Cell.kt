package com.evansitzes.projects.gameoflife

import java.util.Random

class Cell {
    var isAlive: Boolean = false
    val neighbors = ArrayList<Cell>()

    init {
        val random = Random()
        isAlive = random.nextBoolean()
    }

    override fun toString(): String {
        if (isAlive) {
            return "x"
        }
        return " "
    }

    fun isAliveNextTurn(): Boolean {
        var counter = 0

        for (neighbor in neighbors) {
            if (neighbor.isAlive) {
                counter++
            }
        }

        if (!isAlive && counter == 3) {
            return true
        }

        if (!isAlive) {
            return false
        }

        return when (counter) {
            in 0..1 -> false
            in 2..3 -> true
            else -> false
        }
    }
}