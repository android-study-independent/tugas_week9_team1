package com.example.tugasmodule9.ui.nowPlaying

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmodule9.R
import com.example.tugasmodule9.data.api.Network
import com.example.tugasmodule9.data.response.MovieResponse
import kotlinx.coroutines.launch

class NowPlayingActivity : AppCompatActivity() {

    private lateinit var adapter: AdapterNowPlaying
    private var listNowPlaying = mutableListOf<MovieResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_playing)

        val rvNowPlaying = findViewById<RecyclerView>(R.id.rvNowPlaying)
        rvNowPlaying.layoutManager = LinearLayoutManager(this)

        adapter = AdapterNowPlaying(listNowPlaying)
        rvNowPlaying.adapter = adapter

        lifecycleScope.launch {

            val result = Network.getService(this@NowPlayingActivity).getNowPlaying(
                page = 1
            )

            Log.d("debug", "total page -> ${result.totalPages}")


            result.results.map {
                Log.d("debug", "hasilnya -> ${it.title} - ${it.overview}")
                listNowPlaying.add(it)
            }

            // update recyclerviewnya lagi
            adapter.notifyDataSetChanged()
        }
    }
}
