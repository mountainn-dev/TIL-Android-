package com.mountain.listviewpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val context: Context, private val dogList: ArrayList<Dog>) : BaseAdapter() {
    override fun getCount(): Int {
        return dogList.size
    }

    override fun getItem(position: Int): Any {
        return dogList[position]
    }

    override fun getItemId(position: Int): Long {   // TODO: 어디에 쓰이는지 확인 필요
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // item xml 파일의 view들을 context에 입력될 activity와 연결시켜준다.
        val view: View = LayoutInflater.from(context).inflate(R.layout.item, null)

        val image = view.findViewById<ImageView>(R.id.imgDog)
        val name = view.findViewById<TextView>(R.id.nameDog)
        val type = view.findViewById<TextView>(R.id.typeDog)
        val feature = view.findViewById<TextView>(R.id.featureDog)

        // arrayList의 순번을 돌려가면서 데이터를 view에 할당해준다.
        image.setImageResource(dogList[position].image)
        name.text = dogList[position].name
        type.text = dogList[position].type
        feature.text = dogList[position].features

        return view
    }

}