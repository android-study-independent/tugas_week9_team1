package com.example.tugasmodule9.ui.topRated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmodule9.data.response.MovieResponse
import com.example.tugasmodule9.R
import com.squareup.picasso.Picasso

class ItemTopRatedAdapter(private val listMovie: List<MovieResponse>) :
    RecyclerView.Adapter<ItemTopRatedAdapter.ItemTopRatedHolder>() {
    inner class ItemTopRatedHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(movie: MovieResponse){
            val imgPoster = view.findViewById<ImageView>(R.id.imgPoster)
            val movieTitle = view.findViewById<TextView>(R.id.tvTitle)
            val movieRating = view.findViewById<TextView>(R.id.tvRating)

            movieTitle.text = movie.title
            movieRating.text = movie.voteAverage.toString()
            val path = buildPosterPath(movie.posterPath)
            Picasso.get().load(path).into(imgPoster)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTopRatedHolder =
        ItemTopRatedHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_rated, parent, false)
        )

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ItemTopRatedHolder, position: Int) = holder.bindView(listMovie[position])

    private fun buildPosterPath(posterPath: String?): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}