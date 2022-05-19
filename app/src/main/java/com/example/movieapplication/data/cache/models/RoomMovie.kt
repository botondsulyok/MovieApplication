package com.example.movieapplication.data.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class RoomMovie(
    @PrimaryKey(autoGenerate = false) val id: Long?,
    val title: String?,
    val budget: Long?,
    val releaseDate: String?,
    val voteAverage: Double? = null,
)