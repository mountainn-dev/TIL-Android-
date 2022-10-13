package com.mountain.amicomprojectmanager

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.logging.Handler
import android.os.CountDownTimer as CountDownTimer1

class MyAdapter(
    private val context: Context,
    private val projectList: ArrayList<Project>,
    private val projectKeyList: ArrayList<String>,
    ) : BaseAdapter(){

    private val mAuth = Firebase.auth
    private val database: DatabaseReference = Firebase.database.reference

    override fun getCount(): Int {
        return projectList.size
    }

    override fun getItem(position: Int): Any {
        return projectList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item, null)

        val semester = view.findViewById<TextView>(R.id.tvSemester)
        val projectName = view.findViewById<TextView>(R.id.tvProjectName)
        val contents = view.findViewById<TextView>(R.id.tvContents)
        val deleteProject = view.findViewById<Button>(R.id.btnDelete)

        // arrayList의 순번을 돌려가면서 데이터를 view에 할당해준다.
        semester.text = projectList[position].semester
        projectName.text = projectList[position].projectName
        contents.text = projectList[position].contents

        deleteProject.setOnClickListener {
            if (mAuth.currentUser!!.uid != "UdpZZMIA7mhbg5bX5R9eYAXFzpn2") {
                Toast.makeText(MyApplication.ApplicationContext(),
                    "프로젝트 삭제는 관리자 계정으로만 가능합니다. 동아리에 문의해주세요.",
                    Toast.LENGTH_SHORT).show()
            } else {
                val removeAnim = AnimationUtils.loadAnimation(MyApplication.ApplicationContext(), R.anim.aniamtion_remove_card)

                // 애니메이션이 끝나는 동시에 프로젝트 리스트를 갱신하기 위해 onAnimationEnd 리스너를 설정한다.
                removeAnim.setAnimationListener(object: Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                    }
                    override fun onAnimationEnd(p0: Animation?) {
                        removeProject(projectList, position)
                        updateList()
                        database.child("ProjectList").child(projectKeyList[position]).removeValue()
                        database.child("ProjectKeyList").child("$position").removeValue()
                        // TODO: 애니메이션 종료 후 해당 position을 key 리스트 인덱스로 사용하여 db의 데이터 삭제 기능 구현 필요
                        // TODO: 프로젝트 삭제 후 프로젝트가 없을 때 가이드버튼 재생성 코드 구현 필요
                    }
                    override fun onAnimationRepeat(p0: Animation?) {
                    }
                })
                view.startAnimation(removeAnim)
//            // TODO: 애니메이션 시작으로부터 일정시간 이후 진행될 코드를 Handler.postDelayed()메서드로 작성
//            val a = android.os.Handler()
//            a.postDelayed(}, 500)
            }
        }
        return view
    }

    fun updateList() {   // 리스트 데이터 갱신 메서드 구현
        this.notifyDataSetChanged()
    }

    private fun removeProject(arr: ArrayList<Project>, index: Int) : ArrayList<Project> {
        arr.removeAt(index)
        return arr
    }
}