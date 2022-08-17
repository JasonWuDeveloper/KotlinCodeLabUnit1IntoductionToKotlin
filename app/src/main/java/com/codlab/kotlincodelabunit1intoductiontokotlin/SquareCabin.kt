package com.codlab.kotlincodelabunit1intoductiontokotlin

class SquareCabin(residents: Int): Dwelling(residents) {
    override val buildingMaterial: String
        get() = "Wood"
    override val capacity: Int
        get() = 6
}