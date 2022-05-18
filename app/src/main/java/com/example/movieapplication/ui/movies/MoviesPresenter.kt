package com.example.movieapplication.ui.movies

import com.example.movieapplication.domain.MoviesInteractor
import javax.inject.Inject

class MoviesPresenter @Inject constructor(
    private val moviesInteractor: MoviesInteractor
) {

    suspend fun load() {
        // todo
    }
}