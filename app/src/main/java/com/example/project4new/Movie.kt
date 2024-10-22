package com.example.project4new

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("original_language")
    val language: String? = null
) {
    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}
