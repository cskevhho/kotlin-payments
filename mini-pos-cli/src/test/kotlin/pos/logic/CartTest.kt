package pos.logic
import pos.model.Item
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CartTest {
    val itemOne: Item = Item.create("Primeagen's Beans",420.69,5)
    val itemTwo: Item = Item.create("DHH's Ruby", 1337.37, 10)
    val itemThree: Item = Item.create("TJ's Terminal", 0.50, 1000)
    val itemFour: Item = Item.create("Theo's Stack", 6996.00, 500)

    @Test
    fun testAddItems() {
    }

    @Test
    fun testRemoveItems() {
        val testCart = Cart(mutableListOf(itemOne, itemTwo, itemThree))
        val expected = Cart(mutableListOf(itemOne, itemThree))
        testCart.removeItem(2)
        assertEquals(expected, testCart)
    }

    @Test
    fun testDisplayItems() {
    }

    @Test
    fun testCalculateTotal() {
    }

}