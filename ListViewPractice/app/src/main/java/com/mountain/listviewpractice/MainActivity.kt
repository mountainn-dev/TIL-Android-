package com.mountain.listviewpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.mountain.listviewpractice.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val dogList = arrayListOf<Dog>(
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        Dog("A", "B", R.mipmap.ic_launcher, "Good"),
        )
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemLayout = findViewById<LinearLayout>(R.id.llItem)
        itemLayout.setOnClickListener {

        }
        binding.lvDog.adapter = MyAdapter(this, dogList)
    }
}