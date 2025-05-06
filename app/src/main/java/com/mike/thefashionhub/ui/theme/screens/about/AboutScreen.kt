package com.mike.thefashionhub.ui.theme.screens.about


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun AboutScreen(navController: NavController) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFFF5F5DC)) // Light olive tone
      .padding(16.dp)
  ) {
    // Search Bar
    OutlinedTextField(
      value = "",
      onValueChange = {},
      placeholder = { Text("Search...") },
      leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
      modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Horizontal Category Tabs
    val tabs = listOf("Women", "Kids", "Men", "Home", "Curve")
    var selectedTab by remember { mutableIntStateOf(0) }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      itemsIndexed(tabs) { index, tab ->
        val isSelected = index == selectedTab
        Text(
          text = tab,
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) Color(0xFFBA8B65) else Color.Transparent)
            .clickable { selectedTab = index }
            .padding(horizontal = 16.dp, vertical = 8.dp),
          color = if (isSelected) Color.White else Color.Black
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Banner Section
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
      // Grid Category
    )
    val categories = listOf(
      "Sweaters", "Sport Wear", "Hats",
      "Socks", "Accessories", "Dresses"
    )

    LazyVerticalGrid(
      columns = GridCells.Fixed(3),
      verticalArrangement = Arrangement.spacedBy(12.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.weight(1f)
    ) {
      items(categories) { category ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Box(
            modifier = Modifier
              .size(80.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(Color.LightGray),
            contentAlignment = Alignment.Center
          ) {
            // Image Placeholder
            Icon(Icons.Default.Face, contentDescription = null)
          }
          // Bottom Navigation

        }
      }
    }
  }
}










      @Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
  AboutScreen(navController = rememberNavController())
}


