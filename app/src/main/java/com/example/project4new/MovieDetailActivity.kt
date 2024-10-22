package com.example.project4new

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val posterImageView: ImageView = findViewById(R.id.detailMoviePoster)
        val titleTextView: TextView = findViewById(R.id.detailMovieTitle)
        val overviewTextView: TextView = findViewById(R.id.detailMovieOverview)
        val popularityTextView: TextView = findViewById(R.id.detailMoviePopularity)
        val releaseDateTextView: TextView = findViewById(R.id.detailMovieReleaseDate)
        val languageTextView: TextView = findViewById(R.id.detailMovieLanguage)

        val title: String? = intent.getStringExtra("title")
        val posterPath: String? = intent.getStringExtra("poster_path")
        val overview: String? = intent.getStringExtra("overview")
        val popularity: Double = intent.getDoubleExtra("popularity", 0.0)
        val releaseDate: String? = intent.getStringExtra("release_date")
        val language: String? = intent.getStringExtra("language")

        titleTextView.text = title
        overviewTextView.text = overview
        popularityTextView.text = "Popularity: $popularity"
        releaseDateTextView.text = "Release Date: $releaseDate"
        languageTextView.text = "Language: $language"

        posterPath?.let {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/$it")
                .into(posterImageView)
        }
    }
}
