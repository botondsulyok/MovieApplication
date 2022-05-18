package com.example.movieapplication.ui.movies

import com.example.movieapplication.ui.movies.models.UiMovie

sealed class MoviesViewState

object Loading : MoviesViewState()

data class Error(val errorMessage: String?) : MoviesViewState()

data class MoviesLoaded(val movies: List<UiMovie>) : MoviesViewState()