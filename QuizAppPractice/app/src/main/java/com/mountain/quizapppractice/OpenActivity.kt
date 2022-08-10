package com.mountain.quizapppractice

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.mountain.quizapppractice.databinding.ActivityOpenBinding

class OpenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOpenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        binding.imgLogo.animation = topAnim
        binding.tvLogo.animation = bottomAnim
        binding.btnMainStart.visibility = View.VISIBLE
        binding.btnMainStart.setBackgroundColor(Color.TRANSPARENT)
        binding.btnMainStart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}