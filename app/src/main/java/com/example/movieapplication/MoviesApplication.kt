package com.example.movieapplication

import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import com.example.movieapplication.di.AppComponent
import com.example.movieapplication.di.DaggerAppComponent

class MoviesApplication : RainbowCakeApplication() {

    override lateinit var injector: RainbowCakeComponent

    override fun setupInjector() {
        injector = DaggerAppComponent.create()
    }
}