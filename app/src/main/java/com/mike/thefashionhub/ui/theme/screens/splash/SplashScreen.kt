package com.mike.thefashionhub.ui.theme.screens.splash

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mike.thefashionhub.R
import com.mike.thefashionhub.data.UserPreference
import com.mike.thefashionhub.navigation.ROUT_START
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.ProcessBuilder.Redirect

//
//@RequiresApi(Build.VERSION_CODES.O)
//@SuppressLint("CoroutineCreationDuringComposition")
//@Composable
//fun SplashScreen(navController: NavController){
//  val coroutine = rememberCoroutineScope()
//  coroutine.launch {
//    delay(1000)
//    navController.navigate(ROUT_START)
//
//
//    val isLoggedIn = userPreferences.isLoggedIn.first()
//    val userId = userPreferences.getUserId.first()
//
//    if (isLoggedIn && userId != -1) {
//      navController.navigate("home/$userId") {
//        popUpTo("splash") { inclusive = true }
//      }
//    } else {
//      navController.navigate("login") {
//        popUpTo("splash")
//
//
//  }
//
//
//
//  Column (
//    modifier = Modifier.fillMaxSize()
//      .background(color = Color.Black),
//    verticalArrangement = Arrangement.Center,
//    horizontalAlignment = Alignment.CenterHorizontally
//
//  ){
//    Image(
//      painter = painterResource(R.drawable.icon2),
//      contentDescription = "",
//      modifier = Modifier.fillMaxSize()
//    )
//
//  }
//
//
//}}}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SplashScreen(navController: NavController,userPreferences: UserPreference) {

  // This should come from a ViewModel or DI

  LaunchedEffect(Unit) {
    delay(1000)

    val isLoggedIn = userPreferences.isLoggedIn.first()
    val userId = userPreferences.getUserId.first()

    if (isLoggedIn && userId != -1) {
      navController.navigate("home/$userId") {
        popUpTo("splash") { inclusive = true }
      }
    } else {
      navController.navigate("start") {
        popUpTo("splash") { inclusive = true }
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color.Black),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(R.drawable.icon2),
      contentDescription = null,
      modifier = Modifier.fillMaxSize()
    )
    }
}

////
////@RequiresApi(Build.VERSION_CODES.O)
////@Preview(showBackground = true)
////@Composable
////fun SplashScreenPreview(){
////  SplashScreen(navController = rememberNavController())
//}

