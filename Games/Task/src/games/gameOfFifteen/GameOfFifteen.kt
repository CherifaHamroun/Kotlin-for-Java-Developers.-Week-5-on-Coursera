package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game
import games.game2048.moveValues
import games.game2048.moveValuesInRowOrColumn

class  GameOfFifteen(val initializer: GameOfFifteenInitializer):Game {
    var init : MutableList<Int?> = initializer.initialPermutation.toMutableList()
    override fun initialize() {
        val i : Int? = null
        init = init.plus(i).toMutableList()
    }

    override fun canMove(): Boolean {
        return this.init.filter { it == null }.size == 0
    }

    override fun hasWon(): Boolean {
        var boo = true
        var i = 0
        var list = init.filterNotNull()
        while (i<list.size){ if (list[i]!=i+1) boo = false
        i+=1}
        return boo
    }

    override fun processMove(direction: Direction) {
        for (i in 1..4){
            for (j in 1..4){
                if (init[(i-1)*4+(j-1)] == null) {
                    when(direction){
                        Direction.UP->{
                            if (i != 4) {
                                this.init[(i-1)*4+(j-1)] = this.init[(i)*4+(j-1)]
                                this.init[(i)*4+(j-1)] = null
                                return
                            }

                        }
                        Direction.DOWN ->{
                            if (i != 1) {
                                this.init[(i-1)*4+(j-1)] = this.init[(i-2)*4+(j-1)]
                                this.init[(i-2)*4+(j-1)] = null
                                return
                            }

                        }
                        Direction.RIGHT->{

                            if (j != 1) {

                                this.init[(i-1)*4+(j-1)] = this.init[(i-1)*4+(j-2)]
                                this.init[(i-1)*4+(j-2)] = null
                                return
                            }

                        }
                        Direction.LEFT->{
                            if (j != 4) {
                                this.init[(i-1)*4+(j-1)] = this.init[(i-1)*4+(j)]
                                this.init[(i-1)*4+(j)] = null
                                return
}
                        }
                    }
                }
            }
        }
    }

    override fun get(i: Int, j: Int): Int? {
        return init[(i-1)*4+(j-1)]
    }
    operator fun set(i:Int,j: Int,value : Int) {
        this.init[(i-1)*4+(j-1)]=value
    }

}
/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
   GameOfFifteen(initializer)

