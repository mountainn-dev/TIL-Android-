package com.mountain.amicomprojectmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.mountain.amicomprojectmanager.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val projectList = arrayListOf<Project>(
        Project("2022 1학기", "프로젝트1", "프로젝트 소개내용입니다. 간단하게 프로젝트 내용을 적어도 되고 계획을 적어도 되고 아무거나 적어도 돼요."),
        Project("2022 2학기", "프로젝트2", "프로젝트 소개내용입니다. 간단하게 프로젝트 내용을 적어도 되고 계획을 적어도 되고 아무거나 적어도 돼요."),
        Project("2023 1학기", "프로젝트3", "프로젝트 소개내용입니다. 간단하게 프로젝트 내용을 적어도 되고 계획을 적어도 되고 아무거나 적어도 돼요."),
        Project("2023 2학기", "프로젝트4", "프로젝트 소개내용입니다. 간단하게 프로젝트 내용을 적어도 되고 계획을 적어도 되고 아무거나 적어도 돼요."),
        Project("2022 1학기", "프로젝트5", "프로젝트 소개내용입니다. 간단하게 프로젝트 내용을 적어도 되고 계획을 적어도 되고 아무거나 적어도 돼요."),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val semester = intent.getStringExtra("semester").toString()
        val projectName = intent.getStringExtra("projectName").toString()
        val contents = intent.getStringExtra("contents").toString()
        val chatroom = intent.getStringExtra("chatroom").toString()
        projectList.add(projectList.size, Project("$semester", "$projectName", "$contents"))
        binding.btnAddProject.setOnClickListener {
            val intent = Intent(this, AddProjectActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.lvProject.adapter = MyAdapter(this, projectList)
//        val card = findViewById<CardView>(R.id.cv)   // TODO: 카드뷰 선택 후 컨텐츠 소개 구현 필요
//        val cardSemester = findViewById<TextView>(R.id.tvCardSemester)
//        card.setOnClickListener {
//            val intent2 = Intent(this, ProjectActivity::class.java)
//            intent2.putExtra("cardSemester", cardSemester.text)
//        }
    }
}