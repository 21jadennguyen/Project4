package com.example.project4new

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieRecyclerViewAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieItem: Movie? = null
        var titleTextView: TextView = itemView.findViewById(R.id.movieTitle)
        var posterImageView: ImageView = itemView.findViewById(R.id.moviePoster)
    }



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieItem = movie
        holder.titleTextView.text = movie.title
        Glide.with(holder.itemView.context)
            .load(movie.getPosterUrl())
            .apply(RequestOptions()
                .placeholder(R.drawable.smiley))
            .transform(RoundedCorners(32))
            .into(holder.posterImageView)


        // Example usage: Logging the new language property
        Log.d("MovieRecyclerViewAdapter", "Language: ${movie.language}")

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java)
            intent.putExtra("title", movie.title)
            intent.putExtra("poster_path", movie.posterPath)
            intent.putExtra("overview", movie.overview)
            intent.putExtra("popularity", movie.popularity) // Optional: Pass popularity
            intent.putExtra("release_date", movie.releaseDate) // Optional: Pass release date
            intent.putExtra("language", movie.language) // Optional: Pass language
            holder.itemView.context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int = movies.size
}
