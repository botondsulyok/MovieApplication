package com.example.movieapplication.ui.movies.models

data class UiMovie(
    val id: Long?,
    val title: String?,
    val budget: Long?,
    val releaseDate: String?,
    val voteAverage: Double? = null,
    val formattedBudget: String? = null,
)
