package pos.data
import pos.model.Item
import pos.model.ItemStock

object ItemInventory {
    private val inventory: MutableMap<Int, ItemStock> = mutableMapOf()
    private val item1 = Item.create( "Primeagen's Beans", 420.69)
    private val item2 = Item.create( "DHH's Rubies", 13.37)
    private val item3 = Item.create( "TJ's Terminals", 0.50)
    private val item4 = Item.create("Altman's LLMs", 100_000_000.00)

    init { // for testing reasons, maybe deploy with this as default init?
        inventory[item1.id] = ItemStock(item1, 50)
        inventory[item2.id] = ItemStock(item2, 30)
        inventory[item3.id] = ItemStock(item3, 99)
        inventory[item4.id] = ItemStock(item4, 0)
    }

    fun reset() {
        inventory[item1.id]?.quantity = 50
        inventory[item2.id]?.quantity = 30
        inventory[item3.id]?.quantity = 99
        inventory[item4.id]?.quantity = 0
    }

    // fun getItemById(id: Int): Item = inventory[id]?.item ?: error("Item ID#$id not found") // elvis for error, but it should already be confirmed pre-condition
    internal fun getItemById(id: Int): Item { return inventory[id]!!.item } // visibility lock to module-level


    // fun checkStock(id: Int): Int = inventory[id]?.quantity ?: 0 // KISS vs DRY?
    fun checkStock(id: Int): Int {
        val item = getItemById(id) // getItemById already confirms existence of item

        return inventory[item.id]!!.quantity
    }

    fun takeItem(id: Int, quantity: Int): Boolean {
        val item = getItemById(id)
        val stock = inventory[item.id]!!

        return if (quantity > stock.quantity) {
            false
        } else {
            stock.quantity -= quantity
            true
        }
    }

    fun restockItem(id: Int, quantity: Int): String {
        val item = getItemById(id)
        val stock = inventory[item.id]!!
        val remainingSpace = 99 - stock.quantity

        val addedAmount = minOf(quantity, remainingSpace)
        stock.quantity += addedAmount

        return if (addedAmount < quantity) {
            "Only $addedAmount of units restocked to ${item.name}. Inventory capped at 99."
        } else {
            "$addedAmount of units added to ${item.name}. Current quantity: ${stock.quantity}"
        }
    }
}