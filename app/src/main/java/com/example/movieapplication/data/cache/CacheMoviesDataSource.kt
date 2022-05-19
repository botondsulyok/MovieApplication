package com.example.movieapplication.data.cache

import android.util.Log
import com.example.movieapplication.MoviesApplication
import com.example.movieapplication.data.*
import com.example.movieapplication.data.cache.models.RoomMovie
import com.example.movieapplication.ui.movies.models.UiMovie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheMoviesDataSource @Inject constructor() {

    val moviesDao = MoviesApplication.db.moviesDao()

    fun getMoviesFromCache(): Result<List<UiMovie>, String> {
        return try {
            val movies = moviesDao.getMovies().map { it.toUiMovie() }
            ResultSuccess(movies)
        }
        catch (e: Exception) {
            ResultFailure(e.message.toString())
        }
    }

    fun cacheMovies(movies: List<UiMovie>) {
        val roomMovies = movies.map { it.toRoomMovie() }
        try {
            moviesDao.deleteAll()
            moviesDao.insertMovies(roomMovies)
        }
        catch (e: Exception) {
            Log.e("Cache Exception", e.message.toString())
        }
    }

    private fun UiMovie.toRoomMovie(): RoomMovie {
        return RoomMovie(
            id = id,
            title = title,
            budget = budget,
            releaseDate = releaseDate,
            imageUrl = imageUrl,
            voteAverage = voteAverage
        )
    }

    private fun RoomMovie.toUiMovie(): UiMovie {
        return UiMovie(
            id = id,
            title = title,
            budget = budget,
            releaseDate = releaseDate,
            imageUrl = imageUrl,
            voteAverage = voteAverage
        )
    }
}