package com.mountain.numcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.mountain.numcalc.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myCalculator = NumCalculator()
    }

    class NumCalculator() {
        var haveDecimal = false
        init {
            binding.btnDecimal.setOnClickListener { onDecimal(binding.btnDecimal) }
            binding.btnCLR.setOnClickListener { onCLR() }

            binding.btnOne.setOnClickListener { onNum(binding.btnOne) }
            binding.btnTwo.setOnClickListener { onNum(binding.btnTwo) }
            binding.btnThree.setOnClickListener { onNum(binding.btnThree) }
            binding.btnFour.setOnClickListener { onNum(binding.btnFour) }
            binding.btnFive.setOnClickListener { onNum(binding.btnFive) }
            binding.btnSix.setOnClickListener { onNum(binding.btnSix) }
            binding.btnSeven.setOnClickListener { onNum(binding.btnSeven) }
            binding.btnEight.setOnClickListener { onNum(binding.btnEight) }
            binding.btnNine.setOnClickListener { onNum(binding.btnNine) }
            binding.btnZero.setOnClickListener { onNum(binding.btnZero) }
        }

        private fun onNum(view: View) {
            if (binding.tvInput.text.toString() == "0") {   // 버튼의 text값들이 입력되는 순간, tvInput이 charsequence로 형변환되기 때문에, toString을 넣어준다.
                binding.tvInput.text = ""
                // 소수점 유무와 숫자 길이에 따라 컴마 입력에 차이를 두어 세자리 구분자를 출력한다.
                if (!binding.tvInput.text.contains(".") && (binding.tvInput.text.length == 3 || (binding.tvInput.text.length-6)%4 == 1)) {
                    binding.tvInput.append(",")
                    binding.tvInput.append((view as Button).text) // 입력받은 뷰가 버튼일 경우, 해당 버튼의 텍스트를 tvInput에 추가
                    binding.tvInputSave.text = binding.tvInput.text
                }
                else {
                    binding.tvInput.append((view as Button).text)
                    binding.tvInputSave.text = binding.tvInput.text
                }
            } else {
                if (!binding.tvInput.text.contains(".") && (binding.tvInput.text.length == 3 || (binding.tvInput.text.length-6)%4 == 1)) {
                    binding.tvInput.append(",")
                    binding.tvInput.append((view as Button).text) // 입력받은 뷰가 버튼일 경우, 해당 버튼의 텍스트를 tvInput에 추가
                    binding.tvInputSave.text = binding.tvInput.text
                }
                else {
                    binding.tvInput.append((view as Button).text)
                    binding.tvInputSave.text = binding.tvInput.text
                }
            }
        }

        private fun onCLR() {
            binding.tvInput.text = "0"
            binding.tvInputSave.text = binding.tvInput.text
        }

        private fun onDecimal(view: View) {
            if (binding.tvInput.text.contains(".")) {   // contains(x) 는 character 'x' 포함여부에 따라 불리안 값 반환
                haveDecimal = true
            }
            else {
                binding.tvInput.append((view as Button).text)
                binding.tvInputSave.text = binding.tvInput.text
            }
        }
    }
}