package com.mountain.a7minutesexcerciseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.mountain.a7minutesexcerciseapp.databinding.ActivityExerciseBinding
import kotlin.concurrent.timer

private var _binding: ActivityExerciseBinding? = null
private val binding: ActivityExerciseBinding
    get () = _binding!!

class ExerciseActivity : AppCompatActivity() {
    private var second = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarExercise)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 아이콘 화면에 표시
        }
        binding.toolbarExercise.setNavigationOnClickListener { onBackPressed() }


        timer(period = 1000, initialDelay = 1000) {
            if (second > 0) second-- else return@timer

        }
    }
}
