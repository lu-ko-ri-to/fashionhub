package com.mike.thefashionhub.repository

import com.mike.thefashionhub.model.Product

class Cart {

  // In-memory storage for cart items
  private val cartItems = mutableListOf<Product>()

  // Get the list of items in the cart
  fun getCartItems(): List<Product> {
    return cartItems
  }

  // Add a product to the cart
  fun addProductToCart(product: Product) {
    cartItems.add(product)
  }

  // Remove a product from the cart
  fun removeProductFromCart(product: Product) {
    cartItems.remove(product)
  }
}
