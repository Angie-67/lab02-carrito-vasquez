package com.vasquez.lab02carritokotlin

import com.vasquez.lab02carritokotlin.model.Product
import com.vasquez.lab02carritokotlin.model.ShoppingCart
import java.util.Scanner

/**
 * Maneja la interacción por consola con el usuario para el carrito de compras.
 */
class ConsoleApp {
    private val scanner = Scanner(System.`in`)
    private val cart = ShoppingCart()

    fun run() {
        printHeader()
        requestClientName()
        inputProductsLoop()
        displaySummary()
    }

    private fun printHeader() {
        println("=========================================")
        println("  CARRITO DE COMPRAS - TIENDA TECSUP  ")
        println("=========================================")
    }

    private fun requestClientName() {
        print("Ingrese nombre del cliente: ")
        val name = scanner.nextLine()
        println("Bienvenido, $name\n")
    }

    private fun inputProductsLoop() {
        var continueAdding = true
        while (continueAdding) {
            println("--- Registro de Producto ---")
            print("Nombre: ")
            val name = scanner.nextLine()

            print("Precio: ")
            val price = scanner.nextLine().toDoubleOrNull() ?: 0.0

            print("Cantidad: ")
            val quantity = scanner.nextLine().toIntOrNull() ?: 0

            cart.addProduct(Product(name, price, quantity))

            print("¿Desea agregar otro producto? (s/n): ")
            val response = scanner.nextLine()
            continueAdding = response.equals("s", ignoreCase = true)
            println()
        }
    }

    private fun displaySummary() {
        if (cart.isEmpty()) {
            println("El carrito está vacío.")
            return
        }

        println("--------- DETALLE DEL CARRITO------------")
        cart.getProducts().forEachIndexed { index, product ->
            println(String.format("%d. %-20s x%d S/ %8.2f",
                index + 1, product.name, product.quantity, product.calculateImport()))
        }
        println("-----------------------------------------")
        println(String.format("%-22s: %d", "Cantidad de productos", cart.productCount()))

        val subtotal = cart.calculateSubtotal()
        val igv = cart.calculateIGV()
        val total = cart.calculateTotal()
        val discount = cart.calculateDiscount()
        val finalTotal = cart.calculateFinalTotal()

        println(String.format("%-22s: S/ %8.2f", "Subtotal", subtotal))
        println(String.format("%-22s: S/ %8.2f", "IGV (18%)", igv))
        println(String.format("%-22s: S/ %8.2f", "TOTAL A PAGAR", total))

        if (discount > 0) {
            println("-----------------------------------------")
            println(String.format("%-22s: S/ %8.2f", "Descuento aplicado", discount))
            println(String.format("%-22s: S/ %8.2f", "TOTAL CON DESCUENTO", finalTotal))
        }

        println("-----------------------------------------")
        cart.findMostExpensiveProduct()?.let {
            println("Producto más caro: ${it.name} " +
                    String.format("(S/ %.2f)", it.price))
        }
        println("-----------------------------------------")
        println("¡Gracias por su compra!")
    }
}
