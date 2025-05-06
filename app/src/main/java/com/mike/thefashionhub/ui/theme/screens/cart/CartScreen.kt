package com.mike.thefashionhub.ui.theme.screens.cart
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

// Your data/model classes
import com.mike.thefashionhub.model.CartItem
import com.mike.thefashionhub.model.Product
import com.mike.thefashionhub.ui.theme.viewmodel.CartViewModel

@Composable
fun CartScreen(viewModel: CartViewModel = viewModel(), cartViewModel: CartViewModel) {
  val items = viewModel.cartItems

  Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
    Text("Your Cart", fontSize = 24.sp, fontWeight = FontWeight.Bold)

    if (items.isEmpty()) {
      Text("Your cart is empty.", modifier = Modifier.padding(top = 20.dp))
    } else {
      LazyColumn {
        items(items) { item ->
          CartItemRow(
            item = item, onRemove = { viewModel.removeItem(item) },
            product = TODO(),
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
//@Composable
//fun CartItemRow(item: CartItem, onRemove: () -> Unit) {
//  Row(
//    modifier = Modifier
//      .fillMaxWidth()
//      .padding(vertical = 8.dp),
//    horizontalArrangement = Arrangement.SpaceBetween
//  ) {
//    Column {
//      Text(item.name, fontWeight = FontWeight.SemiBold)
//      Text("Qty: ${item.quantity}")
//      Text("Price: $${item.price}")
//    }
//    IconButton(onClick = onRemove) {
//      Icon(Icons.Default.Delete, contentDescription = "Remove")
//    }
//  }
//}
@Composable
fun CartItemRow(
  product: Product,
  onRemove: () -> Unit,
  onPay: () -> Unit,
  item: CartItem
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    elevation = CardDefaults.cardElevation(4.dp)
  ) {
    Column(modifier = Modifier.padding(8.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(product.name, modifier = Modifier.weight(1f))
        Text("₹${product.price}", modifier = Modifier.padding(horizontal = 8.dp))
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp),
        horizontalArrangement = Arrangement.End
      ) {
        OutlinedButton(onClick = onPay) {
          Text("Pay")
        }
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(onClick = onRemove, colors = ButtonDefaults.outlinedButtonColors(
          contentColor = Color.Red)) {
          Text("Delete")
        }
      }
    }
  }
}

