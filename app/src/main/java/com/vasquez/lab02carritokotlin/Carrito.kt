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
fun calcularDescuento(total: Double): Double {
    return when {
        total > 5000 -> total * 0.10
        total > 3000 -> total * 0.05
        else -> 0.0
    }
}
fun buscarProducto(productos: List<Producto>, nombre: String): Producto? {
    return productos.find { it.nombre.equals(nombre, ignoreCase = true) }
}
fun mostrarDetalle(productos: List<Producto>) {
    println("--------- DETALLE DEL CARRITO------------")
    var i = 1
    for (p in productos) {
        val importe = p.precio * p.cantidad
        println(String.format("%d. %-20s x%d S/ %8.2f", i, p.nombre, p.cantidad, importe))
        i++
    }
    println("-----------------------------------------")
}

//main
fun main() {
    val nombreCliente = "Pedrito Flores"        // String (inferido)
    val carrito = mutableListOf<Producto>() // lista vacía de productos

    println("Cliente: ${nombreCliente}")
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

    //reporte
    fun reporteactulizado() {
        mostrarDetalle(carrito)

        println(String.format("%-22s: %-10d", "Cantidad de productos", carrito.size))

        val subtot = calcularSubtotal(carrito)
        val IGV = calcularIGV(subtot)
        val tot = calcularTotal(subtot, IGV)

        println(String.format("%-22s: S/ %8.2f", "Subtotal", subtot))
        println(String.format("%-22s: S/ %8.2f", "IGV (18%)", IGV))
        println(String.format("%-22s: S/ %8.2f", "TOTAL A PAGAR", tot))
        println("----------------------------------------")

        val masCaro = carrito.maxByOrNull { it.precio }
        if (masCaro != null) {
            println(
                "Producto mas caro: ${masCaro.nombre} " +
                        String.format("(S/ %.2f)", masCaro.precio)
            )
        }

        //descuento para mostrar en pantalla
        val desc = calcularDescuento(tot)
        val textdesc = when {
            tot > 5000 -> "10% por compra mayor a S/ 5000"
            tot > 3000 -> "5% por compra mayor a S/ 3000"
            else -> "No hay descuento"
        }
        println("Descuento aplicado: ${textdesc}")

        //total a pagar
        val totpagar = tot - desc
        println(String.format("%-22s: S/ %8.2f", "TOTAL CON DESCUENTO", totpagar))
    }
    reporteactulizado()

    //otro apartado
    println("---------------------------------------")

    //buscar
    val nombrebuscar = "mouse"
    val encontrar = buscarProducto(carrito, nombrebuscar)
    println("Se esta buscando: ${nombrebuscar}")
    if (encontrar != null) {
        println("¡Producto encontrado!")
    } else {
        println("No se encontró.")
    }

    //eliminar
    val eliminar = "teclado"
    println("Se esta eliminando el producto ${eliminar}")
    val eliminado = carrito.removeIf {it.nombre.equals(eliminar, ignoreCase = true) }
    if (eliminado) {
        println("Producto eliminado de manera exitosa")
    } else {
        println("No se encontró.")
    }
    println("---------------------------------------")
    reporteactulizado()
}