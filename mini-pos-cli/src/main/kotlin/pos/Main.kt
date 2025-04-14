package org.example.pos
import pos.logic.Cart
fun main() {
    val cart = Cart()
    while (true) {
        println("MENU:")
        println("1. View Inventory")
        println("2. Add Item to Cart")
        println("3. Remove Item from Cart")
        println("4. View Cart")
        println("5. Checkout")
        println("6. Exit")

        when (readLine()?.trim()) {
            "1" -> handleViewInventory()
            "2" -> handleAddToCart()
            "3" -> handleRemoveFromCart()
            "4" -> handleViewCart()
            "5" -> handleCheckout()
            "6" -> {
                println("Exiting... Bye bye :)")
                break
            }
            else -> println("That's not an option. Try again.")
        }
    }
}

fun handleViewInventory() {}

fun handleAddToCart(cart: Cart) {}

fun handleRemoveFromCart(cart: Cart) {}

fun handleViewCart(cart: Cart) {}

fun handleCheckout(cart: Cart) {}