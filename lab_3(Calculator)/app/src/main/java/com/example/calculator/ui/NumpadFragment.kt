package com.example.calculator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.calculator.R
import com.example.calculator.databinding.FragmentFieldBinding
import com.example.calculator.databinding.FragmentNumpadBinding
import com.example.calculator.model.SymbViewModel


class NumpadFragment : Fragment() {
    lateinit var binding: FragmentNumpadBinding
    private val viewModel: SymbViewModel by activityViewModels<SymbViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumpadBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnZero?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn1?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn2?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn3?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn4?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn5?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn6?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn7?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn8?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btn9?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnDot?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnAcs?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnAsn?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnAtn?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnCos?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnTan?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnSin?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnProcent?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnPow?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnPi?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnLg?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnLn?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnExp?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnSqrt?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnFactorial?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnLeftBracket?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnRightBracket?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnMinus?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnPlus?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnDivide?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnMultiply?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnEqual?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnAc?.setOnClickListener{view -> numButtonsOnClick(view)}
        binding.btnClear?.setOnClickListener{view -> numButtonsOnClick(view)}
    }

    fun numButtonsOnClick(view: View) {
        var symb: String = ""

        when (view.id) {
            binding.btnZero?.id -> symb = getString(R.string.zero)
            binding.btn1?.id -> symb = getString(R.string.one)
            binding.btn2?.id -> symb = getString(R.string.two)
            binding.btn3?.id -> symb = getString(R.string.three)
            binding.btn4?.id -> symb = getString(R.string.four)
            binding.btn5?.id -> symb = getString(R.string.five)
            binding.btn6?.id -> symb = getString(R.string.six)
            binding.btn7?.id -> symb = getString(R.string.seven)
            binding.btn8?.id -> symb = getString(R.string.eight)
            binding.btn9?.id -> symb = getString(R.string.nine)
            binding.btnDot?.id -> symb = getString(R.string.dot)
            binding.btnAcs?.id -> symb = getString(R.string.acs) + "("
            binding.btnAsn?.id -> symb = getString(R.string.asn) + "("
            binding.btnAtn?.id -> symb = getString(R.string.atn) + "("
            binding.btnCos?.id -> symb = getString(R.string.cos) + "("
            binding.btnTan?.id -> symb = getString(R.string.tan) + "("
            binding.btnSin?.id -> symb = getString(R.string.sin) + "("
            binding.btnProcent?.id -> symb = getString(R.string.procent)
            binding.btnPow?.id -> symb = getString(R.string.pow)
            binding.btnPi?.id -> symb = getString(R.string.pi)
            binding.btnLg?.id -> symb = getString(R.string.lg) + "("
            binding.btnLn?.id -> symb = getString(R.string.ln) + "("
            binding.btnExp?.id -> symb = getString(R.string.e)
//            binding.btnSqrt?.id -> symb = getString(R.string.square)
            binding.btnSqrt?.id -> symb = "sqrt("
            binding.btnFactorial?.id -> symb = getString(R.string.factorial)
            binding.btnLeftBracket?.id -> symb = getString(R.string.leftBracket)
            binding.btnRightBracket?.id -> symb = getString(R.string.rightBracket)
            binding.btnMinus?.id -> symb = getString(R.string.minus)
            binding.btnPlus?.id -> symb = getString(R.string.plus)
            binding.btnDivide?.id -> symb = getString(R.string.divide)
            binding.btnMultiply?.id -> symb = getString(R.string.multiply)

            binding.btnEqual?.id -> symb = "-2"
            binding.btnAc?.id -> symb = "-1"
            binding.btnClear?.id -> symb = "-0"
        }

        viewModel.stringMessage.value = symb

    }
}