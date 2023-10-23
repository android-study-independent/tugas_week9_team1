package com.example.tugasmodule9.ui.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
//import coil.load
import com.example.tugasmodule9.R
import com.example.tugasmodule9.data.model.Movie
import com.example.tugasmodule9.databinding.ActivityMovieDetailBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.util.Date

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    val db = Firebase.firestore

    companion object {
        const val PATH_MOVIE = "movies"
        const val PATH_USER_MOVIE = "user_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val poster = intent.getStringExtra("image")
        val title = intent.getStringExtra("movie_title")
        val rating = intent.getFloatExtra("movie_rating", 0.0f)
        val popularity = intent.getFloatExtra("movie_popularity", 0f)
        val overview = intent.getStringExtra("movie_overview")
        val path = buildPosterPath(poster)

        setView(title, rating, popularity, overview, path)
        setAction(title, rating, popularity, overview, path)
    }

    private fun setView(
        title: String?,
        rating: Float?,
        popularity: Float?,
        overview: String?,
        path: String?
    ) {
        Picasso.get().load(path).into(binding.ivPoster)
        binding.tvTitle.text = title
        binding.tvRating.text = rating.toString()
        binding.tvPopularity.text = popularity.toString()
        binding.tvOverview.text = overview
    }

    private fun setAction(
        title: String?,
        rating: Float?,
        popularity: Float?,
        overview: String?,
        path: String?
    ) {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnAddFavorite.setOnClickListener {

            val currentUser = Firebase.auth.currentUser

            val movie = Movie(
                poster = path,
                title = title,
                rating = rating,
                popularity = popularity,
                overview = overview,
                createdAt = Date()
            )

            db.collection(PATH_MOVIE).document(currentUser?.uid ?: "")
                .collection(PATH_USER_MOVIE)
                .add(movie)
                .addOnSuccessListener {
                    Log.d("tag", "Sukses menyimpan ${it.toString()}")
                    Toast.makeText(this, "Menambahkan ke favorite movie", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Log.e("tag", "gagal menambahkan ${it.localizedMessage}")
                }
        }
    }

    private fun buildPosterPath(posterPath: String?): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}