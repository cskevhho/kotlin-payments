package pos.util

object ItemIdGenerator {
    private var nextId = 1 // should this be defaulted to 0 or 1?

    fun generateId(): Int = nextId++

    fun reset() { nextId = 1 } // to reset for test cases
}