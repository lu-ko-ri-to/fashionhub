package com.mike.thefashionhub

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.mike.thefashionhub.data.UserPreference
import com.mike.thefashionhub.navigation.AppNavHost
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name="user_prefs")

class MainActivity : ComponentActivity() {
  @RequiresApi(Build.VERSION_CODES.Q)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val userPreference = UserPreference(applicationContext.dataStore)

    setContent {
      // Provide userPreference to AppNavHost if needed
      AppNavHost(userPreference = userPreference)
      }
  }
}
