package com.mike.thefashionhub.ui.theme.screens.explore


import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mike.thefashionhub.R
import com.mike.thefashionhub.navigation.ROUT_ADD_PRODUCT
import com.mike.thefashionhub.navigation.ROUT_EXPLORE
import com.mike.thefashionhub.navigation.ROUT_HOME
import com.mike.thefashionhub.navigation.ROUT_OTHERS
import com.mike.thefashionhub.navigation.ROUT_PRODUCT_LIST
import com.mike.thefashionhub.ui.theme.viewmodel.PaymentViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
  navController: NavController,
  paymentViewModel: PaymentViewModel = viewModel()

) {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    var searchQuery by remember { mutableStateOf("") }
//    val filteredProducts = productList.filter {
//      it.name.contains(searchQuery, ignoreCase = true)
//    }

    var showPaymentDialog by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current
    val paymentState by paymentViewModel.paymentState.collectAsState()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var price by remember { mutableStateOf("") }






    //Scaffold

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
      //BottomBar
      bottomBar = {
        NavigationBar(
          containerColor = Color.LightGray
        ) {
          NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedIndex == 1,
            onClick = {
              navController.navigate(ROUT_HOME)
            }
          )
          NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Favorites") },
            label = { Text("Explore") },
            selected = selectedIndex == 0,
            onClick = {
              selectedIndex = 0
              navController.navigate(ROUT_EXPLORE)
            }
          )
          NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Favorites") },
            label = { Text("Collection") },
            selected = selectedIndex == 3,
            onClick = {
              selectedIndex = 3
              navController.navigate(ROUT_OTHERS)
            }
          )
          NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Profile") },
            label = { Text("Recent Products") },
            selected = selectedIndex == 2,
            onClick = {
              selectedIndex = 2
              navController.navigate(ROUT_PRODUCT_LIST)

              //  navController.navigate(ROUT_HOME)
            }
          )

        }
      },

      //FloatingActionButton
      floatingActionButton = {
        FloatingActionButton(
          onClick = {
            navController.navigate(ROUT_ADD_PRODUCT)

          },
          containerColor = Color.LightGray
        ) {
          Icon(Icons.Default.Add, contentDescription = "Add")
        }
      },
      content = { paddingValues ->
        Column(
          modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)) // Light olive tone
            .padding(paddingValues)
        ) {
          val mContext= LocalContext.current

          // Search Bar
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

          Spacer(modifier = Modifier.height(16.dp))
          Column (
            modifier = Modifier.verticalScroll(rememberScrollState())

          ){
            Column {
              //ROW
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.tish),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape= RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{

                            showPaymentDialog = true



                        },

                    )}


                  Text(
                    text="KSH 2000",
                    fontSize = 15.sp
                  )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {

                    Image(
                      painter = painterResource(R.drawable.tiisho),
                      contentDescription = "",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )}

                    Text(
                      text = "KSH 2000",
                      fontSize = 15.sp
                    )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.tishoo),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )}
                    Text(
                      text = "Ksh 2000",
                      fontSize = 15.sp
                    )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                  Box {
                    Image(
                      painter = painterResource(R.drawable.tishooo3),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align(Alignment.TopEnd)
                        .clickable {
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text = "Ksh 2000",
                    fontSize = 15.sp
                  )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))



              }

            }
            Spacer(modifier = Modifier.height(10.dp))

            Column {
              //ROW
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                  Image(
                    painter = painterResource(R.drawable.hoodie),
                    contentDescription = "dress",
                    modifier = Modifier.size(150.dp).clip(shape= RoundedCornerShape(10.dp)),

                    contentScale = ContentScale.FillBounds
                  )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 3500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                  Image(
                    painter = painterResource(R.drawable.out2),
                    contentDescription = "",
                    modifier = Modifier.size(150.dp).clip(shape= RoundedCornerShape(10.dp)),

                    contentScale = ContentScale.FillBounds
                  )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 3500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                  Image(
                    painter = painterResource(R.drawable.hoodi3),
                    contentDescription = "dress",
                    modifier = Modifier.size(150.dp).clip(shape= RoundedCornerShape(10.dp)),

                    contentScale = ContentScale.FillBounds
                  )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )

                  }
                  Text(
                    text="aston 3500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.hoodie2),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                    }
                  Text(
                    text="Ksh 3500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))



              }

            }
            //jacket column
            Column {
              //ROW
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                  Image(
                    painter = painterResource(R.drawable.jacko),
                    contentDescription = "dress",
                    modifier = Modifier.size(150.dp).clip(shape= RoundedCornerShape(10.dp)),

                    contentScale = ContentScale.FillBounds
                  )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                    }
                  Text(
                    text="Ksh 4000",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.jacko2),
                      contentDescription = "",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 4000",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.jacko3),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 4000",
                    fontSize = 15.sp
                  )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                  Image(
                    painter = painterResource(R.drawable.jacko4),
                    contentDescription = "dress",
                    modifier = Modifier.size(150.dp).clip(shape= RoundedCornerShape(10.dp)),

                    contentScale = ContentScale.FillBounds
                  )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="KSH 4000",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))



              }

            }
            //sweater
            Column {
              //ROW
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.sw34t3r),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 2500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.sw3ater),
                      contentDescription = "",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 2500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                  Image(
                    painter = painterResource(R.drawable.swaeter1),
                    contentDescription = "dress",
                    modifier = Modifier.size(150.dp).clip(shape= RoundedCornerShape(10.dp)),

                    contentScale = ContentScale.FillBounds
                  )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 2500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                  Box{
                  Image(
                    painter = painterResource(R.drawable.sweater1),
                    contentDescription = "dress",
                    modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                    contentScale = ContentScale.FillBounds
                  )
                }
                  Text(
                    text="Ksh 2500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))



              }

            }
            //suits
            Column {
              //ROW
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.suits9),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 5500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.suits3),
                      contentDescription = "",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 5500",
                    fontSize = 15.sp
                  )
                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.suits2),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true
