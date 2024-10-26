package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val main: ConstraintLayout = findViewById(R.id.main)
        var time: TextView = findViewById(R.id.time)
        val play: ImageView = findViewById(R.id.play)
        val pause: ImageView = findViewById(R.id.pause)
        val reset: ImageView = findViewById(R.id.reset)
        val handler: Handler = Handler(Looper.getMainLooper())
        var minutes = 0
        var seconds = 0
        var millseconds = 0
        var minutesDisplay = 0
        var secondsDisplay = 0
        var millsecondsDisplay = 0
        var running = true;
        var runnable: Runnable = Runnable { }
        runnable = Runnable {
            handler.postDelayed(runnable, 100)
            val updateTimeDisplay: () -> Unit = {
                var string = ""
                if(minutes<=9){
                    string += "0$minutes."
                }else{
                    string += "$minutes."
                }
                if(seconds<=9){
                    string += "0$seconds."
                }else{
                    string += "$seconds."
                }
                string += millseconds
                time.text = string
            }
            if(running){
                millseconds++
                if(millseconds==10){
                    millseconds = 0
                    seconds++
                }
                if(seconds == 60){
                    seconds = 0
                    minutes++
                }
            }
            updateTimeDisplay()
            play.setOnClickListener{
                running = true
            }
            pause.setOnClickListener{
                running = false
            }
            reset.setOnClickListener{
                minutes = 0
                seconds = 0
                millseconds = 0
                time.text = "00.00.0"
            }

        }
        handler.postDelayed(runnable, 1000)
    }
}