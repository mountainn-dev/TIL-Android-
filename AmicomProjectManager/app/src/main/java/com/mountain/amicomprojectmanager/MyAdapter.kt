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
import androidx.cardview.widget.CardView

class MyAdapter(private val context: Context, private var projectList: ArrayList<Project>) : BaseAdapter() {
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
        val card = view.findViewById<CardView>(R.id.cv)

        // arrayList의 순번을 돌려가면서 데이터를 view에 할당해준다.
        semester.text = projectList[position].semester
        projectName.text = projectList[position].projectName
        contents.text = projectList[position].contents
        deleteProject.setOnClickListener {
            val removeAnim = AnimationUtils.loadAnimation(MyApplication.ApplicationContext(), R.anim.aniamtion_remove_card)
            view.animation = removeAnim
            Toast.makeText(MyApplication.ApplicationContext(), "Toast: $removeAnim", Toast.LENGTH_SHORT).show()
//            removeProject(projectList, position)
//            updateList()
        }

        return view
    }

    fun updateList() {   // 리스트 데이터 갱신 메서드 구현
        this.notifyDataSetChanged()
    }

    fun removeProject(arr: ArrayList<Project>, index: Int) : ArrayList<Project> {
        arr.removeAt(index)
        return arr
    }
}