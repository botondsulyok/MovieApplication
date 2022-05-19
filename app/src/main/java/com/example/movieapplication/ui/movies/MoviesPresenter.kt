package com.example.movieapplication.ui.movies

import co.zsmb.rainbowcake.withIOContext
import com.example.movieapplication.data.*
import com.example.movieapplication.domain.MoviesInteractor
import com.example.movieapplication.ui.movies.models.UiMovie
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class MoviesPresenter @Inject constructor(
    private val moviesInteractor: MoviesInteractor
) {

    suspend fun getMoviesFromNetwork(): Result<List<UiMovie>, String> = withIOContext {
        handleInteractorResponse(moviesInteractor.getMoviesFromNetwork())
    }

    suspend fun getMoviesFromCache(): Result<List<UiMovie>, String> = withIOContext {
        handleInteractorResponse(moviesInteractor.getMoviesFromCache())
    }

    suspend fun cacheMovies(movies: List<UiMovie>) = withIOContext {
        moviesInteractor.cacheMovies(movies)
    }

    suspend fun searchMoviesByTitleInCache(title: String): Result<List<UiMovie>, String> = withIOContext {
        TODO()
    }

    private fun handleInteractorResponse(result: Result<List<UiMovie>, String>): Result<List<UiMovie>, String> {
        return when (result) {
            is ResultSuccess -> {
                ResultSuccess(result.value.formatData())
            }
            is ResultFailure -> result
        }
    }

    private fun List<UiMovie>.formatData(): List<UiMovie> {
        return map { it.copy(formattedBudget = it.budget?.toStringNoDecimal()) }.sortedBy { it.releaseDate }
    }

    private fun Long.toStringNoDecimal(): String? {
        val decimalFormat = DecimalFormat("#,###.##")
        return decimalFormat.format(this)
    }
}