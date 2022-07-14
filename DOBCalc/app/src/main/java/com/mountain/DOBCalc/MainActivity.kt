package com.mountain.DOBCalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var tvTime: TextView   //
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

    private fun datePick() {   // 클래스 객체지향 특성을 위해 메서드화 한다. onCreate 메서드에 일일이 적지 않음.
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this, "You Selected ${selectedYear}/${selectedMonth+1}/${selectedDay}", Toast.LENGTH_LONG).show()
            val selectedDate = "${selectedDay}/${selectedMonth+1}/${selectedYear}"   // 람다식, 달력에서 날짜를 선택하면 해당 값들이 selected 변수로 할당되고
            // 변수 값들이 인수로써 '->' 뒤에 함수에 사용된다. 맨 뒤 year month 등은 현재 날짜를 표시하는데 사용
            tvDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)   // 문자열을 데이트 형태로 변환
            val theDate = sdf.parse(selectedDate) ?: return@OnDateSetListener   // null이면 onDateSetListner를 탈출
            val currentDate = sdf.parse("${dayOfMonth}/${month+1}/${year}") ?: return@OnDateSetListener
            val theDateInMinutes = theDate.time / 60000
            val currentDateInMintues = currentDate.time / 60000

            val differenceInMinutes = currentDateInMintues - theDateInMinutes
            tvTime.text = differenceInMinutes.toString()
        },
            year, month, dayOfMonth) // show() 코드 꼭 입력! 단, show()의 return 타입이 Unit인데,
        // 선택날짜 제한을 위해 datePicker 클래스를 사용하려면 datePickerDialog 타입이 되어야한다. 따라서, show()를 나중에 붙여준다.

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}
