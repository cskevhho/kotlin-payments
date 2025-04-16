package com.example.axiumsim.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.axiumsim.models.Item
import com.example.axiumsim.viewmodels.BasketViewModel

@Composable
fun BasketScreen(viewModel: BasketViewModel, onCheckout: () -> Unit) {
    val items by viewModel.items.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Basket", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items) { item ->
                Text(text = "${item.name} - $${item.price}")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Total: $${viewModel.getTotalPrice()}")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onCheckout, modifier = Modifier.fillMaxWidth()) {
            Text("Proceed to Checkout")
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    BasketScreen(
        viewModel = BasketViewModel(),
        onCheckout = {}
    )
}

