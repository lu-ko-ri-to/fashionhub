package com.mike.thefashionhub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mike.thefashionhub.model.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
  abstract fun userDao(): UserDao

  companion object {
    @Volatile
    private var INSTANCE: UserDatabase? = null
////this codes makes the database up to date
    fun getDatabase(context: Context): UserDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          UserDatabase::class.java,
          "user_database"
        )
          .fallbackToDestructiveMigration() // DANGEROUS IN PRODUCTION, OK FOR NOW
          .build()
        INSTANCE = instance
        instance
      }
    }
  }
}
