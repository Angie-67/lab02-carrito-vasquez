package com.vasquez.lab02carritokotlin

data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)
fun calcularSubtotal(productos: List<Producto>): Double {
    var subtotal = 0.0
    for (p in productos) {
        subtotal += p.precio * p.cantidad
    }
    return subtotal
}

fun calcularIGV(subtotal: Double): Double {
    var igv = subtotal * 0.18
    return igv
}

fun calcularTotal(subtotal: Double, igv: Double): Double {
    val total = subtotal + igv
    return total
}

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

    fun mostrarDetalle(productos: List<Producto>) {
        println("--------- DETALLE DEL CARRITO------------")
        var i = 1
        for (p in productos) {
            val importe = p.precio * p.cantidad
            println(String.format("%d. %-20s x%d S/ %8.2f",
                i, p.nombre, p.cantidad, importe))
            i++
        }
        println("-----------------------------------------")
        println(String.format("%-22s: %-10d","Cantidad de productos", carrito.size))
        var subtot = calcularSubtotal(carrito)
        var IGV = calcularIGV(subtot)
        var tot = calcularTotal(subtot, IGV)
        println(String.format("%-22s: S/ %8.2f", "Subtotal", subtot))
        println(String.format("%-22s: S/ %8.2f", "IGV (18%)", IGV))
        println(String.format("%-22s: S/ %8.2f", "TOTAL A PAGAR", tot))
        println("----------------------------------------")
        val masCaro = carrito.maxByOrNull { it.precio }
        if (masCaro != null) {
            println("Producto mas caro: ${masCaro.nombre} " +
                    String.format("(S/ %.2f)", masCaro.precio))
        }
        fun calcularDescuento(total: Double): Double {
            return when {
                total > 5000 -> total * 0.10
                total > 3000 -> total * 0.05
                else -> 0.0
            }
        }

        val desc = calcularDescuento(tot)
        val totdesc = tot - desc
        println("Descuento aplicado: ${desc}")
        println(String.format("%-22s: S/ %8.2f", "TOTAL CON DESCUENTO", totdesc))
    }

    mostrarDetalle(carrito)
}