package com.example.tugasmodule9.ui.upComing

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
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
    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_coming)

        val rvupcomming = findViewById<RecyclerView>(R.id.rvTopRated)
        val dates = findViewById<TextView>(R.id.dates)

        rvupcomming.layoutManager = LinearLayoutManager(this)
        adapter = adapterUpComming(listMovie)

        rvupcomming.adapter = adapter
        lifecycleScope.launch {
            val result = Network.getService(this@UpComingActivity).getUpcoming(
                page = 1, lang = "en-EN"
            )
            dates.text = "${result.dates?.minimum} - ${result.dates?.maximum}"
            result.results.map {
                Log.d("debug", "No. ${it.id} : ${it.title} : ${it.backdropPath}")
                listMovie.add(it)
            }
            // Update the RecyclerViewnya
            adapter.notifyDataSetChanged()
        }

    }
}