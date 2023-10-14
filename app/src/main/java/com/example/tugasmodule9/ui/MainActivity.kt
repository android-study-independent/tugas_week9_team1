package com.example.tugasmodule9.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.tugasmodule9.R
import com.example.tugasmodule9.data.api.Network
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
           val result = Network.getService().getNowPlaying(
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0OTQxNzYxM2NiY2NjZTcyODBjZWZkOTRjOGQ1YWE0NSIsInN1YiI6IjY1MjRkMDA0ZWE4NGM3MDBhZWVmYjI4OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.i_oTihrSOXm692mbAhsV4M069WoCOslCxlB6IXO85gw",
                lang = "id-ID",
                page = 1,
            )

            result.results.map {
                Log.d("debug","hasilnya -> ${it.title}")
            }
        }


    }
}