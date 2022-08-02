package com.mountain.DOBCalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class TestActivity : AppCompatActivity() {
    private lateinit var tvTime: TextView
    private lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDate: Button = findViewById(R.id.btnDate)
        tvTime = findViewById(R.id.tvTime)
        tvDate = findViewById(R.id.tvDate)

        btnDate.setOnClickListener {
            datePick()
        }
    }

    fun datePick() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay
            ->  Toast.makeText(this, "${selectedYear}/${selectedMonth+1}/${selectedDay}", Toast.LENGTH_LONG).show()
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val currentDate = sdf.parse("${dayOfMonth}/${month+1}/${year}")
            tvDate.text = currentDate.toString()

            val selectedDate = sdf.parse("${selectedDay}/${selectedMonth+1}/${selectedYear}")
            val currentInMinutes = currentDate.time / 60000
            val selectInMinutes = selectedDate.time / 60000
            val differenceInMinutes = currentInMinutes - selectInMinutes
            tvTime.text = differenceInMinutes.toString()}
            , year, month, dayOfMonth)
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}