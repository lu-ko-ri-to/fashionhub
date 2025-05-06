package com.mike.thefashionhub.ui.theme.screens.products

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mike.thefashionhub.R
import com.mike.thefashionhub.navigation.ROUT_ADD_PRODUCT
import com.mike.thefashionhub.navigation.ROUT_HOME
import com.mike.thefashionhub.navigation.ROUT_PRODUCT_LIST
import com.mike.thefashionhub.ui.theme.viewmodel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController, viewModel: ProductViewModel) {
  var name by remember { mutableStateOf("") }
  var price by remember { mutableStateOf("") }
  var phone by remember { mutableStateOf("") }
  var imageUri by remember { mutableStateOf<Uri?>(null) }
  var showMenu by remember { mutableStateOf(false) }

  val context = LocalContext.current

  // Image Picker Launcher
  val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
    uri?.let {
      imageUri = it
      Log.d("ImagePicker", "Selected image URI: $it")
    }
  }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Add Product", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
        navigationIcon = {
          IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
          }
        },
        actions = {
          IconButton(onClick = { showMenu = true }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
          }
          DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
          ) {
            DropdownMenuItem(
              text = { Text("Product List") },
              onClick = {
                navController.navigate(ROUT_PRODUCT_LIST)
                showMenu = false
              }
            )
            DropdownMenuItem(
              text = { Text("Add Product") },
              onClick = {
                navController.navigate(ROUT_ADD_PRODUCT)
                showMenu = false
              }
            )
          }
        }
      )
    },
    bottomBar = {
      BottomNavigationBar(navController)
    },
    content = { paddingValues ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(Color(0xFFF5F5DC)) // Light olive tone

          .padding(paddingValues)
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Product Name
        OutlinedTextField(
          value = name,
          onValueChange = { name = it },
          label = { Text("Product Name") },
          leadingIcon = { Icon(painter = painterResource(R.drawable.img_37), contentDescription = "Name",modifier = Modifier.size(20.dp)) },
          modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Product Price
        OutlinedTextField(
          value = price,
          onValueChange = { price = it },
          label = { Text("Product Price") },
          leadingIcon = { Icon(painter = painterResource(R.drawable.img_17), contentDescription = "Price",modifier = Modifier.size(20.dp)) },
          modifier = Modifier.fillMaxWidth()
        )



        Spacer(modifier = Modifier.height(10.dp))

        // Phone Number
        OutlinedTextField(
          value = phone,
          onValueChange = { phone = it },
          label = { Text("Phone Number") },
          leadingIcon = { Icon(painter = painterResource(R.drawable.img_18), contentDescription = "Price",modifier = Modifier.size(20.dp)) },
          modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(16.dp))

        // Image Picker Box
        Box(
          modifier = Modifier
            .size(200.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
            .clickable { imagePicker.launch("image/*") },
          contentAlignment = Alignment.Center
        ) {
          if (imageUri != null) {
            Image(
              painter = rememberAsyncImagePainter(model = imageUri),
              contentDescription = "Selected Image",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )
          } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Icon(painter = painterResource(R.drawable.img_19), contentDescription = "Pick Image")
              Text("Tap to pick image", color = Color.DarkGray)
            }
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Add Product Button
        Button(
          onClick = {
            val priceValue = price.toDoubleOrNull()
            if (priceValue != null) {
              imageUri?.toString()?.let { viewModel.addProduct(name, priceValue, phone,it) }
              navController.popBackStack()
              navController.navigate(ROUT_PRODUCT_LIST)
            }
          },
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
          Text("Add Product", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
      }
    }
  )
}

// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBar(navController: NavController) {
  NavigationBar(
    containerColor = Color(0xFF6F6A72),
    contentColor = Color.White
  ) {
    NavigationBarItem(
      selected = false,
      onClick = { navController.navigate(ROUT_PRODUCT_LIST) },
      icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Product List") },
      label = { Text("Product List") }
    )
    NavigationBarItem(
      selected = false,
      onClick = { navController.navigate(ROUT_ADD_PRODUCT) },
      icon = { Icon(Icons.Default.AddCircle, contentDescription = "Add Product") },
      label = { Text("Add") }
    )


    NavigationBarItem(
      selected = false,
      onClick = { navController.navigate(ROUT_HOME) },
      icon = { Icon(Icons.Default.Home, contentDescription = "") },
      label = { Text("Home") }
    )
  }
}
