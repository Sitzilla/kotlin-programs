package com.evansitzes.projects.gameoflife

fun main (args: Array<String>) {
    val cells = createCells()
    printCells(cells)

    while (true) {
        Thread.sleep(1000)
        println()
        println("==============================================")
        println()
        playOneIteration(cells)
        printCells(cells)
    }
}

fun playOneIteration(cells: Array<Array<Cell>>) {
    val nextTurnState = buildNextState(cells)
    updateState(nextTurnState, cells)
}

fun updateState(nextTurnState: Array<Array<Boolean>>, cells: Array<Array<Cell>>) {
    for (i in cells.indices) {
        for (j in cells[i].indices) {
            cells[i][j].isAlive = nextTurnState[i][j]
        }
    }
}

fun buildNextState(cells: Array<Array<Cell>>): Array<Array<Boolean>> {
    val nextTurnState = Array(cells.size, {Array(cells.size, {false})})

    for (i in cells.indices) {
        for (j in cells[i].indices) {
            nextTurnState[i][j] = cells[i][j].isAliveNextTurn()
        }
    }

    return nextTurnState
}

fun printCells(cells: Array<Array<Cell>>) {
    for(i in cells.indices) {
        for(j in cells[i].indices) {
            print(cells[i][j])
        }
        println()
    }
}

fun createCells(): Array<Array<Cell>> {
    val size = 50
    val cells = Array(size, {Array(size, { Cell() })})

    for(i in cells.indices) {
        for(j in cells[i].indices) {

            if (i > 0) {
                cells[i][j].neighbors.add(cells[i - 1][j])

                if (j > 0) {
                    cells[i][j].neighbors.add(cells[i - 1][j - 1])
                }

                if (j < size - 1) {
                    cells[i][j].neighbors.add(cells[i - 1][j + 1])
                }
            }

            if (j > 0) {
                cells[i][j].neighbors.add(cells[i][j - 1])
            }

            if (i < size - 1) {
                cells[i][j].neighbors.add(cells[i + 1][j])

                if (j > 0) {
                    cells[i][j].neighbors.add(cells[i +  1][j - 1])
                }

                if (j < size - 1) {
                    cells[i][j].neighbors.add(cells[i + 1][j + 1])
                }
            }

            if (j < size - 1) {
                cells[i][j].neighbors.add(cells[i][j + 1])
            }
        }
    }

    return cells
}