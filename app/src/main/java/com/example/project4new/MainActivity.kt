package com.example.project4new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var topRatedRecyclerView: RecyclerView
    private lateinit var popularMoviesRecyclerView: RecyclerView
    private val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topRatedRecyclerView = findViewById(R.id.topRatedRecyclerView)
        popularMoviesRecyclerView = findViewById(R.id.popularMoviesRecyclerView)

        topRatedRecyclerView.layoutManager = LinearLayoutManager(this)
        popularMoviesRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchTopRatedMovies()
        fetchPopularMovies()
    }

    private fun fetchTopRatedMovies() {
        val client = AsyncHttpClient()
        client["https://api.themoviedb.org/3/movie/top_rated?api_key=$API_KEY", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val resultsJSON = json.jsonObject.getJSONArray("results")
                val moviesRawJSON = resultsJSON.toString()

                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                val movies: List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType)

                topRatedRecyclerView.adapter = MovieRecyclerViewAdapter(movies)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?) {
                // Handle failure
            }
        }]
    }

    private fun fetchPopularMovies() {
        val client = AsyncHttpClient()
        client["https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val resultsJSON = json.jsonObject.getJSONArray("results")
                val moviesRawJSON = resultsJSON.toString()

                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                val movies: List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType)

                popularMoviesRecyclerView.adapter = MovieRecyclerViewAdapter(movies)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?) {
                // Handle failure
            }
        }]
    }
}
