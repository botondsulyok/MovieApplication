package com.example.movieapplication.ui.movies

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesPresenter: MoviesPresenter
) : RainbowCakeViewModel<MoviesViewState>(Loading) {

    fun loadMovies() = execute {
        // todo
    }
}