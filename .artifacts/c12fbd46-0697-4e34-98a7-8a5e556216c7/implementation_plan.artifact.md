# Refactorización del Carrito de Compras

Este plan detalla el traslado de la lógica desde `ExampleUnitTest` a `Carrito.kt`, la implementación de nuevas funcionalidades de búsqueda y eliminación, y la aplicación de buenas prácticas de POO.

## User Review Required

> [!IMPORTANT]
> Se consolidará la lógica de interacción en `Carrito.kt` tal como se solicitó, eliminando la dependencia de `ConsoleApp.kt` si es necesario para mantener el código ordenado en un solo punto de entrada principal.

## Proposed Changes

### [model]

#### [MODIFY] [ShoppingCart.kt](file:///C:/Users/User/lab02-carrito-vasquez/app/src/main/java/com/vasquez/lab02carritokotlin/model/ShoppingCart.kt)
- Agregar método `findProductByName(name: String): Product?` usando `.find`.
- Agregar método `removeProductByName(name: String): Boolean` usando `removeIf`.

### [app]

#### [MODIFY] [Carrito.kt](file:///C:/Users/User/lab02-carrito-vasquez/app/src/main/java/com/vasquez/lab02carritokotlin/Carrito.kt)
- Implementar la lógica de menú principal.
- Integrar las operaciones de:
    1. Agregar producto.
    2. Mostrar carrito.
    3. Buscar producto (por nombre).
    4. Eliminar producto (por nombre).
    5. Finalizar compra y mostrar resumen.
- Aplicar principios de Clean Code y POO.

#### [DELETE] [ConsoleApp.kt](file:///C:/Users/User/lab02-carrito-vasquez/app/src/main/java/com/vasquez/lab02carritokotlin/ConsoleApp.kt)
- Este archivo será eliminado ya que su lógica se trasladará y mejorará en `Carrito.kt`.

## Verification Plan

### Manual Verification
- Ejecutar la aplicación por consola.
- Probar el flujo completo: agregar varios productos, buscar uno existente, buscar uno inexistente, eliminar un producto, y verificar que los cálculos de subtotal, IGV, descuento y total sean correctos.
