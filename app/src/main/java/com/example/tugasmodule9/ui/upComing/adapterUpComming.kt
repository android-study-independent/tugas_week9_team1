package com.example.tugasmodule9.ui.upComing

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmodule9.R
import com.example.tugasmodule9.data.response.MovieResponse
import com.example.tugasmodule9.ui.movieDetail.MovieDetailActivity
import com.squareup.picasso.Picasso

class adapterUpComming(private val listMovie: List<MovieResponse>):
    RecyclerView.Adapter<adapterUpComming.UpCommingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpCommingHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_up_comming, parent, false)
        return UpCommingHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: UpCommingHolder, position: Int) {
        holder.bindView(listMovie[position])
    }

    inner class UpCommingHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(movie: MovieResponse) {
            val imgPoster = view.findViewById<ImageView>(R.id.imgPoster)
            val tvTitle = view.findViewById<TextView>(R.id.tvTittle)
            val tvRating =view.findViewById<TextView>(R.id.tvRating)
            val tvOverview =view.findViewById<TextView>(R.id.tvOverview)

            tvTitle.text = movie.title
            tvRating.text = "${movie.voteAverage}"
            tvOverview.text ="${movie.overview}"


            val path = buildPosterPath(movie.posterPath)
            // load image from url into imageview
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

        private fun buildPosterPath(posterPath: String?): String {
            return "https://image.tmdb.org/t/p/w500/$posterPath"
        }
    }

}