package com.example.axiumsim.viewmodels

import androidx.lifecycle.ViewModel
import com.example.axiumsim.models.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BasketViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    fun addItem(item: Item) {
        _items.value = _items.value + item
    }

    fun removeItem(item: Item) {
        _items.value = _items.value - item
    }

    fun getTotalPrice(): Double {
        return _items.value.sumOf { it.price }
    }
}