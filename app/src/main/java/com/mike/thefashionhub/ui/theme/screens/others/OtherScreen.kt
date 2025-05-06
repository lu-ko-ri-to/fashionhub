package com.mike.thefashionhub.ui.theme.screens.others

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mike.thefashionhub.R
import com.mike.thefashionhub.navigation.ROUT_EXPLORE
import com.mike.thefashionhub.navigation.ROUT_HOME
import com.mike.thefashionhub.navigation.ROUT_OTHERS
import com.mike.thefashionhub.navigation.ROUT_PRODUCT_LIST


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OthersScreen(navController: NavController) {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    //Scaffold
    var searchQuery by remember { mutableStateOf("") }


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
            selected = selectedIndex == 5,
            onClick = {
              selectedIndex = 5
              navController.navigate(ROUT_EXPLORE)
            }
          )
          NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Favorites") },
            label = { Text("Collection") },
            selected = selectedIndex == 0,
            onClick = {
              selectedIndex = 0
              navController.navigate(ROUT_OTHERS)
            }
          )
          NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Profile") },
            label = { Text("Insta Buy") },
            selected = selectedIndex == 2,
            onClick = {
              selectedIndex = 2
              navController.navigate(ROUT_PRODUCT_LIST)
            }
          )

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
                tint = Gray
              )
            },
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = Color.Black,  // Border color when focused
              unfocusedBorderColor = Gray, // Border color when not focused
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
              Text(
                text="Shoes",
                fontSize = 35.sp
              )
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
//
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){


                  Box {
                    Image(
                      painter = painterResource(R.drawable.jj),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )}


                  Text(
                    text="KSH 4900",
                    fontSize = 15.sp
                  )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {

                    Image(
                      painter = painterResource(R.drawable.j4),
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
                    text = "KSH 3500",
                    fontSize = 15.sp
                  )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.j41),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )}
                  Text(
                    text = "Ksh 4000",
                    fontSize = 15.sp
                  )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                  Box {
                    Image(
                      painter = painterResource(R.drawable.shoes4),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text = "Ksh 4500",
                    fontSize = 15.sp
                  )


                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))



              }

            }
            Spacer(modifier = Modifier.height(20.dp))

            Column {
              Text(
                text="Ladywear" ,

                fontSize = 35.sp
              )
              //ROW
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                    Image(
                      painter = painterResource(R.drawable.ladies2),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 3800",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                    Image(
                      painter = painterResource(R.drawable.ladies3),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 2600",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                    Image(
                      painter = painterResource(R.drawable.ladies4),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )

                  }
                  Text(
                    text="aston 3000",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.ladies),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 4500",
                    fontSize = 15.sp
                  )

                }
                //end of column



              }

            }
            Spacer(modifier = Modifier.height(20.dp))

            //jacket column
            Column {
              //ROW
              Text(
                text="Handbags" ,

                fontSize = 35.sp
              )

              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box{
                    Image(
                      painter = painterResource(R.drawable.bag3),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 5200",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.bag5),
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

                      )
                  }
                  Text(
                    text="Ksh 4850",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.bag1),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

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
                      painter = painterResource(R.drawable.bag4),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="KSH 4300",
                    fontSize = 15.sp
                  )

                }
                //end of column


              }

            }
            Spacer(modifier = Modifier.height(20.dp))



            //sweater
            Column {
              //ROW
              Text(
                text="Watches" ,

                fontSize = 35.sp
              )
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.watch3),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

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
                  Box {
                    Image(
                      painter = painterResource(R.drawable.watch4),
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
                      painter = painterResource(R.drawable.watch2),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

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
                      painter = painterResource(R.drawable.watch1),
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




              }

            }
            Spacer(modifier = Modifier.height(20.dp))
            //suits
            Column {
              //ROW
              Text(
                text="Workout Gear" ,

                fontSize = 35.sp
              )
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.gym3),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 1500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.img_1),
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

                      )
                  }
                  Text(
                    text="Ksh 1500",
                    fontSize = 15.sp
                  )
                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.gym),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

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
                      painter = painterResource(R.drawable.gym2),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }

                  Text(
                    text="KSH KSh 1500",
                    fontSize = 15.sp
                  )

                }
                //end of column




              }

            }
            Spacer(modifier = Modifier.height(20.dp))
            //dress
            Column {
              //ROW
              Text(
                text="Socks" ,

                fontSize = 35.sp
              )
              Row (
                modifier = Modifier.padding(start=20.dp).horizontalScroll(rememberScrollState())
              ){
                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.socks3),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 400",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))

                //column
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.socks2),
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

                      )
                  }
                  Text(
                    text="Ksh 600",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.socks),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )                  }
                  Text(
                    text="Ksh 500",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                  Box {
                    Image(
                      painter = painterResource(R.drawable.img),
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
                          val simToolKitLaunchIntent =
                            mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                          simToolKitLaunchIntent?.let { mContext.startActivity(it) }

                        },

                      )
                  }
                  Text(
                    text="Ksh 800",
                    fontSize = 15.sp
                  )

                }
                //end of column
                Spacer(modifier = Modifier.width(20.dp))



              }

            }

          }
          //hoodie column


        }})}}
@Preview(showBackground = true)
@Composable
fun OthersScreenPreview(){
  OthersScreen(navController = rememberNavController())
}


