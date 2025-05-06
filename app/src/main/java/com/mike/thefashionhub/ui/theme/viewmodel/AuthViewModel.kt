package com.mike.thefashionhub.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mike.thefashionhub.model.User
import com.mike.thefashionhub.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
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
}
