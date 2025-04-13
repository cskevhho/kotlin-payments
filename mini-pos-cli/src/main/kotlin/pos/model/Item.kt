package pos.model
import pos.util.ItemIdGenerator

data class Item private constructor(
    val id: Int,
    val name: String,
    val price: Double
) {
    companion object {
        fun create(name: String, price: Double): Item {
            return Item(ItemIdGenerator.generateId(), name, price)
        }
    }
}