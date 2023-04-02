package com.khusinov.livedatademo

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.log

class MainActivityViewModel : ViewModel() {


    private lateinit var timer: CountDownTimer


    private var _seconds = MutableLiveData<Int>()
    private var finished = MutableLiveData<Boolean>()
    var timerValue = MutableLiveData<Long>()

    fun seconds(): LiveData<Int> {
        return _seconds
    }

    fun finished(): LiveData<Boolean> {
        return finished
    }


    fun startTimer() {
        Log.d("TAG", "startTimer: started")
        timer = object : CountDownTimer(timerValue.value!!, 1000) {

            override fun onTick(p0: Long) {
                val timeLeft = p0 / 1000
                _seconds.value = timeLeft.toInt()
                Log.d("TAGG", "onTick: $timeLeft")
            }

            override fun onFinish() {
                finished.value = true
                Log.d("TAgg", "onFinish: ")
            }

        }.start()

    }

    fun stopTimer() {
        timer.cancel()
    }
}