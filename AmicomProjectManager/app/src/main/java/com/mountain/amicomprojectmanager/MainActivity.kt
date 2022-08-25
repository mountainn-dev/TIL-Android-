package com.mountain.amicomprojectmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import com.mountain.amicomprojectmanager.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myData: Intent
    private val projectList = arrayListOf<Project>()
    private val mAdapter = MyAdapter(this, projectList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 재적용은 최적화에 좋지 않기 때문에 onCreate에서 어댑터 적용을 한 번만 해준다.
        binding.lvProject.adapter = mAdapter

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result -> if(result.resultCode == Activity.RESULT_OK) {
                myData = result.data ?: return@registerForActivityResult
                val year = myData.getStringExtra("year")
                val semester = myData.getStringExtra("semester")
                val projectName = myData.getStringExtra("projectName")
                val contents = myData.getStringExtra("contents")
                val chatroom = myData.getStringExtra("chatroom")
                projectList.add(projectList.size, Project("${year} ${semester}",
                    "$projectName", "$contents", "$chatroom"))
                mAdapter.updateList()
            }
        }
        binding.btnAddProject.setOnClickListener {
            val intent = Intent(this, AddProjectActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.lvProject.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ProjectActivity::class.java)
            intent.putExtra("itemSemester", projectList[position].semester)
            intent.putExtra("itemProjectName", projectList[position].projectName)
            intent.putExtra("itemContents", projectList[position].contents)
            intent.putExtra("itemChatroom", projectList[position].chatroom)
            startActivity(intent)
        }
    }
}