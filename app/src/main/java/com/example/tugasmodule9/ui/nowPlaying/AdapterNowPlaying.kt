package com.example.tugasmodule9.ui.nowPlaying

import android.content.Intent
import android.graphics.Movie
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

class AdapterNowPlaying(private var listMovie: List<MovieResponse>) :
    RecyclerView.Adapter<AdapterNowPlaying.NowPlayingHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_now_playing, parent, false)
        return NowPlayingHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) {
        val itemMovie = listMovie[position]
        holder.bindView(listMovie[position])

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(itemMovie)
        }
    }

    var onItemClick: ((MovieResponse) -> Unit)? = null

    inner class NowPlayingHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(movie: MovieResponse) {
            val ivPoster = view.findViewById<ImageView>(R.id.ivPoster)
            val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            val tvTextRating = view.findViewById<TextView>(R.id.tvTextRating)
            val tvOverview = view.findViewById<TextView>(R.id.tvOverview)

            tvTitle.text = movie.title
            tvTextRating.text = "${movie.voteAverage}"
            tvOverview.text = "${movie.overview}"

            val path = buildPosterPath(movie.posterPath)
            Picasso.get().load(path).into(ivPoster)

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