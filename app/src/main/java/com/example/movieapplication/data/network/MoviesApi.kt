package com.example.movieapplication.data.network

import com.example.movieapplication.data.network.models.movie.MovieResponse
import com.example.movieapplication.data.network.models.movies.MoviesResponse
import com.example.movieapplication.utils.Credentials
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    @GET("3/movie/popular/")
    fun getMovies(@Query("api_key") apyKey: String = Credentials.API_KEY): Call<MoviesResponse>

    @GET("3/movie/")
    fun getMovie(@Path("id") id: Long?, @Query("api_key") apyKey: String = Credentials.API_KEY): Call<MovieResponse>
}