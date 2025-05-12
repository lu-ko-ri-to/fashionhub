package com.mike.thefashionhub.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")

data class CartItem(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  val name: String,
  val price: Double,
  val imageResId: Int,

  var quantity: Int=1

)

