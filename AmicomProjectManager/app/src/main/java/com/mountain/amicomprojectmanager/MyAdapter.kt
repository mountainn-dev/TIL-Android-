package com.mountain.amicomprojectmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyAdapter(private val context: Context, private val projectList: ArrayList<Project>) : BaseAdapter() {
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
        val view: View = LayoutInflater.from(context).inflate(R.layout.item, null)

        val semester = view.findViewById<TextView>(R.id.tvSemester)
        val projectName = view.findViewById<TextView>(R.id.tvProjectName)
        val contents = view.findViewById<TextView>(R.id.tvContents)
        val deleteProject = view.findViewById<TextView>(R.id.btnDelete)

        // arrayList의 순번을 돌려가면서 데이터를 view에 할당해준다.
        semester.text = projectList[position].semester
        projectName.text = projectList[position].projectName
        contents.text = projectList[position].contents

        return view
    }

    fun updateList() {   // 리스트 데이터 갱신 메서드 구현
        this.notifyDataSetChanged()
    }
}