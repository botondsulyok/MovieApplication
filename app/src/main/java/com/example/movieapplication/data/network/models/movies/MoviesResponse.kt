package com.example.movieapplication.data.network.models.movies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    val page: Long? = null,
    val results: List<NetworkMoviesMovie> = listOf(),
    @Json(name = "total_pages") val totalPages: Long? = null,
    @Json(name = "total_results") val totalResults: Long? = null
)