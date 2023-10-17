package com.example.tugasmodule9.ui.topRated

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tugasmodule9.R
import com.example.tugasmodule9.data.api.Network
import com.example.tugasmodule9.data.response.MovieResponse
import com.example.tugasmodule9.data.response.TopRatedResponse
import com.example.tugasmodule9.databinding.ActivityTopRatedBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopRatedBinding
    private lateinit var adapter: ItemTopRatedAdapter
    private var listMovie = mutableListOf<MovieResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopRatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(){
        binding.rvTopRated.layoutManager = GridLayoutManager(this, 3)
        adapter = ItemTopRatedAdapter(listMovie)
        binding.rvTopRated.adapter = adapter

        lifecycleScope.launch {
            val result = Network.getService(this@TopRatedActivity).getTopRated(
                page = 1
            )

            Log.d("debug", "total page -> ${result.totalPage}")

            result.results.map {
                Log.d("debug", "hasilnya -> ${it.title} - ${it.overview}")
                listMovie.add(it)
            }

            // update recyclerviewnya
            adapter.notifyDataSetChanged()
        }
    }

//    private fun showLoading(isLoading: Boolean){
//        if (isLoading){
//            binding.progressCircular.visibility = View.VISIBLE
//        } else {
//            binding.progressCircular.visibility = View.GONE
//        }
//    }
}