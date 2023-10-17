package com.example.tugasmodule9.ui.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.tugasmodule9.R
import com.example.tugasmodule9.databinding.ActivityMovieDetailBinding
import com.squareup.picasso.Picasso

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setAction()
    }

    private fun setView(){
        val poster = intent.getStringExtra("image")
        val title = intent.getStringExtra("movie_title")
        val rating = intent.getFloatExtra("movie_rating", 0.0f)
        val popularity = intent.getFloatExtra("movie_pupularity", 0f)
        val overview = intent.getStringExtra("movie_overview")
        val path = buildPosterPath(poster)

        Picasso.get().load(path).into(binding.ivPoster)
        binding.tvTitle.text = title
        binding.tvRating.text = rating.toString()
        binding.tvPopularity.text = popularity.toString()
        binding.tvOverview.text = overview
    }

    private fun setAction(){
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun buildPosterPath(posterPath: String?): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}