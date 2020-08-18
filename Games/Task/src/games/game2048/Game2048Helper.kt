package games.game2048

import org.omg.CORBA.INTERNAL

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/

fun <T : Any> herge(t: T): Any{
    if (t is String) return t+t
    else {
        if (t is Int) return t+t
        else return t
    }
}
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
    var nonNuls = this.filterNotNull()
    var list = listOf<T>()
    var list2 = listOf<Int>()
    var i = 0
    while(i<nonNuls.size){
        if (i+1 < nonNuls.size)
        {if(nonNuls[i+1]==nonNuls[i]) {
            var k = herge(nonNuls[i])
            list += k as T
            i +=2
        }
        else {
            list += nonNuls[i]
            i=i+1
        }}
        else {
            list+=nonNuls[i]
            i+=1
        }
    }
    return list
}
