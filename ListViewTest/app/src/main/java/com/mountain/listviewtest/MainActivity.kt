package com.mountain.listviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.view.LayoutInflater
import com.mountain.listviewtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val movieList = arrayListOf<Movie>(
        Movie(R.drawable.movie1),
        Movie(R.drawable.movie2),
        Movie(R.drawable.movie1),
        Movie(R.drawable.movie2),
        Movie(R.drawable.movie1),
        Movie(R.drawable.movie2),
        Movie(R.drawable.movie1),
        Movie(R.drawable.movie2),
        Movie(R.drawable.movie1),
        Movie(R.drawable.movie2),
        Movie(R.drawable.movie1),
        Movie(R.drawable.movie2)
    )
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lvMovie.adapter = MyAdapter(this, movieList)
    }
}