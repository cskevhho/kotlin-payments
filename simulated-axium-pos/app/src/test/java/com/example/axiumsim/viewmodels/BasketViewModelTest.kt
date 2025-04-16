import com.example.axiumsim.models.Item
import com.example.axiumsim.viewmodels.BasketViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BasketViewModelTest {

    private lateinit var viewModel: BasketViewModel

    @Before
    fun setup() {
        viewModel = BasketViewModel()
    }

    @Test
    fun `addItem adds item to basket`() { // this is some wild test method naming convention
        val item = Item(1, "Apple", 1.50)
        viewModel.addItem(item)
        assertTrue(viewModel.items.value.contains(item))
    }

    @Test
    fun `removeItem removes item from basket`() {
        val item = Item(2, "Banana", 1.00)
        viewModel.addItem(item)
        viewModel.removeItem(item)
        assertFalse(viewModel.items.value.contains(item))
    }

    @Test
    fun `getTotalPrice returns correct sum`() {
        val item1 = Item(3, "Orange", 1.25)
        val item2 = Item(4, "Mango", 2.75)
        viewModel.addItem(item1)
        viewModel.addItem(item2)
        assertEquals(4.0, viewModel.getTotalPrice(), 0.01)
    }
}
