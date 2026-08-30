package com.vasquez.lab02carritokotlin

import com.vasquez.lab02carritokotlin.model.Product
import com.vasquez.lab02carritokotlin.model.ShoppingCart
import java.util.Scanner
import java.util.Locale

/**
 * Punto de entrada principal para el Laboratorio 02 - Carrito de Compras.
 * Implementa un menú interactivo por consola siguiendo principios de POO.
 */
class CarritoApp {
    private val scanner = Scanner(System.`in`)
    private val cart = ShoppingCart()

    fun start() {
        var option: Int
        do {
            showMenu()
            option = readOption()
            processOption(option)
        } while (option != 0)
    }

    private fun showMenu() {
        println("\n=========================================")
        println("  SISTEMA DE CARRITO DE COMPRAS")
        println("=========================================")
        println("1. Agregar producto")
        println("2. Mostrar carrito")
        println("3. Buscar producto (por nombre)")
        println("4. Eliminar producto (por nombre)")
        println("5. Mostrar resumen de compra")
        println("0. Salir")
        print("Seleccione una opción: ")
    }

    private fun readOption(): Int {
        return scanner.nextLine().toIntOrNull() ?: -1
    }

    private fun processOption(option: Int) {
        when (option) {
            1 -> addProduct()
            2 -> showCart()
            3 -> searchProduct()
            4 -> removeProduct()
            5 -> displaySummary()
            0 -> println("Saliendo del sistema...")
            else -> println("Opción no válida. Intente de nuevo.")
        }
    }

    private fun addProduct() {
        println("\n--- Nuevo Producto ---")
        print("Nombre: ")
        val name = scanner.nextLine()
        if (name.isBlank()) {
            println("El nombre no puede estar vacío.")
            return
        }

        print("Precio: ")
        val price = scanner.nextLine().toDoubleOrNull() ?: 0.0
        if (price <= 0) {
            println("El precio debe ser mayor a 0.")
            return
        }

        print("Cantidad: ")
        val quantity = scanner.nextLine().toIntOrNull() ?: 0
        if (quantity <= 0) {
            println("La cantidad debe ser mayor a 0.")
            return
        }

        cart.addProduct(Product(name, price, quantity))
        println("Producto '$name' agregado exitosamente.")
    }

    private fun showCart() {
        if (cart.isEmpty()) {
            println("\nEl carrito está vacío.")
            return
        }

        println("\n--- Detalle del Carrito ---")
        println(String.format(Locale.US, "%-20s | %-10s | %-8s | %-10s", "Producto", "Precio", "Cant.", "Importe"))
        println("-------------------------------------------------------------")
        cart.getProducts().forEach { product ->
            println(String.format(Locale.US, "%-20s | S/ %8.2f | %8d | S/ %8.2f",
                product.name, product.price, product.quantity, product.calculateImport()))
        }
    }

    private fun searchProduct() {
        print("\nIngrese el nombre del producto a buscar: ")
        val name = scanner.nextLine()
        val product = cart.findProductByName(name)

        if (product != null) {
            println("Producto encontrado:")
            println("- Nombre: ${product.name}")
            println("- Precio: S/ ${product.price}")
            println("- Cantidad: ${product.quantity}")
            println("- Importe: S/ ${product.calculateImport()}")
        } else {
            println("No se encontró el producto '$name'.")
        }
    }

    private fun removeProduct() {
        print("\nIngrese el nombre del producto a eliminar: ")
        val name = scanner.nextLine()
        val removed = cart.removeProductByName(name)

        if (removed) {
            println("Producto '$name' eliminado correctamente.")
        } else {
            println("No se pudo eliminar. El producto '$name' no existe en el carrito.")
        }
    }

    private fun displaySummary() {
        if (cart.isEmpty()) {
            println("\nNo hay productos para generar un resumen.")
            return
        }

        println("\n=========================================")
        println("        RESUMEN DE COMPRA")
        println("=========================================")
        println(String.format(Locale.US, "%-22s: %d", "Productos totales", cart.productCount()))
        println(String.format(Locale.US, "%-22s: S/ %8.2f", "Subtotal", cart.calculateSubtotal()))
        println(String.format(Locale.US, "%-22s: S/ %8.2f", "IGV (18%)", cart.calculateIGV()))
        println(String.format(Locale.US, "%-22s: S/ %8.2f", "Total Bruto", cart.calculateTotal()))

        val discount = cart.calculateDiscount()
        if (discount > 0) {
            println(String.format(Locale.US, "%-22s: S/ %8.2f", "Descuento aplicado", discount))
        }

        println("-----------------------------------------")
        println(String.format(Locale.US, "%-22s: S/ %8.2f", "TOTAL NETO", cart.calculateFinalTotal()))
        println("=========================================")

        cart.findMostExpensiveProduct()?.let {
            println("Producto más caro: ${it.name} (S/ ${it.price})")
        }
        println("¡Gracias por su preferencia!")
    }
}

fun main() {
    val app = CarritoApp()
    app.start()
}
