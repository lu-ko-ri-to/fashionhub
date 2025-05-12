package com.mike.thefashionhub.ui.theme.screens.products



import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mike.thefashionhub.R
import com.mike.thefashionhub.model.Product
import com.mike.thefashionhub.navigation.ROUT_ADD_PRODUCT
import com.mike.thefashionhub.navigation.ROUT_EDIT_PRODUCT
import com.mike.thefashionhub.navigation.ROUT_HOME
import com.mike.thefashionhub.navigation.ROUT_PRODUCTADMIN_LIST
import com.mike.thefashionhub.navigation.ROUT_PRODUCT_LIST
import com.mike.thefashionhub.navigation.editProductRoute
import com.mike.thefashionhub.ui.theme.viewmodel.ProductViewModel
import java.io.IOException
import java.io.OutputStream


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAdminListScreen(navController: NavController,
                      viewModel: ProductViewModel) {
  val mContext= LocalContext.current

  val productList by viewModel.allProducts.observeAsState(emptyList())
  var showMenu by remember { mutableStateOf(false) }
  var searchQuery by remember { mutableStateOf("") }


  val filteredProducts = productList.filter {
    it.name.contains(searchQuery, ignoreCase = true)
  }

  Scaffold(
    topBar = {
      Column {
        TopAppBar(
          title = { Text("Products", fontSize = 20.sp) },
          colors = TopAppBarDefaults.mediumTopAppBarColors(Color.LightGray),
          actions = {
            IconButton(onClick = { showMenu = true }) {
              Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
            }
            DropdownMenu(
              expanded = showMenu,
              onDismissRequest = { showMenu = false }
            ) {
              DropdownMenuItem(
                text = { Text("Home") },
                onClick = {
                  navController.navigate(ROUT_HOME)
                  showMenu = false
                }
              )
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


        //Search Bar
        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
          placeholder = { Text("Search products...") },
          singleLine = true,
          leadingIcon = {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = "Search",
              tint = Color.Gray
            )
          },
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,  // Border color when focused
            unfocusedBorderColor = Color.Gray, // Border color when not focused
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.DarkGray
          )
        )
      }
    },
//    bottomBar = {
//      NavigationBar(
//        containerColor = Color.LightGray
//      ){
//        NavigationBarItem(
//          icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//          label = { Text("Home") },
//          selected = selectedIndex == 0,
//          onClick = { selectedIndex = 0
//            navController.navigate(ROUT_HOME)
//          }
//        )
//        NavigationBarItem(
//          icon = { Icon(Icons.Default.Search, contentDescription = "Explore") },
//          label = { Text("Explore") },
//          selected = selectedIndex == 1,
//          onClick = { selectedIndex = 1
//            navController.navigate(ROUT_EXPLORE)
//          }
//        )
//
//
//        NavigationBarItem(
//          icon = { Icon(Icons.Default.List, contentDescription = "Favorites") },
//          label = { Text("Collection") },
//          selected = selectedIndex == 3,
//          onClick = { selectedIndex = 3
//            navController.navigate(ROUT_OTHERS)
//          }
//        )
//        NavigationBarItem(
//          icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Profile") },
//          label = { Text("Insta Buy") },
//          selected = selectedIndex == 2,
//          onClick = { selectedIndex = 2
//            navController.navigate(ROUT_PRODUCT_LIST)
//
//            //  navController.navigate(ROUT_HOME)
//          }
//        )
//
//      }
//    },
    bottomBar = { BottomNavigationBarr(navController) }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF5F5DC)) // Light olive tone

        .padding(paddingValues)
        .padding(16.dp)
    ) {
      LazyColumn {
        items(filteredProducts.size) { index ->
          ProductItem1(navController, filteredProducts[index], viewModel)
        }
      }
    }
  }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ProductItem1(navController: NavController, product: Product, viewModel: ProductViewModel) {
  val mContext= LocalContext.current

  val painter: Painter = rememberAsyncImagePainter(
    model = product.imagePath?.let { Uri.parse(it) } ?: Uri.EMPTY
  )
  val context = LocalContext.current

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp)
      .clickable {
        if (product.id != 0) {
          navController.navigate(ROUT_EDIT_PRODUCT)
        }
      },
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(4.dp)
  ) {
    Box(modifier = Modifier.fillMaxWidth()) {
      // Product Image
      Image(
        painter = painter,
        contentDescription = "Product Image",
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp),
        contentScale = ContentScale.Crop
      )

      // Gradient Overlay
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(120.dp)
          .align(Alignment.BottomStart)
          .background(
            Brush.verticalGradient(
              colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
            )
          )
      )

      // Product Info
      Column(
        modifier = Modifier
          .align(Alignment.BottomStart)
          .padding(start = 12.dp, bottom = 60.dp)
      ) {
        Text(
          text = product.name,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Text(
          text = "Price: Ksh${product.price}",
          fontSize = 16.sp,
          color = Color.White
        )
      }

      // Buttons (Message, Edit, Delete, Download PDF)
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
          .align(Alignment.BottomEnd)
      ) {
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          // Message Seller
          OutlinedButton(
            onClick = {
              val smsIntent = Intent(Intent.ACTION_SENDTO)
              smsIntent.data = "smsto:${product.phone}".toUri()
              smsIntent.putExtra("sms_body", "Hello Seller,...?")
              context.startActivity(smsIntent)
            },
            shape = RoundedCornerShape(8.dp),
          ) {
            Row {
              Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Message Seller"
              )
              Spacer(modifier = Modifier.width(3.dp))
              Text(text = "Message Seller")
            }
          }
          //pay for product
          IconButton(
            onClick = {

              val simToolKitLaunchIntent =
                mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
              simToolKitLaunchIntent?.let { mContext.startActivity(it) }

            }
          ) {
            Icon(

              imageVector = Icons.Default.ShoppingCart,
              contentDescription = "Pay",
              tint = Color.White
            )
          }

          // Edit Product
          IconButton(
            onClick = {
              navController.navigate(editProductRoute(product.id))
            }
          ) {
            Icon(

              imageVector = Icons.Default.Edit,
              contentDescription = "Edit",
              tint = Color.White
            )
          }

          // Delete Product
          IconButton(
            onClick = { viewModel.deleteProduct(product) }
          ) {
            Icon(
              imageVector = Icons.Default.Delete,
              contentDescription = "Delete",
              tint = Color.White
            )
          }

          // Download PDF
          IconButton(
            onClick = { generateProductPDF1(context, product) }
          ) {
            Icon(
              painter = painterResource(R.drawable.img_3),
              contentDescription = "",
              tint = Color.White
            )
          }
        }
      }
    }
  }
}

