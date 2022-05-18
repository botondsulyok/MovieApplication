package com.example.movieapplication.ui.detailedmovie

import com.example.movieapplication.domain.MoviesInteractor
import javax.inject.Inject

class MoviesPresenter @Inject constructor(
    private val moviesInteractor: MoviesInteractor
) {

    suspend fun load() {
        // todo
    }
}