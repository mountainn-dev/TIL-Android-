package com.mountain.quizapppractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mountain.quizapppractice.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Quiz.setQuestion(
            binding.tvQuestion, binding.imgQuestion, binding.pgBar, binding.tvOpt1,
            binding.tvOpt2, binding.tvOpt3, binding.tvOpt4, binding.btnCheckAnswer
        )
        // TODO: 오브젝트에서 mCurrent 변수를 증가시키면 왜 튕기는지??
        mCurrentPosition++

        binding.btnCheckAnswer.setOnClickListener {
            Quiz.setQuestion(
                binding.tvQuestion, binding.imgQuestion, binding.pgBar, binding.tvOpt1,
                binding.tvOpt2, binding.tvOpt3, binding.tvOpt4, binding.btnCheckAnswer
            )
            mCurrentPosition++
        }
        val tvList = arrayOf(binding.tvOpt1, binding.tvOpt2, binding.tvOpt3, binding.tvOpt4)
        for (tv in tvList) {
            tv.setOnClickListener(this)
        }
    }

    override fun onClick(tv: View) {
        binding.tvOpt1.setBackgroundResource(R.drawable.border_editbox)
        binding.tvOpt2.setBackgroundResource(R.drawable.border_editbox)
        binding.tvOpt3.setBackgroundResource(R.drawable.border_editbox)
        binding.tvOpt4.setBackgroundResource(R.drawable.border_editbox)
        tv.setBackgroundResource(R.drawable.border_selected_opt)
    }
}