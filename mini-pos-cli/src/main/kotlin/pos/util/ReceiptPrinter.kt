package pos.util
import pos.logic.Cart

object ReceiptPrinter {
    fun printReceipt(cart: Cart): String {
        val builder = StringBuilder()

        builder.appendLine("RECEIPT")
        builder.appendLine("-------------------------------------")
        builder.appendLine("Qty  Item                  Subtotal")

        for (entry in cart.showEntries()) {
            val qty = entry.quantity.toString().padEnd(4)
            val name = entry.item.name.padEnd(24)
            val subtotal = String.format("$%.2f", entry.item.price * entry.quantity)

            builder.appendLine("$qty$name$subtotal")
        }

        builder.appendLine("-------------------------------------")
        builder.appendLine("TOTAL:".padEnd(28) + String.format("$%.2f", cart.calculateTotal()))

        return builder.toString()
    }
}
