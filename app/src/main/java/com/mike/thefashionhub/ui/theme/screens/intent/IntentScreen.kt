package com.mike.thefashionhub.ui.theme.screens.intent


import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mike.thefashionhub.data.UserPreference
import com.mike.thefashionhub.navigation.ROUT_HOME
import com.mike.thefashionhub.navigation.ROUT_START
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("QueryPermissionsNeeded")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentScreen(navController: NavController,userPreferences: UserPreference){
  val mContext= LocalContext.current
  var menuExpanded by remember { mutableStateOf(false)}

  Column (
    modifier = Modifier.fillMaxSize()
      .background(Color(0xFFF5F5DC)) // Light olive background

  ){ TopAppBar(
    title = {Text(text = "Account Settings")},
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = Color.Gray,
      titleContentColor = Color.White,
      navigationIconContentColor = Color.White,
      actionIconContentColor = Color.White

    ),
    navigationIcon = {
      IconButton(onClick = {
        navController.navigate(ROUT_HOME)
      }) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
      }
    },
    actions = {

//      IconButton(onClick = {
//        navController.navigate(ROUT_START)
//
//      }) {
//        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
//      }
      IconButton(onClick = {
        menuExpanded=true


      }) {
        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
      }

      DropdownMenu(
        expanded = menuExpanded,
        onDismissRequest = { menuExpanded = false }
      ) {
        DropdownMenuItem(
          text = { Text("Logout") },
          onClick = {
            menuExpanded = false
            CoroutineScope(Dispatchers.IO).launch {
              userPreferences.clearLogin()
              withContext(Dispatchers.Main) {
                navController.navigate("login") {
                  popUpTo(0) { inclusive = true } // Clears the entire back stack
                  launchSingleTop=true

                }
              }
            }
          }
        )
      }
//      // Sign Out Option
//      Row(
//        modifier = Modifier
//          .fillMaxWidth()
//          .clickable {
//            viewModel.signOut()
//            onSignOut()
//          },
//        verticalAlignment = Alignment.CenterVertically
//      ) {
//        Icon(
//          imageVector = Icons.Default.ExitToApp,
//          contentDescription = "Sign Out",
//          modifier = Modifier.padding(end = 12.dp)
//        )
//        Text("Sign Out")
//      }
    }






  )
    Spacer(modifier = Modifier.height(20.dp))
    //Searchbar
    var search by remember { mutableStateOf("") }
    OutlinedTextField(
      value = search,
      onValueChange = {search = it},
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
      leadingIcon = {Icon(imageVector = Icons.Default.Search, contentDescription = "")},
      placeholder = {Text(text="Search")}
    )
    Spacer(modifier = Modifier.height(40.dp))

//

    //end of top bar
    //stk




    //end of top bar
    //CALL
    Button(
      onClick = {
        val callIntent=Intent(Intent.ACTION_DIAL)
        callIntent.data="tel:0722933377".toUri()
        mContext.startActivity(callIntent)
      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Icon(imageVector = Icons.Default.Call, contentDescription = "")
      Spacer(modifier = Modifier.width(20.dp))


      Text(
        text = "CALL US HERE",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(40.dp))



    //end of top bar
    Button(
      onClick = {
        val shareIntent=Intent(Intent.ACTION_SEND)
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "The FashionhubApp")
        mContext.startActivity(Intent.createChooser(shareIntent, "Share"))

      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Icon(imageVector = Icons.Default.Share, contentDescription = "")
      Spacer(modifier = Modifier.width(20.dp))

      Text(
        text = "Share Our App",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(40.dp))



    //end of top bar
    //SMS
    Button(
      onClick = {
        val smsIntent=Intent(Intent.ACTION_SENDTO)
        smsIntent.data="smsto:0722933377".toUri()
        smsIntent.putExtra("sms_body","Hello Michael,how was your day?")
        mContext.startActivity(smsIntent)
      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Icon(imageVector = Icons.Default.Send, contentDescription = "")
      Spacer(modifier = Modifier.width(20.dp))


      Text(
        text = "Message us here",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(40.dp))



    //end of top bar
    //E-MAIL
    Button(
      onClick = {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("michaelkitangasi@gmail.com"))
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject")
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body")
        mContext.startActivity(shareIntent)

      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Icon(imageVector = Icons.Default.Email, contentDescription = "")
      Spacer(modifier = Modifier.width(20.dp))


      Text(
        text = "e-Mail us here",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))

    //end of top bar
    //SETTINGS
    Button(
      onClick = {


      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Icon(imageVector = Icons.Default.Settings, contentDescription = "")
      Spacer(modifier = Modifier.width(20.dp))


      Text(
        text = "APP SETTINGS",
        fontSize = 18.sp
      )
    }









  }

}
