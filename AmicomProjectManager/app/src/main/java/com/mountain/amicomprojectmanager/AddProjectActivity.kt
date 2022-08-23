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
import androidx.fragment.app.Fragment
import com.mountain.amicomprojectmanager.databinding.ActivityAddProjectBinding

class AddProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("semester", binding.editSemester.text)
            intent.putExtra("projectName", binding.editProjectName.text)
            intent.putExtra("contents", binding.editContents.text)
            intent.putExtra("chatroom", binding.editChatroom.text)
            startActivity(intent)
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
}