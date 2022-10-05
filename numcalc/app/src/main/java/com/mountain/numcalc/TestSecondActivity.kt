package com.mountain.numcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mountain.numcalc.databinding.ActivityTestSecondBinding


class TestSecondActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val numList = arrayOf(
            binding.btnOne, binding.btnTwo, binding.btnThree,
            binding.btnFour, binding.btnFive, binding.btnSix,
            binding.btnSeven, binding.btnEight, binding.btnNine,
            binding.btnZero
        )
        val operatorList = arrayOf(
            binding.btnPlus, binding.btnMinus,
            binding.btnMultiple, binding.btnDivide
        )

        for (num in numList) {
            num.setOnClickListener { NumCalc.selectNumber(num, binding.tvNum) }
        }
        for (operator in operatorList) {
            operator.setOnClickListener { NumCalc.selectOperator(operator, binding.tvNum) }
        }
        binding.btnResult.setOnClickListener { NumCalc.selectResult(binding.tvNum) }
        binding.btnDecimal.setOnClickListener { NumCalc.selectDecimal(
                binding.btnDecimal,
                binding.tvNum
            )
        }
        binding.btnCLR.setOnClickListener { NumCalc.selectCLR(binding.tvNum) }
        binding.btnPercent.setOnClickListener { NumCalc.selectPercent(binding.tvNum, binding.tvPercent) }
    }
}