package com.example.tugasmodule9.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugasmodule9.databinding.ActivityMainBinding
import com.example.tugasmodule9.ui.nowPlaying.NowPlayingActivity
import com.example.tugasmodule9.ui.popular.PopularActivity
import com.example.tugasmodule9.ui.topRated.TopRatedActivity
import com.example.tugasmodule9.ui.upComing.UpComingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setAction(){
        binding.btnTopRated.setOnClickListener {
            val intent = Intent(this, TopRatedActivity::class.java)
            startActivity(intent)
        }
        binding.btnNowPlaying.setOnClickListener {
            val intent = Intent(this, NowPlayingActivity::class.java)
            startActivity(intent)
        }
        binding.btnPopular.setOnClickListener {
            val intent = Intent(this, PopularActivity::class.java)
            startActivity(intent)
        }
        binding.btnUpComing.setOnClickListener {
            val intent = Intent(this, UpComingActivity::class.java)
            startActivity(intent)
        }
    }
}