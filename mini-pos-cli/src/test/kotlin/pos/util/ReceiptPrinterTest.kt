package pos.util
import pos.model.Item
import pos.logic.Cart
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReceiptPrinterTest {
    lateinit var cart: Cart
    val itemOne: Item = Item.create("Primeagen's Beans",420.69)
    val itemTwo: Item = Item.create("DHH's Rubies", 13.37)
    val itemThree: Item = Item.create("TJ's Terminals", 0.50)
    val itemFour: Item = Item.create("Theo's Stacks", 6996.00)

    @Test
    fun testPrintReceipt_MultipleItems() {
        cart = Cart(mutableListOf())
        cart.addEntry(itemOne, 2) // Primeagen's Beans, $420.69
        cart.addEntry(itemTwo, 1) // DHH's Rubies, $13.37

        val receipt = ReceiptPrinter.printReceipt(cart)

        assertTrue(receipt.contains("Primeagen's Beans"))
        assertTrue(receipt.contains("DHH's Rubies"))
        assertTrue(receipt.contains("2"))
        assertTrue(receipt.contains("TOTAL"))
        assertTrue(receipt.contains((itemOne.price * 2 + itemTwo.price).toString()))
    }

}