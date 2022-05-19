package com.example.movieapplication

import android.os.Bundle
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import com.example.movieapplication.ui.movies.MoviesFragment

class MainActivity : SimpleNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.add(MoviesFragment())
        }
    }

}