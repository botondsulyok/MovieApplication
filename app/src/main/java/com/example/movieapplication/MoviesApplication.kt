package com.example.movieapplication

import androidx.room.Room
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import com.example.movieapplication.data.cache.AppDatabase
import com.example.movieapplication.di.AppComponent
import com.example.movieapplication.di.DaggerAppComponent

class MoviesApplication : RainbowCakeApplication() {

    override lateinit var injector: RainbowCakeComponent

    companion object {
        lateinit var db: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "movies.db"
        ).build()
    }

    override fun setupInjector() {
        injector = DaggerAppComponent.create()
    }
}