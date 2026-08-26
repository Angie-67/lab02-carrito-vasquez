package com.vasquez.lab02carritokotlin

import com.vasquez.lab02carritokotlin.model.Product
import com.vasquez.lab02carritokotlin.model.ShoppingCart
import org.junit.Test
import org.junit.Assert.*

/**
 * Pruebas unitarias para validar la lógica del carrito de compras.
 */
class ExampleUnitTest {
    @Test
    fun `verificar calculos basicos del carrito`() {
        val cart = ShoppingCart()
        cart.addProduct(Product("Laptop HP", 2400.0, 1))
        cart.addProduct(Product("Mouse", 40.0, 4))
        
        val subtotalEsperado = 2400.0 + (40.0 * 4) // 2560.0
        val igvEsperado = subtotalEsperado * 0.18 // 460.8
        val totalEsperado = subtotalEsperado + igvEsperado // 3020.8
        
        assertEquals(subtotalEsperado, cart.calculateSubtotal(), 0.01)
        assertEquals(igvEsperado, cart.calculateIGV(), 0.01)
        assertEquals(totalEsperado, cart.calculateTotal(), 0.01)
    }

    @Test
    fun `verificar descuento de 5 por ciento`() {
        val cart = ShoppingCart()
        // Total debe ser > 3000 para el 5%
        cart.addProduct(Product("Producto Caro", 3200.0, 1))
        
        val subtotal = 3200.0
        val total = subtotal + (subtotal * 0.18) // 3776.0
        val descuentoEsperado = total * 0.05 // 188.8
        
        assertEquals(descuentoEsperado, cart.calculateDiscount(), 0.01)
    }

    @Test
    fun `verificar producto mas caro`() {
        val cart = ShoppingCart()
        val p1 = Product("Laptop", 2400.0, 1)
        val p2 = Product("Mouse", 40.0, 1)
        
        cart.addProduct(p1)
        cart.addProduct(p2)
        
        assertEquals(p1, cart.findMostExpensiveProduct())
    }
}
