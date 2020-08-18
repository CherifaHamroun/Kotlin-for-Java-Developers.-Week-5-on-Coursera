package games.game2048

import board.*
import games.game.Game

/*
 * Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
fun newGame2048(initializer: Game2048Initializer<Int> = RandomGame2048Initializer): Game =
        Game2048(initializer)

class Game2048(private val initializer: Game2048Initializer<Int>) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

/*
 * Add a new value produced by 'initializer' to a specified cell in a board.
 */
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>) {
    val freeCell = initializer.nextValue(this)
    if (freeCell != null) if (this[freeCell.first]==null) this[freeCell.first] = freeCell.second

}

/*
 * Update the values stored in a board,
 * so that the values were "moved" in a specified rowOrColumn only.
 * Use the helper function 'moveAndMergeEqual' (in Game2048Helper.kt).
 * The values should be moved to the beginning of the row (or column),
 * in the same manner as in the function 'moveAndMergeEqual'.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    var listInts = listOf<Int?>()
    var j = 0
    while (j<rowOrColumn.size) {
        listInts+= this[rowOrColumn[j]]
        j+=1
    }
    val list = listInts.moveAndMergeEqual { it }
    if (list.egaler(listInts)) return false
    else {
        var i = 0
        while (i < list.size) {
            this[rowOrColumn[i]] = list[i]
            i+=1
        }
        while (i<rowOrColumn.size) {
            this[rowOrColumn[i]] = null
            i+=1
        }
        return true
    }

}

private fun <E> List<E>.egaler(listInts: List<E?>): Boolean {
    var i = 0
    var b = true
 while (i<this.size && b){
     if (this[i] != listInts[i]) b = false
     i++
 }
    return b
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" to the specified direction
 * following the rules of the 2048 game .
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction?): Boolean =
        when (direction) {
        Direction.UP -> {
            var ret :Boolean = false
            for (i in 1 .. 4) {
            var j = this.moveValuesInRowOrColumn(this.getColumn(1 .. 4, i))
            ret = ret || j
        }
             ret
        }
        Direction.DOWN -> {
            var ret :Boolean = false
            for (i in 1..4) {
                var j = this.moveValuesInRowOrColumn(this.getColumn(4 downTo 1, i))
                ret = ret || j
        }
             ret
        }
        Direction.LEFT -> {
            var ret :Boolean = false
            for (i in 1..4) {
                var j = this.moveValuesInRowOrColumn(this.getRow(i, 1..4))
                ret = ret || j
            }
            ret
        }
        Direction.RIGHT -> {
            var ret :Boolean = false
            for (i in 1..4) {
                var j = this.moveValuesInRowOrColumn(this.getRow(i, 4 downTo 1))
                ret = ret || j
                println(ret)
            }
            ret
    }
            else -> false
    }


