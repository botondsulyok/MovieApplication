package com.example.movieapplication.domain

import com.example.movieapplication.ui.movies.models.UiMovie
import javax.inject.Inject
import javax.inject.Singleton
import com.example.movieapplication.data.*
import com.example.movieapplication.data.network.NetworkMoviesDataSource

@Singleton
class MoviesInteractor @Inject constructor(
    private val networkMoviesDataSource: NetworkMoviesDataSource
) {

    fun getMoviesFromNetwork(): Result<List<UiMovie>, String> {
        return networkMoviesDataSource.getMovies()
    }

}