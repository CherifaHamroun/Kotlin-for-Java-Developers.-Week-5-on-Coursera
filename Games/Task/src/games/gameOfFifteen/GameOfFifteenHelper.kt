package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var i = 0
    var list = permutation.toMutableList()
    var b = 0
    var j = 0
    var k = 0
   if (0 !in list){ while (i<permutation.size){
        if (list[i] != i+1){
            j = list[i]
            k = list.indexOf(i+1)
            list[k] = j
            list[i] =i+1
            b=b+1
        }
        i+=1
    }
    return b%2 == 0}
    else{
       while (i<permutation.size){
           if (list[i] != i){
               j = list[i]
               k = list.indexOf(i)
               list[k] = j
               list[i] =i
               b=b+1
           }
           i+=1
       }
       return b%2 == 0
   }
}
