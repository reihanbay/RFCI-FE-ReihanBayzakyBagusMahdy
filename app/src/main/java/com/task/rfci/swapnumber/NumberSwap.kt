package com.task.rfci.swapnumber
import java.util.*
/**
 * ALGORITMA:
 * Inisialisasi deret Angka berupa array
 * deklarasi total swap = 1
 * jika array awal tidak sama dengan array yang di sort
 *      perulangan (int a = 0 ; a<swap.size(); a++ )
 *          jika index[a]> index[a+1]]
 *              int x = index[a]
 *              index[a+1] = index[a]
 *              index[a+1] = x
 *              print array
 * total++
 * */

fun main(){
    var arr = arrayOf(4 ,9 ,7 ,5 ,8 ,9 ,3)
    deretAngka(arr)
}

fun deretAngka(arr: Array<Int>){
    var total = 1
    while (arr.sortedArray().joinToString() != arr.joinToString())
        for (a in 0..arr.size-2){
            if (arr[a]>arr[a+1]){
                var x = arr[a]
                arr[a] = arr[a+1]
                arr[a+1] = x
                println("$total. [${arr[a]}, ${arr[a+1]}] -> ${arr.joinToString()}")
                total++
            }
        }

    println("\nTotal Swap : ${total - 1}")
}