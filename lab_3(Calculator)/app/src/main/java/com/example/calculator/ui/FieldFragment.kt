package com.example.calculator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.calculator.R
import com.example.calculator.databinding.FragmentFieldBinding
import com.example.calculator.model.SymbViewModel
import com.example.calculator.services.service.Companion.blockKeyboardInput
import org.mariuszgromada.math.mxparser.Expression


class FieldFragment : Fragment() {
    lateinit var binding: FragmentFieldBinding
    private val viewModel: SymbViewModel by activityViewModels<SymbViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFieldBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blockKeyboardInput(binding.editTextValue)
    }

    override fun onPause() {
        super.onPause()
        blockKeyboardInput(binding.editTextValue)
        binding.editTextValue.isEnabled =
            false //  отключаю всплывание клавиатуры, после сворачивания приложения
        binding.editTextValue.isEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stringMessage.value = ""
    }
}