package com.codlab.kotlincodelabunit1intoductiontokotlin

fun main() {
}
abstract class Dwelling(private var residents: Int){
   abstract val buildingMaterial: String
   abstract val capacity: Int

    fun hasRoom(): Boolean {
        return residents < capacity
    }
}




class Happy() {

}

enum class Bob() {

}
sealed class Test() {

}
data class Goat(val go : String) {

}