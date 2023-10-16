package com.example.tugasmodule9.ui.upComing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmodule9.R
import com.example.tugasmodule9.data.api.Network
import com.example.tugasmodule9.data.response.MovieResponse
import kotlinx.coroutines.launch

class UpComingActivity : AppCompatActivity() {
    private lateinit var adapter: adapterUpComming
    private var listMovie = mutableListOf<MovieResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_coming)

        val rvTopRated = findViewById<RecyclerView>(R.id.rvTopRated) // Correct the view type
        rvTopRated.layoutManager = LinearLayoutManager(this)

        adapter = adapterUpComming(listMovie)
        rvTopRated.adapter = adapter

        lifecycleScope.launch {
            val result = Network.getService(this@UpComingActivity).getTopRated(
                page = 1, lang = "en-EN"
            )
            Log.d("debug", "total page -> ${result.totalPage}")
            result.results.map {
                Log.d("debug", "hasilnya-> ${it.title} - ${it.overview}")
                listMovie.add(it)
            }
            // Update the RecyclerViewnya
            adapter.notifyDataSetChanged()
        }
        
    }
}