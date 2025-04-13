package pos.logic
import pos.model.CartEntry
import pos.model.Item

class Cart (
    private val cartEntries: MutableList<CartEntry>
) {
    fun getEntries(): List<CartEntry> = cartEntries // private initial prop to ensure immutability?
//  fun getEntries(): List<Item> = items.toList() // doesn't seem very space efficient? but according to docs guarantees imm?

    fun addEntry(item: Item, quantity: Int) {
        val entry = cartEntries.find { it.item.id == item.id }

        if (entry != null) {
            entry.quantity += quantity
        } else {
            cartEntries.add(CartEntry(item, quantity))
        }
    }
    
    fun removeEntry(targetId: Int?) { // may not exist
        //        for (item in items) {
        //            if (item.id == targetId) {
        //                items.remove(item)
        //            }
        //        }
    }

    @Override
    override fun equals(other: Any?): Boolean {
        if (this === other) return true // ref check
        if (other !is Cart) return false // type check

        return this.cartEntries == other.cartEntries
    }

    @Override
    override fun toString(): String {
        return "Cart(items=${cartEntries.joinToString()})"
    }

    @Override
    override fun hashCode(): Int { // intellisense gets mad if equals is overridden without hashCode as well?
        return cartEntries.hashCode()
    }
}