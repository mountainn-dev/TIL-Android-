package com.mountain.amicomprojectmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mountain.amicomprojectmanager.databinding.ActivityProjectBinding

class ProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemSemester = intent.getStringExtra("itemSemester")
        val itemProjectName = intent.getStringExtra("itemProjectName")
        val itemContents = intent.getStringExtra("itemContents")
        val itemChatroom = intent.getStringExtra("itemChatroom")

        binding.tvSemester.text = itemSemester
        binding.tvProjectName.text = itemProjectName
        binding.tvProjectContents.text = itemContents
        binding.tvChatroom.text = itemChatroom
        binding.btnGoBack.setOnClickListener {
            finish()
        }
    }
}