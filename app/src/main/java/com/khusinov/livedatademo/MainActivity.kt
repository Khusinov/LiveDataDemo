package com.khusinov.livedatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.khusinov.livedatademo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]


        viewModel.seconds().observe(this, Observer {
            binding.tv.text = it.toString()
            Log.d("TAGG", "onCreate:${it.toString()} ")
        })

        viewModel.finished().observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show()

            }
        })

        binding.btnStart.setOnClickListener {
            viewModel.timerValue.value =  binding.edt.text.toString().toLong()
            viewModel.startTimer()

        }


        binding.btnStop.setOnClickListener {
            viewModel.stopTimer()
            binding.tv.text = "0"
        }



    }
}