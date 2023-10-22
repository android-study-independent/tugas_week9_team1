package com.example.tugasmodule9.ui.topRated

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmodule9.data.response.MovieResponse
import com.example.tugasmodule9.R
import com.example.tugasmodule9.ui.movieDetail.MovieDetailActivity
import com.squareup.picasso.Picasso

class adapterPopular(private val listMovie: List<MovieResponse>) :
    RecyclerView.Adapter<adapterPopular.PopularHolder>() {
    inner class PopularHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(movie: MovieResponse){
            val imgPoster = view.findViewById<ImageView>(R.id.imgPoster)
            val movieTitle = view.findViewById<TextView>(R.id.tvTitle)
            val movieRating = view.findViewById<TextView>(R.id.tvRating)

            movieTitle.text = movie.title
            movieRating.text = movie.voteAverage.toString()
            val path = buildPosterPath(movie.posterPath)
            Picasso.get().load(path).into(imgPoster)

            itemView.setOnClickListener{
                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                intent.putExtra("image", movie.posterPath)
                intent.putExtra("movie_title", movie.title)
                intent.putExtra("movie_rating", movie.voteAverage)
                intent.putExtra("movie_popularity", movie.popularity)
                intent.putExtra("movie_overview", movie.overview)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder =
        PopularHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_rated, parent, false)
        )

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: PopularHolder, position: Int) = holder.bindView(listMovie[position])

    private fun buildPosterPath(posterPath: String?): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
        }
}