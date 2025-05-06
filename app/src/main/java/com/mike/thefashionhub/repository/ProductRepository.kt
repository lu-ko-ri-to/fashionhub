package com.mike.thefashionhub.repository


import android.content.Context
import com.mike.thefashionhub.data.ProductDatabase
import com.mike.thefashionhub.model.Product

class ProductRepository(context: Context) {
  private val productDao = ProductDatabase.getDatabase(context).productDao()

  suspend fun insertProduct(product: Product) {
    productDao.insertProduct(product)
  }

  fun getAllProducts() = productDao.getAllProducts()

  suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)
}
