package com.mike.thefashionhub.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class UserPreference(private val dataStore: DataStore<Preferences>) {

  val isLoggedIn: Flow<Boolean> = dataStore.data
    .map { preferences ->
      preferences[IS_LOGGED_IN_KEY] == true
    }

  val getUserId: Flow<Int> = dataStore.data
    .map { preferences ->
      preferences[USER_ID_KEY] ?: -1
    }

  suspend fun clearLogin() {
    dataStore.edit { preferences ->
      preferences.remove(IS_LOGGED_IN_KEY)
      preferences.remove(USER_ID_KEY)
    }
  }

  companion object {
    val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    val USER_ID_KEY = intPreferencesKey("user_id")
    }
}