@RequiresApi(Build.VERSION_CODES.Q)
fun generateProductPDF1(context: Context, product: Product) {
  val pdfDocument = PdfDocument()
  val pageInfo = PdfDocument.PageInfo.Builder(300, 500, 1).create()
  val page = pdfDocument.startPage(pageInfo)
  val canvas = page.canvas
  val paint = android.graphics.Paint()

  val bitmap: Bitmap? = try {
    product.imagePath?.let {
      val uri = Uri.parse(it)
      context.contentResolver.openInputStream(uri)?.use { inputStream ->
        BitmapFactory.decodeStream(inputStream)
      }
    }
  } catch (e: Exception) {
    e.printStackTrace()
    null
  }

  bitmap?.let {
    val scaledBitmap = Bitmap.createScaledBitmap(it, 250, 150, false)
    canvas.drawBitmap(scaledBitmap, 25f, 20f, paint)
  }

  paint.textSize = 16f
  paint.isFakeBoldText = true
  canvas.drawText("Product Details", 80f, 200f, paint)

  paint.textSize = 12f
  paint.isFakeBoldText = false
  canvas.drawText("Name: ${product.name}", 50f, 230f, paint)
  canvas.drawText("Price: Ksh${product.price}", 50f, 250f, paint)
  canvas.drawText("Seller Phone: ${product.phone}", 50f, 270f, paint)

  pdfDocument.finishPage(page)

  // Save PDF using MediaStore (Scoped Storage)
  val fileName = "${product.name}_Details.pdf"
  val contentValues = ContentValues().apply {
    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
    put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
  }

  val contentResolver = context.contentResolver
  val uri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

  if (uri != null) {
    try {
      val outputStream: OutputStream? = contentResolver.openOutputStream(uri)
      if (outputStream != null) {
        pdfDocument.writeTo(outputStream)
        Toast.makeText(context, "PDF saved to Downloads!", Toast.LENGTH_LONG).show()
      }
      outputStream?.close()
    } catch (e: IOException) {
      e.printStackTrace()
      Toast.makeText(context, "Failed to save PDF!", Toast.LENGTH_LONG).show()
    }
  } else {
    Toast.makeText(context, "Failed to create file!", Toast.LENGTH_LONG).show()
  }

  pdfDocument.close()
}

// Bottom Navigation Bar Component
@Composable
fun BottomNavigationBarr(navController: NavController) {
  NavigationBar(
    containerColor = Color(0xFFA2B9A2),
    contentColor = Color.White
  ) {
    NavigationBarItem(
      selected = false,
      onClick = { navController.navigate(ROUT_PRODUCTADMIN_LIST) },
      icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Your Products") },
      label = { Text("Your Products") }
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


