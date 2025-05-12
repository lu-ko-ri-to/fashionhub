package com.mike.thefashionhub.ui.theme.screens.cart
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

// Optional if using shapes and dividers
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

// Your data/model classes
import com.mike.thefashionhub.model.CartItem
import com.mike.thefashionhub.model.Product
import com.mike.thefashionhub.ui.theme.viewmodel.CartViewModel

@Composable
fun CartScreen(viewModel: CartViewModel = viewModel()) {
  val items = viewModel.cartItems
  LaunchedEffect(Unit) {
    items.forEach {
      println("Cart item: ${it.name}, imageResId = ${it.imageResId}")
    }
  }




  Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
    Text("Your Cart", fontSize = 24.sp, fontWeight = FontWeight.Bold)

    if (items.isEmpty()) {
      Text("Your cart is empty.", modifier = Modifier.padding(top = 20.dp))
    } else {
      LazyColumn {
        items(items) { item ->
          CartItemRow(
            item = item, onRemove = { viewModel.removeItem(item) },
            onPay = TODO()
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        "Total: $${viewModel.totalPrice()}",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
      )

      Button(onClick = { viewModel.clearCart() }) {
        Text("Checkout")
      }
    }
  }
}

@Composable
fun CartItemRow(
  onRemove: () -> Unit,
  onPay: () -> Unit,
  item: CartItem
) {
  Row(modifier = Modifier
    .fillMaxWidth()
    .padding(8.dp)) {

    Image(
      painter = painterResource(id = item.imageResId),
      contentDescription = item.name,
      modifier = Modifier.size(80.dp)
    )

    Spacer(modifier = Modifier.width(16.dp))

    Column(modifier = Modifier.weight(1f)) {
      Text(text = item.name, fontWeight = FontWeight.Bold)
      Text(text = "Price: $${item.price}")
      Text(text = "Qty: ${item.quantity}")
    }

    IconButton(onClick = onRemove) {
      Icon(Icons.Default.Delete, contentDescription = "Remove")
    }
  }

}
