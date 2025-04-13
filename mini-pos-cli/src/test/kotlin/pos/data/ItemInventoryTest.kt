package pos.data

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows

class ItemInventoryTest {

    @BeforeEach
    fun resetQty() {
        ItemInventory.reset()
    }

    @Test
    fun testGetItemById() {
        val actual = ItemInventory.getItemById(1)
        assertEquals("Primeagen's Beans", actual.name)
        assertEquals(420.69, actual.price)
        assertEquals(1, actual.id)
    }

    @Test
    fun testGetItemById_ThrowMissing() {
        assertThrows<NullPointerException> {
            ItemInventory.getItemById(999)
        }
    }

    @Test
    fun testCheckStock() {
        val expected = 50
        val actual = ItemInventory.checkStock(1)

        assertEquals(actual, expected)
    }

    @Test
    fun testTakeItem_OverQuantity() {
        val expected = false
        val actual = ItemInventory.takeItem(1, 51)

        assertEquals(actual, expected)
    }

    @Test
    fun testTakeItem_OverQuantity_DoesNotAffectStock() {
        val before = ItemInventory.checkStock(1)
        ItemInventory.takeItem(1, before + 10)
        val after = ItemInventory.checkStock(1)
        assertEquals(before, after)
    }

    @Test
    fun testTakeItem_StockQuantityWithinRange() {
        val expected = 40
        ItemInventory.takeItem(1, 10)
        val actual = ItemInventory.checkStock(1)

        assertEquals(actual, expected)
    }

    @Test
    fun testTakeItem_MultiTake() {
        ItemInventory.takeItem(1, 10)
        ItemInventory.takeItem(1, 20)
        val actual = ItemInventory.checkStock(1)
        assertEquals(20, actual)
    }

    @Test
    fun testRestockItem_ClampedRange() {
        val expected = 99
        ItemInventory.restockItem(1, 500)
        val actual = ItemInventory.checkStock(1)

        assertEquals(actual, expected)
    }

    @Test
    fun testRestockItem_ClampedMessage() {
        val message = ItemInventory.restockItem(1, 100)
        assertTrue(message.contains("Inventory capped at 99"))
    }

    @Test
    fun testRestockItem_WithinRange() {
        val expected = 51
        ItemInventory.restockItem(1, 1)
        val actual = ItemInventory.checkStock(1)

        assertEquals(actual, expected)
    }
}