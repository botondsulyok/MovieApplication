package com.example.movieapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import com.example.movieapplication.ui.detailedmovie.MoviesFragment

class MainActivity : SimpleNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.add(MoviesFragment())
        }
    }

}