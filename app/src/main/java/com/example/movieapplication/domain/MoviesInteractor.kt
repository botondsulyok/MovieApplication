package com.example.movieapplication.domain

import android.util.Log
import com.example.movieapplication.ui.movies.models.UiMovie
import javax.inject.Inject
import javax.inject.Singleton
import com.example.movieapplication.data.*
import com.example.movieapplication.data.cache.CacheMoviesDataSource
import com.example.movieapplication.data.network.NetworkMoviesDataSource

@Singleton
class MoviesInteractor @Inject constructor(
    private val networkMoviesDataSource: NetworkMoviesDataSource,
    private val cacheMoviesDataSource: CacheMoviesDataSource,
) {

    fun getMoviesFromNetwork(): Result<List<UiMovie>, String> {
        return networkMoviesDataSource.getMovies()
    }

    fun getMoviesFromCache(): Result<List<UiMovie>, String> {
        return cacheMoviesDataSource.getMoviesFromCache()
    }

    fun cacheMovies(movies: List<UiMovie>) {
        cacheMoviesDataSource.cacheMovies(movies)
    }

    fun searchMoviesByTitleInCache(title: String): Result<List<UiMovie>, String> {
        return cacheMoviesDataSource.searchMoviesByTitle(title)
    }
}