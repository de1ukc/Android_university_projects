package com.example.calculator.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.R
import com.example.calculator.databinding.FragmentFieldBinding
import com.example.calculator.databinding.FragmentNumpadBinding


class NumpadFragment : Fragment() {

    lateinit var binding: FragmentNumpadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNumpadBinding.inflate(inflater)
        return binding.root
    }

}