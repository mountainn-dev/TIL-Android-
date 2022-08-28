package com.mountain.amicomprojectmanager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mountain.amicomprojectmanager.databinding.ActivityAddProjectBinding
import java.nio.charset.CoderResult

class AddProjectActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityAddProjectBinding
    private val listYear = arrayOf("2022", "2023", "2024")
    private val listSemester = arrayOf("1학기", "2학기")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayAdapterYear = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listYear)
        val arrayAdapterSemester = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listSemester)
        binding.spinnerYear.onItemSelectedListener = this
        binding.spinnerYear.adapter = arrayAdapterYear
        binding.spinnerSemester.onItemSelectedListener = this
        binding.spinnerSemester.adapter = arrayAdapterSemester

        binding.cl.setOnClickListener { hideKeyboard() }
        binding.editContents.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?,
                                           p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?,
                                       p1: Int, p2: Int, p3: Int) {
                // count number of inputted characters in edit text
                binding.tvLength.text = "${p0?.toString()?.length}/100"
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.btnFinishAddProject.setOnClickListener {
            if (binding.editProjectName.text.trim().toString() == "") {
                Toast.makeText(this, "필수 기재사항을 확인해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, MainActivity::class.java)
                // TODO: intent 데이터를 보내기 전에, 자료형을 확실히 정해준다. ex) toString() 적용
                // TODO: 각 spinner에서 선택된 값들을 곧바로 이용할 수 있다. spinner.selectedItem
                intent.putExtra("year", binding.spinnerYear.selectedItem.toString())
                intent.putExtra("semester", binding.spinnerSemester.selectedItem.toString())
                intent.putExtra("projectName", binding.editProjectName.text.toString())
                intent.putExtra("contents", binding.editContents.text.toString())
                intent.putExtra("chatroom", binding.editChatroom.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        binding.btnGoBack.setOnClickListener {
            finish()
        }
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    @SuppressLint("ServiceCast")
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}