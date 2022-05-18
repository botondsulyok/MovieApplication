package com.example.movieapplication.ui.movies

import co.zsmb.rainbowcake.withIOContext
import com.example.movieapplication.domain.MoviesInteractor
import com.example.movieapplication.ui.movies.models.UiMovie
import javax.inject.Inject
import com.example.movieapplication.data.*

class MoviesPresenter @Inject constructor(
    private val moviesInteractor: MoviesInteractor
) {

    suspend fun getMoviesFromNetwork(): Result<List<UiMovie>, String> = withIOContext {
        return@withIOContext moviesInteractor.getMoviesFromNetwork()
    }

    suspend fun getMoviesFromCache(): Result<List<UiMovie>, String> = withIOContext {
        TODO()
    }

    suspend fun cacheMovies(movies: List<UiMovie>): Nothing = withIOContext {
        TODO()
    }

    suspend fun searchMoviesByTitleInCache(title: String): Result<List<UiMovie>, String> = withIOContext {
        TODO()
    }
}