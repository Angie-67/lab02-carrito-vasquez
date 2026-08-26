package com.vasquez.lab02carritokotlin.model

/**
 * Representa un producto individual en el carrito de compras.
 */
data class Product(
    val name: String,
    val price: Double,
    val quantity: Int
) {
    /**
     * Calcula el importe total para este producto (precio * cantidad).
     */
    fun calculateImport(): Double = price * quantity
}
