package com.example.converter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.example.converter.R
import com.example.converter.databinding.FragmentNumpadBinding
import com.example.converter.models.MetricsViewModel

class NumpadFragment : Fragment() {
    lateinit var binding: FragmentNumpadBinding
    private val viewModel: MetricsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumpadBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnZero.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn00?.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn1.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn2.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn3.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn4.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn5.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn6.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn7.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn8.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btn9.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btnDot.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btnAc.setOnClickListener { view -> numButtonsOnClick(view) }
        binding.btnClear.setOnClickListener { view -> numButtonsOnClick(view) }
    }

    fun numButtonsOnClick(view: View) {
        var symb: String = ""

        when (view.id) {
            binding.btnZero?.id -> symb = getString(R.string.zero)
            binding.btn00?.id -> symb = getString(R.string.doubleZero)
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
            binding.btnAc?.id -> symb = "-1"
            binding.btnClear?.id -> symb = "-0"
        }

        viewModel.stringMessage.value = symb
    }


}