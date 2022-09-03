package com.mountain.amicomprojectmanager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mountain.amicomprojectmanager.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener { signUp() }
        binding.clSignUp.setOnClickListener { hideKeyboard() }
        binding.btnGoBack.setOnClickListener { finish() }
    }
    private fun signUp() {
        val auth = Firebase.auth
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val passwordCheck = binding.editCheckPassword.text.toString()
        
        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (password == passwordCheck) {
                binding.tvWrongMessage.text = ""
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            // TODO: log.d는 무슨 이유로 사용하는건지?
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            Toast.makeText(baseContext,
                                "회원가입이 완료되었습니다.",
                                Toast.LENGTH_SHORT).show()
                            // TODO: updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext,
                                "회원가입에 실패하였습니다. 문제가 지속될 경우 동아리에 문의해주세요.",
                                Toast.LENGTH_SHORT).show()
                            // TODO: updateUI(null)
                        }
                    }
                finish()
            }
            else {
                binding.tvWrongMessage.text = "비밀번호가 일치하지 않습니다."
            }
        } else binding.tvWrongMessage.text = "이메일과 비밀번호를 입력해주세요."
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