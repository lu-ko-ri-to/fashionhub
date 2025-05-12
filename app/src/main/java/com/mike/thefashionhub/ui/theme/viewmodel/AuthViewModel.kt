package com.mike.thefashionhub.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.mike.thefashionhub.data.UserPreference
import com.mike.thefashionhub.model.User
import com.mike.thefashionhub.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
  private val repository: UserRepository,
  private val userPreference: UserPreference
) : ViewModel() {
  var loggedInUser: ((User?) -> Unit)? = null

  fun registerUser(user: User) {
    viewModelScope.launch {
      repository.registerUser(user)
    }
  }

  fun loginUser(email: String, password: String) {
    viewModelScope.launch {
      val user = repository.loginUser(email, password)
      loggedInUser?.invoke(user)
    }
  }

  fun logout(navController: NavController) {
    viewModelScope.launch(Dispatchers.IO) {
      userPreference.clearLogin()
      withContext(Dispatchers.Main) {
        navController.navigate("login") {
          popUpTo("home") { inclusive = true }
        }
      }
      }
    }
}




