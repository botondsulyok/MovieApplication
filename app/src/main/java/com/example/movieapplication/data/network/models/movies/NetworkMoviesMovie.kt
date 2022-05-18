package com.example.movieapplication.data.network.models.movies

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkMoviesMovie(
    val adult: Boolean? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "genre_ids") val genreIDS: List<Long>? = null,
    val id: Long? = null,
    @Json(name = "original_language") val originalLanguage: OriginalLanguage? = null,
    @Json(name = "original_title") val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @Json(name = "vote_average") val voteAverage: Double? = null,
    @Json(name = "vote_count") val voteCount: Long? = null
)

@JsonClass(generateAdapter = true)
enum class OriginalLanguage(val value: String) {
    En("en"),
    Es("es"),
    Fr("fr");

    companion object {
        public fun fromValue(value: String): OriginalLanguage = when (value) {
            "en" -> En
            "es" -> Es
            "fr" -> Fr
            else -> throw IllegalArgumentException()
        }
    }
}
