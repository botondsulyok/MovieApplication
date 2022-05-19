package com.example.movieapplication.data.cache

import androidx.room.*
import com.example.movieapplication.data.cache.models.RoomMovie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): List<RoomMovie>

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Insert
    fun insertMovies(movies: List<RoomMovie>)

    @Delete
    fun deleteMovies(movies: List<RoomMovie>)

    @Update
    fun updateMovies(movies: List<RoomMovie>)

    @Query("SELECT * FROM movie WHERE title like '%' || :t || '%'")
    fun searchByTitle(t: String): List<RoomMovie>

}