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
        var isOperator = true   // 연산자 유무 확인용 변수. 초기값을 true로 두고, 이후 연산자가 입력될 수 있을 때만 false로 설정해준다.
        var isZero = false
        init {
            // 단순반복 setOnClickListner는 array와 for 루프로 처리
            val btnNumList = arrayOf<Button>(binding.btnOne, binding.btnTwo, binding.btnThree, binding.btnFour,
                binding.btnFive, binding.btnSix, binding.btnSeven, binding.btnEight, binding.btnNine, binding.btnZero)
            val btnOperatorList = arrayOf<Button>(binding.btnCLR, binding.btnPlus, binding.btnMinus,
                binding.btnMultiple, binding.btnDivide)

            for (btn in btnNumList) { btn.setOnClickListener { onNum(it) }
            }
            for (btn in btnOperatorList) { btn.setOnClickListener { onOperator(it) }
            }
            binding.btnCLR.setOnClickListener { onCLR() }
            binding.btnDecimal.setOnClickListener { onDecimal(it) }
            binding.btnEqual.setOnClickListener { onEqual(it) }
        }

        private fun onNum(view: View) {
            // 버튼의 text값들이 입력되는 순간, tvInput이 charsequence로 형변환되기 때문에, toString을 넣어준다.
            // Input 텍스트를 기준으로 값이 0 즉 처음 혹은 연산자 입력 직후와 그 외의 상황으로 나누어 구성
            if (binding.tvInput.text.toString() == "0") {
                binding.tvInput.text = (view as Button).text // 입력받은 뷰가 버튼일 경우, 해당 버튼의 텍스트를 tvInput에 추가
                binding.tvInputSave.append(view.text)
                isOperator = false
            } else {
                // 소수점 유무와 숫자 길이에 따라 컴마 입력에 차이를 두어 세자리 구분자를 출력한다.
                if (!binding.tvInput.text.contains(".") && (binding.tvInput.text.length == 3 || (binding.tvInput.text.length-6)%4 == 1)) {
                    binding.tvInput.append(",")
                    binding.tvInput.append((view as Button).text)
                    binding.tvInputSave.append(view.text)
                    isOperator = false
                }
                else {
                    binding.tvInput.append((view as Button).text)
                    binding.tvInputSave.append(view.text)
                    isOperator = false
                }
            }
        }

        private fun onCLR() {
            binding.tvInput.text = "0"
            binding.tvInputSave.text = ""
            isOperator = true
        }

        private fun onDecimal(view: View) {
            if (!binding.tvInput.text.contains(".")) {   // contains(x) 는 character 'x' 포함여부에 따라 불리안 값 반환
                binding.tvInput.append((view as Button).text)
                binding.tvInputSave.append(view.text)
                isOperator = true
            }
        }

        private fun onOperator(view: View) {   // 연산자 입력 직후 tvInput은 초기화, Save는 초기화x
            if (!isOperator) {
                binding.tvInputSave.append((view as Button).text)
                binding.tvInput.text = "0"
                isOperator = true
            }
            else if (binding.tvInput.text.toString() == "0" && (view as Button).text == "-") {   // 연산자 입력이 안 될 경우, - 조건을 추가하여 음수입력 가능
                binding.tvInput.text = view.text
                binding.tvInputSave.append(view.text)
                isOperator = true
            }
        }

        private fun onEqual(view: View) {
            binding.tvInputSave.append((view as Button).text)
            val result = binding.tvInputSave.text.toString()
        }
    }
}