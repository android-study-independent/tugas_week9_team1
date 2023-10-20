package com.example.tugasmodule9.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugasmodule9.databinding.ActivityMainBinding
import com.example.tugasmodule9.ui.login.LoginActivity
import com.example.tugasmodule9.ui.nowPlaying.NowPlayingActivity
import com.example.tugasmodule9.ui.populer.PopulerActivity
import com.example.tugasmodule9.ui.topRated.TopRatedActivity
import com.example.tugasmodule9.ui.upComing.UpComingActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogout.setOnClickListener{
            auth.signOut()
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        setAction()
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
            val intent = Intent(this, PopulerActivity::class.java)
            startActivity(intent)
        }
        binding.btnUpComing.setOnClickListener {
            val intent = Intent(this, UpComingActivity::class.java)
            startActivity(intent)
        }
    }
}