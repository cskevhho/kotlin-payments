package pos
import pos.data.ItemInventory
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

        when (readlnOrNull()?.trim()) {
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

fun handleViewInventory() {
    println("Available Items:")
    println("ID  Name                    Price     Stock")
    println("--------------------------------------------")

    for ((id, stock) in ItemInventory.viewInventory()) {
        val name = stock.item.name.padEnd(22)
        val price = String.format("$%.2f", stock.item.price).padEnd(9)
        val quantity = stock.quantity.toString().padEnd(5)
        println("$id  $name$price$quantity")
    }
}


fun handleAddToCart(cart: Cart) {
    handleViewInventory() // Show inventory for user reference

    print("Enter item ID and quantity (e.g., 1 2): ")
    readlnOrNull()
        ?.split(" ")
        ?.takeIf { it.size == 2 }
        ?.let { (idStr, qtyStr) ->
            val id = idStr.toIntOrNull()
            val quantity = qtyStr.toIntOrNull()

            if (id != null && quantity != null && quantity > 0) {
                if (ItemInventory.takeItem(id, quantity)) {
                    val item = ItemInventory.getItemById(id)
                    cart.addEntry(item, quantity)
                    println("Added $quantity x '${item.name}' to cart.")
                } else {
                    println("Not enough stock for item ID $id.")
                }
            } else {
                println("Invalid ID or quantity.")
            }
        } ?: println("Invalid input format. Use: <ID> <quantity>")
}


fun handleRemoveFromCart(cart: Cart) {}

fun handleViewCart(cart: Cart) {}

fun handleCheckout(cart: Cart) {}