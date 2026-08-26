package com.vasquez.lab02carritokotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test

    data class Producto(
        val nombre: String,
        val precio: Double,
        var cantidad: Int
    )

    fun main() {
        println("=========================================")
        println("  CARRITO DE COMPRAS - TIENDA TECSUP  ")
        println("=========================================")
    }
}