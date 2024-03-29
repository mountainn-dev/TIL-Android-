package com.mountain.amicomprojectmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mountain.amicomprojectmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myData: Intent
    private lateinit var project: Project
    private val database: DatabaseReference = Firebase.database.reference
    private val projectList = arrayListOf<Project>()
    private val projectKeyList = arrayListOf<String>()
    private val mAdapter = MyAdapter(this, projectList, projectKeyList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: db 에서 플젝 키리스트 불러오고 해당 리스트 인덱싱해서 value 가져오는 코드 구현
        val myListener = object : ValueEventListener {
            // TODO: onDataChange 로 액티비티 생성 단계에서 초기 데이터를 불러올 수가 있다. - by 파이어베이스 공식문서
            override fun onDataChange(snapshot: DataSnapshot) {
                val snapProjectKeyList = snapshot.child("ProjectKeyList").children

                // db 프로젝트 키 string 값 호출 및 리스트 할당
                for (i in snapProjectKeyList) {
                    projectKeyList.add(i.value.toString())
                }

                // 생성된 리스트로 매핑 실시
                for (j in 0 until projectKeyList.size) {
                    val contents = snapshot.child("ProjectList")
                        .child(projectKeyList[j]).child("contents").value
                    val chatroom = snapshot.child("ProjectList")
                        .child(projectKeyList[j]).child("chatroom").value
                    val semester = snapshot.child("ProjectList")
                        .child(projectKeyList[j]).child("semester").value
                    val projectName = snapshot.child("ProjectList")
                        .child(projectKeyList[j]).child("projectName").value

                    // 매핑 이후 프로젝트 객체 생성, 프로젝트 리스트에 할당
                    project = Project(
                        "$semester",
                        "$projectName",
                        "$contents",
                        "$chatroom"
                    )
                    projectList.add(project)
                }
                mAdapter.updateList()
                if (projectList.size == 0) binding.btnAddProjectGuider.isVisible = true
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        // TODO: info 와 info2 의 결과값도 다르고, projectKeyList 할당도 유지가 안 된다....왜?
        //  유지는 MainActivitiy() 객체를 잘못 만들어서 그렇고, info 결과값은 onDataChange 실행 순서가 달랐음
        database.addListenerForSingleValueEvent(myListener)

        // TODO: 무조건 리스트는 생성이 되는 것 같은데, db에 데이터가 없을 때 사이즈 말고 가이더를 띄우게 할 만한 다른 조건이 있을까
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
            //  result 데이터가 날아가는게 아니라, startActivity()자체가 기존 액티비티를 새롭게 여는 메서드이다.
                if (year != null) {
                    val projectData = Project("$year $semester",
                        "$projectName", "$contents", "$chatroom")
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

                            // 가장 최근에 추가된 key value 하나만을 접근할 수가 없어서 리스트를 초기화하고 그 때
                            // 그 때 다시 키 리스트 전체를 갱신하는 식으로 구현했다.
                            val snapProjectList = snapshot.children
                            for (i in snapProjectList) projectKeyList.add(i.key.toString())
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
                    // child 리스너의 경우, database의 리스너로 설정해줘야 child add 카운트가 1번으로 작동하였고
                    // ProjectList 리스너로 설정할 경우, child add 카운트가 고정되지 않고 계속해서 증가했다.
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