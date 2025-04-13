package pos.logic
import pos.model.CartEntry
import pos.model.Item

class Cart (
    private val cartEntries: MutableList<CartEntry>
) {

    fun showEntries(): List<CartEntry> = cartEntries.toList() // idiomatic Kotlin prioritizes safety anyway

    fun addEntry(item: Item, quantity: Int) {
        val entry = cartEntries.find { it.item.id == item.id }

        if (entry != null) {
            entry.quantity += quantity
        } else {
            cartEntries.add(CartEntry(item, quantity))
        }
    }

    fun removeEntry(item: Item, quantity: Int) { // cli should confirm if an item is removable or not anyway, no need for null safety
        val entry = cartEntries.find { it.item.id == item.id }

        if (quantity >= entry!!.quantity) {
            cartEntries.remove(entry)
        } else {
           entry.quantity -= quantity
        }
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