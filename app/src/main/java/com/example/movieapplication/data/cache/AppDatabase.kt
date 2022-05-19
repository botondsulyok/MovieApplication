package com.example.movieapplication.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapplication.data.cache.models.RoomMovie

@Database(entities = [RoomMovie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao

}