package pos.logic
import pos.model.Item
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pos.util.ItemIdGenerator

class CartTest {
    val itemOne: Item = Item.create("Primeagen's Beans",420.69)
    val itemTwo: Item = Item.create("DHH's Rubies", 13.37)
    val itemThree: Item = Item.create("TJ's Terminals", 0.50)
    val itemFour: Item = Item.create("Theo's Stacks", 6996.00)

    @BeforeEach // this is neat
    fun resetIds() {
        ItemIdGenerator.reset()
    }
    // TODO: Refactor all tests to reflect system design change w.r.t inventory quantity after testing ItemInventory
    @Test
    fun testRemoveItems() {
        val testCart = Cart(mutableListOf(itemOne, itemTwo, itemThree))
        val expected = Cart(mutableListOf(itemOne, itemThree))
        testCart.removeItem(2)

        assertEquals(expected, testCart)
    }

    @Test
    fun testDisplayItems() {
        val testCart = Cart(mutableListOf(itemOne, itemTwo, itemThree)).getItems()
        val expected = listOf(itemOne, itemTwo, itemThree)

        assertEquals(expected, testCart)
    }

    @Test
    fun testAddItem() {
        val testCart = Cart(mutableListOf(itemOne, itemTwo, itemThree))
        val expected = Cart(mutableListOf(itemOne, itemTwo, itemThree, itemFour))
        testCart.addItem(itemFour)

        assertEquals(expected, testCart)
    }

    @Test
    fun testCalculateTotal() {
    }

}