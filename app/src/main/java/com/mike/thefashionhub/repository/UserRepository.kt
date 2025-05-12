package com.mike.thefashionhub.repository

import com.mike.thefashionhub.data.UserDao

import com.mike.thefashionhub.model.User

class UserRepository(private val userDao: UserDao) {
  suspend fun registerUser(user: User) {
    userDao.registerUser(user)
  }
//  suspend fun registerUser(user: User) {
//    val existingUser = userDao.getUserByEmail(user.email)
//    if (existingUser != null) {
//      throw IllegalStateException("User already exists")
//    }
//    userDao.registerUser(user)
//  }

  suspend fun loginUser(email: String, password: String): User? {
    return userDao.loginUser(email, password)
  }
}
