package com.example.tugasmodule9.ui.upComing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.tugasmodule9.R
import com.example.tugasmodule9.data.api.Network
import kotlinx.coroutines.launch

class UpComingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_coming)

        lifecycleScope.launch {

            val result = Network.getService(this@UpComingActivity).getUpcoming(
                page = 1
            )

            Log.d("debug", "total page -> ${result.totalPage}")


            result.results.map {
                Log.d("debug", "hasilnya -> ${it.title} - ${it.overview}")
            }

        }
        
    }
}