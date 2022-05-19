package com.example.movieapplication.ui.movies

import co.zsmb.rainbowcake.base.QueuedOneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.example.movieapplication.data.ResultFailure
import com.example.movieapplication.data.ResultSuccess
import kotlinx.coroutines.delay
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesPresenter: MoviesPresenter
) : RainbowCakeViewModel<MoviesViewState>(Loading) {

    fun loadMovies() = execute {
        viewState = Loading
        loadMoviesFromCache()
        delay(1000)
        viewState = Loading
        loadMoviesFromNetwork()
    }

    fun updateMovies() = execute {
        viewState = Loading
        loadMoviesFromNetwork()
    }

    private suspend fun loadMoviesFromNetwork() {
        val result = moviesPresenter.getMoviesFromNetwork()
        if (result is ResultSuccess) {
            val movies = result.value
            viewState = MoviesLoaded(movies)
            moviesPresenter.cacheMovies(movies)
        } else {
            postQueuedEvent(FailedToUpdateEvent())
        }
    }

    private suspend fun loadMoviesFromCache() {
        viewState = when (val result = moviesPresenter.getMoviesFromCache()) {
            is ResultSuccess -> {
                if (result.value.isEmpty()) {
                    Loading
                } else {
                    MoviesLoaded(result.value)
                }
            }
            is ResultFailure -> {
                Error(result.reason)
            }
        }
    }

    fun searchArticleByTitle(title: String) = execute {
        val result = moviesPresenter.searchMoviesByTitleInCache(title)
        viewState = when (result) {
            is ResultSuccess -> {
                MoviesLoaded(result.value)
            }
            is ResultFailure -> {
                Error(result.reason)
            }
        }
    }

    data class FailedToUpdateEvent(val message: String = FAILED_TO_UPDATE_MESSAGE) : QueuedOneShotEvent

    companion object {
        private const val FAILED_TO_UPDATE_MESSAGE = "Failed to update"
    }
}