package games.gameOfFifteen

import kotlin.random.Random

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation: List<Int> by lazy {
        var list = mutableListOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15).shuffled().toMutableList()
        if (isEven(list)) list
        else {
            var i= Random.nextInt(14)
            var j= if (i!=1) i-1 else i+1
            var res = list[i]
            list[i] = list[j]
            list[j] = res
            list

        }
 }
}



