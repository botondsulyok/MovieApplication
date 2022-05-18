package com.example.movieapplication.ui.detailedmovie

sealed class MoviesViewState

object Loading : MoviesViewState()

data class ExampleError(val error: String?) : MoviesViewState()

data class ExampleReady(val data: String) : MoviesViewState()