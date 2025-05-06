package com.mike.thefashionhub.ui.theme.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mike.thefashionhub.R
import com.mike.thefashionhub.navigation.ROUT_START
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController){
  val coroutine = rememberCoroutineScope()
  coroutine.launch {
    delay(1000)
    navController.navigate(ROUT_START)

  }



  Column (
    modifier = Modifier.fillMaxSize()
      .background(color = Color.Black),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally

  ){
    Image(
      painter = painterResource(R.drawable.icon2),
      contentDescription = "",
      modifier = Modifier.fillMaxSize()
    )

  }


}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
  SplashScreen(navController = rememberNavController())
}

