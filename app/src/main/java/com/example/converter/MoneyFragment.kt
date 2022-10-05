package com.example.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.converter.databinding.FragmentMoneyBinding
import com.example.converter.services.services


class MoneyFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var binding: FragmentMoneyBinding
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var spinnerFrom: Spinner
    lateinit var spinnerTo: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoneyBinding.inflate(inflater)

        editText1 = binding.etVal1;
        editText2 = binding.etVal2;
        services.blockKeyboardInput(editText1)
        services.blockKeyboardInput(editText2)

        spinnerFrom = binding.spinnerFrom
        spinnerTo = binding.spinnerTo

        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?,
                position: Int, id: Long) {
                Toast.makeText(this@MoneyFragment.context,"You Selected " +
                        "${adapterView?.getItemAtPosition(position)}",Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // NO WAY
            }

        }

        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                Toast.makeText(this@MoneyFragment.context,"You Selected " +
                        "${adapterView?.getItemAtPosition(position)}",Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // NO WAY
            }

        }


        return binding.root
    }


}