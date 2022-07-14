package com.example.kotlin_self_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTest = findViewById<TextView>(R.id.tv_test1)
        val btnClickMe = findViewById<Button>(R.id.btn_mybutton)
        var timesClicked = 0
        btnClickMe.setOnClickListener {
            timesClicked += 1
            tvTest.text = timesClicked.toString()

        }
    }
}