//
//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 2500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.suits),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true
//
//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                    }

                  Text(
                    text="KSH KSh 5500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))



              }

            }
            //dress
            Column {
              //ROW
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.dress4),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 6000",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.dresss1),
                      contentDescription = "",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true
//
//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 6000",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.dress5),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )

                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )                  }
                  Text(
                    text="Ksh 6000",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.dresss),
                      contentDescription = "dress",
                      modifier = Modifier.size(150.dp).clip(shape = RoundedCornerShape(10.dp)),

                      contentScale = ContentScale.FillBounds
                    )
                    Icon(
                      imageVector = Icons.Default.Add,
                      contentDescription = "",
                      tint = Gray,
                      modifier = Modifier.padding(20.dp).align (Alignment.TopEnd)
                        .clickable{
                          showPaymentDialog = true

//                          val simToolKitLaunchIntent =
//                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
//                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 6000",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))



              }

            }
            if (showPaymentDialog) {
              AlertDialog(
                onDismissRequest = { showPaymentDialog = false },
                title = { Text("M-Pesa Payment") },
                text = {
                  Column {
                    Text("Enter your M-Pesa phone number")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                      value = phoneNumber,
                      onValueChange = { phoneNumber = it },
                      label = { Text("Phone Number") },
                      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                  }
                },
                confirmButton = {
                  Button(
                    onClick = {

                      val productId = ""
                      paymentViewModel.initiatePayment(
                        context = context,
                        phoneNumber = phoneNumber,
                        amount = imageUri.toString(),
                        productId = productId
                      )
                      showPaymentDialog = true
                    }
                  ) {
                    Text("Pay")
                  }
                },
                dismissButton = {
                  TextButton(onClick = { showPaymentDialog = false }) {
                    Text("Cancel")
                  }
                }
              )
            }

            // Handle payment state
            when (val state = paymentState) {
              is PaymentViewModel.PaymentState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                  CircularProgressIndicator()
                }
              }
              is PaymentViewModel.PaymentState.Success -> {
                LaunchedEffect(state) {
                  Toast.makeText(
                    context,
                    "STK Push sent. Please check your phone to complete payment.",
                    Toast.LENGTH_LONG
                  ).show()
                }
              }
              is PaymentViewModel.PaymentState.Error -> {
                LaunchedEffect(state) {
                  Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                }
              }
              else -> {}
            }
          }
        }


          //hoodie column


  })}}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview(){
  ExploreScreen(navController = rememberNavController())
}


