package com.mountain.amicomprojectmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mountain.amicomprojectmanager.databinding.ActivityProjectBinding

class ProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val cardSemester = intent.getStringExtra("cardSemester")
//        binding.tvSemester.text = cardSemester
    }
}