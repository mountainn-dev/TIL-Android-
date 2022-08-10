package com.mountain.quizapppractice

import android.content.Intent
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
        val name = intent.getStringExtra("Name")


        Quiz.setQuestion(
            binding.tvQuestion, binding.imgQuestion, binding.pgBar, binding.tvPg, binding.tvOpt1,
            binding.tvOpt2, binding.tvOpt3, binding.tvOpt4, binding.btnCheckAnswer
        )
        // TODO: 오브젝트에서 mCurrent 변수를 증가시키면 왜 튕기는지??

        binding.btnCheckAnswer.setOnClickListener {
            if (mCurrentPosition == binding.pgBar.max) {
                val intent = Intent(this, FinishActivity::class.java)
                intent.putExtra("Name", name)
                intent.putExtra("Score", mScore)
                intent.putExtra("QuestionCount", binding.pgBar.max)
                startActivity(intent)
            }
            else if (mAnswerExpose) {
                Quiz.setQuestion(
                    binding.tvQuestion, binding.imgQuestion, binding.pgBar, binding.tvPg, binding.tvOpt1,
                    binding.tvOpt2, binding.tvOpt3, binding.tvOpt4, binding.btnCheckAnswer
                )
            } else {
                Quiz.checkAnswer(binding.tvOpt1, binding.tvOpt2, binding.tvOpt3, binding.tvOpt4,
                    binding.btnCheckAnswer, binding.pgBar, this)
            }
        }

        val tvList = arrayOf(binding.tvOpt1, binding.tvOpt2, binding.tvOpt3, binding.tvOpt4)
        for (tv in tvList) {
            tv.setOnClickListener(this)
        }
    }

    // 텍스트뷰를 인수로 입력받기 위해 파라미터를 바꾸면 오버라이딩에 어긋나기 때문에
    // Quiz 오브젝트가 아닌 onCreate메서드에서 onClick메서드를 구현했다.
    override fun onClick(tv: View) {
        binding.tvOpt1.setBackgroundResource(R.drawable.border_editbox)
        binding.tvOpt2.setBackgroundResource(R.drawable.border_editbox)
        binding.tvOpt3.setBackgroundResource(R.drawable.border_editbox)
        binding.tvOpt4.setBackgroundResource(R.drawable.border_editbox)
        tv.setBackgroundResource(R.drawable.border_selected_opt)
        when (tv) {
            binding.tvOpt1 -> mCurrentSelected = 1
            binding.tvOpt2 -> mCurrentSelected = 2
            binding.tvOpt3 -> mCurrentSelected = 3
            binding.tvOpt4 -> mCurrentSelected = 4
        }
    }
}