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

//    @Test
//    fun testDisplayItems() {
//        val testCart = Cart(mutableListOf(itemOne, itemTwo, itemThree)).getItems()
//        val expected = listOf(itemOne, itemTwo, itemThree)
//
//        assertEquals(expected, testCart)
//    }

    @Test
    fun testAddEntry_Empty() {
        val testEntry: Item = itemOne
        cart.addEntry(testEntry, 2)
        val expected: MutableList<CartEntry> = mutableListOf(CartEntry(itemOne, 2))

        assertEquals(expected, cart.getEntries())
    }

    @Test
    fun testAddEntry_ExistingEntry() {
        val testEntry: Item = itemOne
        cart.addEntry(testEntry, 2)
        cart.addEntry(testEntry, 4)
        val expected: MutableList<CartEntry> = mutableListOf(CartEntry(itemOne, 6))

        assertEquals(expected, cart.getEntries())
    }

    @Test
    fun testRemoveEntry() {
        // CartEntry(item, quantity) Item(id,name,price)

//        assertEquals(expected, testCart)
    }

    @Test
    fun testCalculateTotal() {
    }

}