package com.mountain.amicomprojectmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mountain.amicomprojectmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myData: Intent
    private val database: DatabaseReference = Firebase.database.reference
    private var projectList = arrayListOf<Project>()
    private var projectKeyList = arrayListOf<String>()
    private val mAdapter = MyAdapter(this, projectList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val myListener = object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val b = snapshot.child("ProjectList").children
//                for (i in b){
//                    Log.i("info", "${i.key}")
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        }
//        database.addValueEventListener(myListener)
        if (projectList.size == 0) binding.btnAddProjectGuider.isVisible = true
        // 재적용은 최적화에 좋지 않기 때문에 onCreate 에서 어댑터 적용을 한 번만 해준다.
        binding.lvProject.adapter = mAdapter
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result -> if(result.resultCode == Activity.RESULT_OK) {
                myData = result.data ?: return@registerForActivityResult
                val year = myData.getStringExtra("year")
                val semester = myData.getStringExtra("semester")
                val projectName = myData.getStringExtra("projectName")
                val contents = myData.getStringExtra("contents")
                val chatroom = myData.getStringExtra("chatroom")
            // TODO: 단순 startActivity()를 할 경우, result 데이터가 날아가기 때문에 goBack 코드와 맞춰주었다.
            //  result데이터가 날아가는게 아니라, startActivity()자체가 기존 액티비티를 새롭게 여는 메서드이다.
                if (year != null) {
                    val projectData = Project("$year $semester",
                        "$projectName", "$contents", "$chatroom")
//                    val addeventListener = object : ValueEventListener {
//                        override fun onDataChange(snapshot: DataSnapshot) {
//                            uidList.clear()
//                            for (ds: DataSnapshot in snapshot.children) {
//                                val uidKey = ds.key
//                                uidList.add(uidKey ?: return)
//                            }
//                        }
//
//                        override fun onCancelled(error: DatabaseError) {
//                        }
//                    }
                    projectList.add(projectList.size, projectData)
                    mAdapter.updateList()
                    // 리스트 전체를 덮어씌우지 않고, 개별 프로젝트 1개를 추가해준다.
                    database.child("ProjectList").push().setValue(projectData)
                    val dbListener = object : ChildEventListener {
                        override fun onChildAdded(
                            snapshot: DataSnapshot,
                            previousChildName: String?,
                        ) {
                            projectKeyList.clear()

                            val a = snapshot.children
                            for (snapshot in a) projectKeyList.add(snapshot.key.toString())
                            database.child("ProjectKeyList").setValue(projectKeyList)

                        }

                        override fun onChildChanged(
                            snapshot: DataSnapshot,
                            previousChildName: String?,
                        ) {

                        }

                        override fun onChildRemoved(snapshot: DataSnapshot) {
                            TODO("Not yet implemented")
                        }

                        override fun onChildMoved(
                            snapshot: DataSnapshot,
                            previousChildName: String?,
                        ) {
                            TODO("Not yet implemented")
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    }
                    // TODO: child가 아닌 database의 Listener로 설정했더니 됐음! -> 오버헤드 가능성이 있다.
                    database.addChildEventListener(dbListener)
                }
                if (projectList.size != 0) binding.btnAddProjectGuider.isVisible = false
            }
        }
        binding.btnAddProject.setOnClickListener {
            val intent = Intent(this, AddProjectActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnAddProjectGuider.setOnClickListener {
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