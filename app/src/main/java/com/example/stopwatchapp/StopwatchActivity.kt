package com.example.stopwatchapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StopwatchActivity : AppCompatActivity() {

    private var segundos: Int = 0
    private var execucao: Boolean = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContentView(R.layout.activity_stopwatch)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        runTimer()
    }

    fun onClickStart(view: View) {
        execucao = true
    }

    fun onClickStop(view: View) {
        execucao = false
    }

    fun onClickReset(view: View) {
        execucao = false
        segundos = 0
    }

    fun runTimer() {
        val timeView: TextView = findViewById(R.id.txtClock)
        val runnable = object : Runnable {
            override fun run() {
                val hours = segundos / 3600
                val minutes = (segundos % 3600) / 60
                val secs = segundos % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (execucao) {
                    segundos++
                }
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }
}