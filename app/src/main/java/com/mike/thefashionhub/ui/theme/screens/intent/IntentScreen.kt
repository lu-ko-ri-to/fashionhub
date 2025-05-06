package com.mike.thefashionhub.ui.theme.screens.intent


import android.annotation.SuppressLint
import android.content.Intent
import android.provider.MediaStore
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController



@SuppressLint("QueryPermissionsNeeded")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntentScreen(navController: NavController){
  val mContext= LocalContext.current
  Column (
    modifier = Modifier.fillMaxSize()
  ){ TopAppBar(
    title = {Text(text = "Intents")},
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = Color.Gray,
      titleContentColor = Color.White,
      navigationIconContentColor = Color.White,
      actionIconContentColor = Color.White

    ),
    navigationIcon = {
      IconButton(onClick = {}) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
      }
    },
    actions = {
      IconButton(onClick = {}) {
        Icon(imageVector = Icons.Default.Share, contentDescription = "")
      }
      IconButton(onClick = {}) {
        Icon(imageVector = Icons.Default.Settings, contentDescription = "")
      }
      IconButton(onClick = {


      }) {
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
      }
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
    Spacer(modifier = Modifier.height(20.dp))



    //end of top bar
    //stk
    Button(
      onClick = {
        val simToolKitLaunchIntent =
          mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
        simToolKitLaunchIntent?.let { mContext.startActivity(it) }

      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Text(
        text = "M-PESA",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))



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
      Text(
        text = "CALL",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))



    //end of top bar
    Button(
      onClick = {
        val shareIntent=Intent(Intent.ACTION_SEND)
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
        mContext.startActivity(Intent.createChooser(shareIntent, "Share"))
      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Text(
        text = "Share",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))



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
      Text(
        text = "SMS",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))



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
      Text(
        text = "e-Mail",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))



    //end of top bar
    //CAMERA
    Button(
      onClick = {
        val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(mContext.packageManager)!=null){
          mContext.startActivity(cameraIntent)
        }else{
          println("Camera app is not available")
        }
      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Text(
        text = "CAMERA",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))



    //end of top bar
    //SETTINGS
    Button(
      onClick = {
        val settingsIntent=Intent(Settings.ACTION_SETTINGS)
        mContext.startActivity(settingsIntent)

      },
      shape = RoundedCornerShape(10.dp),
      colors = ButtonDefaults.buttonColors(Color.Gray),
      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Text(
        text = "SETTINGS",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))








  }


}

@Preview(showBackground = true)
@Composable
fun IntentScreenPreview(){
  IntentScreen(navController = rememberNavController())
}
