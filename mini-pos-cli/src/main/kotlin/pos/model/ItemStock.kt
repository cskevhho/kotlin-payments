package pos.model

data class ItemStock(
    val item: Item,
    var quantity: Int,
    val maxQuantity: Int = 99
)
