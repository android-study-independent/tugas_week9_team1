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

class ItemTopRatedAdapter(private val listMovie: List<MovieResponse>): RecyclerView.Adapter<ItemTopRatedAdapter.ItemTopRatedHolder>() {
    class ItemTopRatedHolder(private val view: View): RecyclerView.ViewHolder(view) {
            val imgPoster = view.findViewById<ImageView>(R.id.imgPoster)
            val movieTitle = view.findViewById<TextView>(R.id.tvTitle)
            val movieRating = view.findViewById<TextView>(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTopRatedHolder = ItemTopRatedHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top_rated, parent, false))

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ItemTopRatedHolder, position: Int) {
        val data = listMovie[position]

        val title = data.title
        val rating = data.voteAverage
        val path = buildPosterPath(data.posterPath)

        holder.movieTitle.text = title
        holder.movieRating.text = rating.toString()
        Picasso.get().load(path).into(holder.imgPoster)

    }

    private fun buildPosterPath(posterPath: String?): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}