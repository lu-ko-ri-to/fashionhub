package com.mike.thefashionhub.ui.theme.screens.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mike.thefashionhub.R
import com.mike.thefashionhub.model.User
import com.mike.thefashionhub.navigation.ROUT_LOGIN
import com.mike.thefashionhub.ui.theme.viewmodel.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
  authViewModel: AuthViewModel,
  navController: NavController,
  onRegisterSuccess: () -> Unit
) {
  var username by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }
  var passwordVisible by remember { mutableStateOf(false) }
  var confirmPasswordVisible by remember { mutableStateOf(false) }
  val context = LocalContext.current
  val animatedAlpha by animateFloatAsState(
    targetValue = 1f,
    animationSpec = tween(durationMillis = 1500, easing = LinearEasing),
    label = "Animated Alpha"
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFFF5F5DC)) // Light olive background

      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,

  ) {
    Image(
      painter = painterResource(R.drawable.icon3),
      contentDescription = "dress",
      modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)),

      contentScale = ContentScale.FillBounds
    )



    AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
      Text(
        "Create Your Account",
        fontSize = 40.sp,
        fontFamily = FontFamily.Cursive
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    //Username
    OutlinedTextField(
      value = username,
      onValueChange = { username = it },
      label = { Text("Username") },
      leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username Icon") },
      modifier = Modifier.fillMaxWidth()
    )
    //End of username



    Spacer(modifier = Modifier.height(8.dp))

    //Email
    OutlinedTextField(
      value = email,
      onValueChange = { email = it },
      label = { Text("Email") },
      leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      modifier = Modifier.fillMaxWidth()
    )
    //End of email

    Spacer(modifier = Modifier.height(8.dp))


    //Role
    var role by remember { mutableStateOf("user") }
    val roleOptions = listOf("user", "admin")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
      expanded = expanded,
      onExpandedChange = { expanded = !expanded }
    ) {
      OutlinedTextField(
        value = role,
        onValueChange = {},
        readOnly = true,
        label = { Text("Select Role") },
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
        modifier = Modifier.menuAnchor().fillMaxWidth()
      )
      ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
      ) {
        roleOptions.forEach { selectionOption ->
          DropdownMenuItem(
            text = { Text(selectionOption) },
            onClick = {
              role = selectionOption
              expanded = false
            }
          )
        }
      }
    }
    //End of role






    // Password Input Field with Show/Hide Toggle
    OutlinedTextField(
      value = password,
      onValueChange = { password = it },
      label = { Text("Password") },
      visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
      trailingIcon = {
        val image = if (passwordVisible) painterResource(R.drawable.eyeicon)  else painterResource(R.drawable.visibilityoff)
        IconButton(onClick = { passwordVisible = !passwordVisible }) {
          Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password")
        }
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Confirm Password Input Field with Show/Hide Toggle
    OutlinedTextField(
      value = confirmPassword,
      onValueChange = { confirmPassword = it },
      label = { Text("Confirm Password") },
      visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password Icon") },
      trailingIcon = {
        val image = if (confirmPasswordVisible) painterResource(R.drawable.eyeicon)  else painterResource(R.drawable.visibilityoff)
        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
          Icon(image, contentDescription = if (confirmPasswordVisible) "Hide Password" else "Show Password")
        }
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(5.dp))

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(
          brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
          ),
          shape = MaterialTheme.shapes.medium
        ),
      contentAlignment = Alignment.Center
    ) {
      Button(
        onClick = {
          if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
          } else if (password != confirmPassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
          } else {
            authViewModel.registerUser(User(username = username, email = email, role = role, password = password))
            onRegisterSuccess()
          }
        },
        modifier = Modifier.fillMaxSize(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
      ) {
        Text("Register", color = Color.White)
      }
    }

    Spacer(modifier = Modifier.height(5.dp))

    TextButton(
      onClick = { navController.navigate(ROUT_LOGIN) }
    ) {
      Text("Already have an account? Login")
    }
  }
}
