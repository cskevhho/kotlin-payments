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
        val testEntry: Item = itemOne
        cart.addEntry(testEntry, 2)
        val expected: MutableList<CartEntry> = mutableListOf(CartEntry(itemOne, 2))

        assertEquals(expected, cart.showEntries())
    }

    @Test
    fun testAddEntry_ExistingEntry() {
        val testEntry: Item = itemOne
        cart.addEntry(testEntry, 2)
        cart.addEntry(testEntry, 4)
        val expected: MutableList<CartEntry> = mutableListOf(CartEntry(itemOne, 6))

        assertEquals(expected, cart.showEntries())
    }

    @Test
    fun testRemoveEntry_Decrement() {
        val testEntry: Item = itemOne
        cart.addEntry(testEntry, 2)
        cart.removeEntry(itemOne, 1)
        val expected: MutableList<CartEntry> = mutableListOf(CartEntry(itemOne, 1))

        assertEquals(expected, cart.showEntries())
    }

    @Test
    fun testRemoveEntry_FullRemove() {
        val testEntry: Item = itemOne
        cart.addEntry(testEntry, 2)
        cart.removeEntry(testEntry, 4)
        val expected: MutableList<CartEntry> = mutableListOf()

        assertEquals(expected, cart.showEntries())
    }

    @Test
    fun testCalculateTotal() {
    }

}