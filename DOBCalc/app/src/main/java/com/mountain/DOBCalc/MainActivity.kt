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

    private var tvDate: TextView? = null   // private 설정을 해두면 메인액티비티 내 다른 클래스에서 사용불가
    private var tvTime: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDate: Button = findViewById(R.id.btnDate)
        tvDate = findViewById(R.id.tvDate)
        tvTime = findViewById(R.id.tvTime)
        btnDate.setOnClickListener {
            clickDate()
        }
    }

    private fun clickDate() {   // 메서드를 private 접근제한으로 설정하여, 다른 클래스에서 접근 불가(오작동 방지)
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this, "Year was ${selectedYear}, month was ${selectedMonth+1}," +
                    " day of month was ${selectedDay}",
                Toast.LENGTH_LONG).show()
            val selectedDate = "${selectedDay}/${selectedMonth+1}/${selectedYear}"
            tvDate?.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
                theDate?.let{
                    val selectedDateInMinutes = theDate.time / 60000   // 1970.1.1 부터 선택된 날짜까지의
                    // ms 단위 시간을 10000 나누어 초 단위, 60 나누어 분 단위로 환산
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                        currentDate?.let{
                            val currentDateInMinutes = currentDate.time / 60000   // 1970.1.1 부터 현재 날짜까지 환산

                            // 위 코드랑 뭐가 다른지 확인 필요
                            // val currentDateInMinutes2 = System.currentTimeMillis() / 60000

                            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                            tvTime?.setText("${differenceInMinutes}")
                        }

                }

        },
            year, month, day)   // 선택가능날짜를 제한하기 위해 .show()를 지우고 dpd 변수에 할당
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()   // dpd datepicker 프로퍼티에 최대가능날짜를 입력하고 .show()
    }
}