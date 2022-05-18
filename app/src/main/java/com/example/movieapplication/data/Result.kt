package com.example.movieapplication.data

sealed class Result<out Success, out Failure>

data class ResultSuccess<out Success>(val value: Success) : Result<Success, Nothing>()

data class ResultFailure<out Failure>(val reason: Failure) : Result<Nothing, Failure>()