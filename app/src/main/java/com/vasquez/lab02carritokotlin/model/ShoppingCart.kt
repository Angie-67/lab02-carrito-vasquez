package com.vasquez.lab02carritokotlin.model

/**
 * Encapsula la lógica del carrito de compras, manejando la lista de productos
 * y realizando los cálculos financieros correspondientes.
 */
class ShoppingCart {
    private val products = mutableListOf<Product>()

    companion object {
        const val IGV_PERCENTAGE = 0.18
    }

    /**
     * Agrega un producto al carrito.
     */
    fun addProduct(product: Product) {
        products.add(product)
    }

    /**
     * Obtiene una lista inmutable de los productos en el carrito.
     */
    fun getProducts(): List<Product> = products.toList()

    /**
     * Calcula el subtotal (suma de importes de todos los productos).
     */
    fun calculateSubtotal(): Double = products.sumOf { it.calculateImport() }

    /**
     * Calcula el IGV basado en el subtotal.
     */
    fun calculateIGV(): Double = calculateSubtotal() * IGV_PERCENTAGE

    /**
     * Calcula el total bruto (subtotal + IGV).
     */
    fun calculateTotal(): Double = calculateSubtotal() + calculateIGV()

    /**
     * Calcula el descuento basado en el total acumulado.
     * > 5000: 10%
     * > 3000: 5%
     */
    fun calculateDiscount(): Double {
        val total = calculateTotal()
        return when {
            total > 5000 -> total * 0.10
            total > 3000 -> total * 0.05
            else -> 0.0
        }
    }

    /**
     * Calcula el total neto después de aplicar el descuento.
     */
    fun calculateFinalTotal(): Double = calculateTotal() - calculateDiscount()

    /**
     * Encuentra un producto por su nombre (ignorando mayúsculas/minúsculas).
     */
    fun findProductByName(name: String): Product? {
        return products.find { it.name.equals(name, ignoreCase = true) }
    }

    /**
     * Elimina un producto por su nombre (ignorando mayúsculas/minúsculas).
     * Retorna true si se eliminó al menos un elemento.
     */
    fun removeProductByName(name: String): Boolean {
        return products.removeIf { it.name.equals(name, ignoreCase = true) }
    }

    /**
     * Encuentra el producto con el precio unitario más alto.
     */
    fun findMostExpensiveProduct(): Product? = products.maxByOrNull { it.price }

    /**
     * Indica si el carrito está vacío.
     */
    fun isEmpty(): Boolean = products.isEmpty()

    /**
     * Retorna la cantidad de productos distintos agregados.
     */
    fun productCount(): Int = products.size
}
