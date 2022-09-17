package com.mountain.amicomprojectmanager

import android.content.Context
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
import com.google.firebase.ktx.Firebase
import java.util.logging.Handler
import android.os.CountDownTimer as CountDownTimer1

class MyAdapter(private val context: Context, private var projectList: ArrayList<Project>) : BaseAdapter(){
    private val mAuth = Firebase.auth
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
                var removeAnim = AnimationUtils.loadAnimation(MyApplication.ApplicationContext(), R.anim.aniamtion_remove_card)

                removeAnim.setAnimationListener(object: Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                    }
                    override fun onAnimationEnd(p0: Animation?) {
                        removeProject(projectList, position)
                        updateList()
                        // TODO: 프로젝트 삭제 후 프로젝트가 없을 때 가이드버튼 재생성 코드 구현 필요
                        if(projectList.size == 0) {

                        }
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