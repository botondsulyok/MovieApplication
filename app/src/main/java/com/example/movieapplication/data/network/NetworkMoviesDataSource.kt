package com.example.movieapplication.data.network

import com.example.movieapplication.data.Result
import com.example.movieapplication.data.ResultFailure
import com.example.movieapplication.data.ResultSuccess
import com.example.movieapplication.data.network.models.movies.NetworkMoviesMovie
import com.example.movieapplication.ui.movies.models.UiMovie
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMoviesDataSource @Inject constructor() {

    private val moviesApi: MoviesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(MoviesApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        this.moviesApi = retrofit.create(MoviesApi::class.java)
    }

    fun getMovies(): Result<List<UiMovie>, String> {
        return try {
            val response = moviesApi.getMovies().execute()
            if (response.isSuccessful) {
                val movies = response.body()?.results?.map { it.toUiMovie() } ?: listOf()
                ResultSuccess(movies)
            } else {
                ResultFailure(response.errorBody().toString())
            }
        } catch (e: Exception) {
            ResultFailure(e.message.toString())
        }
    }

    private fun NetworkMoviesMovie.toUiMovie(): UiMovie {
        val response = moviesApi.getMovie(id).execute()
        val movie = if (response.isSuccessful) response.body() else null
        return UiMovie(
            id = id,
            title = title,
            budget = movie?.budget,
            releaseDate = releaseDate
        )
    }
}