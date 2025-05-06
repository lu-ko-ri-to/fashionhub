package com.mike.thefashionhub.ui.theme.screens.start


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mike.thefashionhub.R
import com.mike.thefashionhub.navigation.ROUT_LOGIN
import com.mike.thefashionhub.navigation.ROUT_REGISTER
import com.mike.thefashionhub.ui.theme.newBlue


@Composable
fun StartScreen(navController: NavController) {
  Column(
    modifier = Modifier.fillMaxSize()
      .background(Color(0xFFF5F5DC)) // Light olive background


    .padding(20.dp),
  verticalArrangement = Arrangement.Center,
  horizontalAlignment = Alignment.CenterHorizontally


    ) {
    Image(
      painter = painterResource(R.drawable.icon),
      contentDescription = "",
      modifier = Modifier.size(300.dp)

    )

    Spacer(modifier = Modifier.height(20.dp))

    Button(
      onClick = {
        navController.navigate(ROUT_LOGIN)
      },
      shape = RoundedCornerShape(10.dp),

      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Text(
        text = "Log In Here!!",
        fontSize = 18.sp
      )
    }
    Spacer(modifier = Modifier.height(20.dp))



    Button(
      onClick = {
        navController.navigate(ROUT_REGISTER)
      },
      shape = RoundedCornerShape(10.dp),

      modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
    ) {
      Text(
        text = "Register Here",
        fontSize = 18.sp
      )
    }



  }
}




@Preview(showBackground = true)
@Composable
fun StartScreenPreview(){
  StartScreen(navController = rememberNavController())
}


