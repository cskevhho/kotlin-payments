package pos.model
import pos.util.ItemIdGenerator

data class Item private constructor(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int,
) {
    companion object {
        fun create(name: String, price: Double, quantity: Int): Item {
            return Item(ItemIdGenerator.generateId(),name, price, quantity)
        }
    }
}
