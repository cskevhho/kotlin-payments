package pos.logic

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pos.model.Item
import pos.util.ItemIdGenerator
import pos.model.CartEntry

class CartTest {
    lateinit var cart: Cart
    val itemOne: Item = Item.create("Primeagen's Beans",420.69)
    val itemTwo: Item = Item.create("DHH's Rubies", 13.37)
    val itemThree: Item = Item.create("TJ's Terminals", 0.50)
    val itemFour: Item = Item.create("Theo's Stacks", 6996.00)

    @BeforeEach // this is neat
    fun setup() {
        ItemIdGenerator.reset()
        cart = Cart(mutableListOf())
    }

    @Test
    fun testAddEntry_Empty() {
        cart.addEntry(itemOne, 2)
        val expected: MutableList<CartEntry> = mutableListOf(CartEntry(itemOne, 2))

        assertEquals(expected, cart.showEntries())
    }

    @Test
    fun testAddEntry_ExistingEntry() {
        cart.addEntry(itemOne, 2)
        cart.addEntry(itemOne, 4)
        val expected: MutableList<CartEntry> = mutableListOf(CartEntry(itemOne, 6))

        assertEquals(expected, cart.showEntries())
    }

    @Test
    fun testAddEntry_ZeroQuantity() {
        cart.addEntry(itemOne, 0)

        assertEquals(emptyList<CartEntry>(), cart.showEntries())
    }

    @Test
    fun testRemoveEntry_Decrement() {
        cart.addEntry(itemOne, 2)
        cart.removeEntry(itemOne, 1)
        val expected: MutableList<CartEntry> = mutableListOf(CartEntry(itemOne, 1))

        assertEquals(expected, cart.showEntries())
    }

    @Test
    fun testRemoveEntry_FullRemove() {
        cart.addEntry(itemOne, 2)
        cart.removeEntry(itemOne, 4)
        val expected: MutableList<CartEntry> = mutableListOf()

        assertEquals(expected, cart.showEntries())
    }

    @Test
    fun testCalculateTotal_EmptyCart() {
        assertEquals(0.0, cart.calculateTotal())
    }

    @Test
    fun testCalculateTotal_SingleEntry() {
        cart.addEntry(itemTwo, 2)

        val expectedTotal = itemTwo.price * 2
        val actualTotal = cart.calculateTotal()

        assertEquals(expectedTotal, actualTotal)
    }

    @Test
    fun testCalculateTotal_MultipleEntries() {
        cart.addEntry(itemOne, 2)
        cart.addEntry(itemTwo, 1)

        val expectedTotal = (itemOne.price * 2) + (itemTwo.price * 1)
        val actualTotal = cart.calculateTotal()

        assertEquals(expectedTotal, actualTotal)
    }
}