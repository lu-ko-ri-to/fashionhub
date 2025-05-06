package com.mike.thefashionhub.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mike.thefashionhub.model.Product

@Dao
interface ProductDao {
  @Query("SELECT * FROM products")
  fun getAllProducts(): LiveData<List<Product>>

  @Insert
  suspend fun insertProduct(product: Product)

  @Update
  suspend fun updateProduct(product: Product)

  @Delete
  suspend fun deleteProduct(product: Product)
}
