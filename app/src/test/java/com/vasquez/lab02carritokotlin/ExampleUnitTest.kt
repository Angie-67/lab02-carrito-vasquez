package com.vasquez.lab02carritokotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)

class ExampleUnitTest {
    @Test

    fun main() {
        val nombreCliente = "Pedrito Flores"        // String (inferido)
        val carrito = mutableListOf<Producto>() // lista vacía de productos
        println("Cliente: $nombreCliente")
        println()

        println("=========================================")
        println("  CARRITO DE COMPRAS - TIENDA TECSUP  ")
        println("=========================================")

        carrito.add(Producto("Laptop HP", 2400.0, 1))
        carrito.add(Producto("Mouse", 40.0, 4))
        carrito.add(Producto("Audifonos Sony", 80.0, 2))
        carrito.add(Producto("Teclado", 55.0, 3))

        for (producto in carrito) {
            println("Producto(s) agregado al carrito: ${producto.nombre}")
        }
    }
}