package pos
import pos.data.ItemInventory
import pos.logic.Cart
import pos.util.ReceiptPrinter

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
            "2" -> handleAddToCart(cart)
            "3" -> handleRemoveFromCart(cart)
            "4" -> handleViewCart(cart)
            "5" -> handleCheckout(cart)
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

            if (id != null && quantity != null && quantity > 0) { // is this idiomatic kotlin?
                if (ItemInventory.takeItem(id, quantity)) {
                    val item = ItemInventory.getItemById(id)
                    cart.addEntry(item, quantity)
                    println("Added $quantity x '${item.name}' to cart.")
                } else {
                    println("Not enough stock for item ID $id, ${ItemInventory.getItemById(id).name}.")
                }
            } else {
                println("Invalid ID or quantity.")
            }
        } ?: println("Invalid input format. Use: <ID> <quantity>")
}


fun handleRemoveFromCart(cart: Cart) {
    val entries = cart.showEntries()

    if (entries.isEmpty()) {
        println("🛒 Your cart is empty.")
        return
    }

    handleViewCart(cart)

    print("Enter item ID and quantity to remove (e.g., 1 2): ")
    readlnOrNull()
        ?.split(" ")
        ?.takeIf { it.size == 2 }
        ?.let { (idStr, qtyStr) ->
            val id = idStr.toIntOrNull()
            val quantity = qtyStr.toIntOrNull()

            if (id != null && quantity != null && quantity > 0) {
                val item = entries.find { it.item.id == id }?.item
                if (item != null) {
                    cart.removeEntry(item, quantity)
                    println("Removed $quantity x '${item.name}' from cart.")
                } else {
                    println("Item ID $id not found in cart.")
                }
            } else {
                println("Invalid ID or quantity.")
            }
        } ?: println("Invalid input format. Use: <ID> <quantity>")
}


fun handleCheckout(cart: Cart) {
    if (cart.showEntries().isEmpty()) {
        println("Your cart is empty. Nothing to checkout.")
        return
    }

    println("\n======= RECEIPT =======")
    println(ReceiptPrinter.printReceipt(cart))

    cart.clear()
    println("Checkout complete. Cart has been cleared.")
}

fun handleViewCart(cart: Cart) {
    val entries = cart.showEntries()

    if (entries.isEmpty()) {
        println("🛒 Your cart is empty.")
        return
    }

    println("Your Cart:")
    println("Qty  Item                    Subtotal")
    println("----------------------------------------")

    for (entry in entries) {
        val qty = entry.quantity.toString().padEnd(4)
        val name = entry.item.name.padEnd(22)
        val subtotal = String.format("$%.2f", entry.item.price * entry.quantity)
        println("$qty$name$subtotal")
    }

    println("----------------------------------------")
    println("TOTAL:".padEnd(28) + String.format("$%.2f", cart.calculateTotal()))
}
