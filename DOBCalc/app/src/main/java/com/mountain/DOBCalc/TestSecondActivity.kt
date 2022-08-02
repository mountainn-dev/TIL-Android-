package com.mountain.DOBCalc

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.mountain.DOBCalc.databinding.ActivityTestSecondBinding
import java.text.SimpleDateFormat
import java.util.*

class TestActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityTestSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectDate.setOnClickListener { clickBtnSelect() }
    }

    private fun clickBtnSelect() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener {
                    _,
                    selectedYear,
                    selectedMonthOfYear,
                    selectedDayOfMonth ->
                binding.tvSelectedDate.text = "$selectedYear/${selectedMonthOfYear+1}/$selectedDayOfMonth"
                val sdf = SimpleDateFormat("yy.MM.dd")
                val currentDate = "$year.${month+1}.$dayOfMonth"
                val selectedDate = "$selectedYear.${selectedMonthOfYear+1}.$selectedDayOfMonth"
                val currentDateInMinutes = sdf.parse(currentDate).time / 60000
                val selectedDateInMinutes = sdf.parse(selectedDate).time / 60000
                binding.tvDateInMinute.text = (currentDateInMinutes - selectedDateInMinutes).toString()
            },
            year,
            month,
            dayOfMonth)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}