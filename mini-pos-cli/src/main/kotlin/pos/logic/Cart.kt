package pos.logic
import pos.model.Item
/*
    Cart should have "Add", "Remove", "Display", "Calculate"?
 */
class Cart (
    private val items: MutableList<Item>
) {
    fun getItems(): List<Item> = items // private initial prop to ensure immutability?
//  fun getItems(): List<Item> = items.toList() // doesn't seem very space efficient? but according to docs guarantees imm?

    fun removeItem(targetId: Int?) { // may not exist
        for (item in items) {
            if (item.id == targetId) {
                items.remove(item)
            }
        }
    }

    fun addItem(item: Item) = items.add(item)

    @Override
    override fun equals(other: Any?): Boolean {
        if (this === other) return true // ref check
        if (other !is Cart) return false // type check

        return this.items == other.items
    }

    @Override
    override fun toString(): String {
        return "Cart(items=${items.joinToString()})"
    }

    @Override
    override fun hashCode(): Int { // intellisense gets mad if equals is overridden without hashCode as well?
        return items.hashCode()
    }
}