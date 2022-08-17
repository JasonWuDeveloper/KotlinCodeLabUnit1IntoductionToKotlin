package com.codlab.kotlincodelabunit1intoductiontokotlin

fun main() {
    val squareCabin = SquareCabin(6)
    println("\nSquare Cabin\n=============")
    println("Capacity: ${squareCabin.capacity}")
    println("Material: ${squareCabin.buildingMaterial}")
    println("Has room? ${squareCabin.hasRoom()}")
    val border = "`-._,-'"
    val timeToRepeat = 4
//    printBorder(border, timeToRepeat)
//    printBorder(border, timeToRepeat)
//    val age = 24
//    val layers = 5
//    printCakeCandles(age)
//    printCakeTop(age)
//    printCakeBottom(age, layers)
}

fun printCakeCandles(age: Int) {
    print(" ")
    repeat(age) {
        print(",")
    }
    println()
    print(" ")
    repeat(age) {
        print("|")
    }
    println()
}
fun printBorder(border: String,timeToRepeat: Int) {

    repeat(timeToRepeat) {
        print(border)
    }
    println()

}
fun printCakeBottom(age: Int, layers: Int) {
    repeat(layers) {
        repeat(age + 2) {
            print("@")
        }
        println()
    }
}

fun printCakeTop(age: Int) {
    repeat(age + 2) {
        print("=")
    }
    println()
}
