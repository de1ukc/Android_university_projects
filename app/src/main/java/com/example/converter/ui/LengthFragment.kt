package com.example.converter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.converter.databinding.FragmentLengthBinding
import com.example.converter.models.AllFragmentsViewModel
import com.example.converter.services.services

class LengthFragment : Fragment() {
    lateinit var binding: FragmentLengthBinding
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var spinnerFrom: Spinner
    lateinit var spinnerTo: Spinner
    private val viewModel: AllFragmentsViewModel by activityViewModels<AllFragmentsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLengthBinding.inflate(inflater)

        editText1 = binding.etVal1;
        editText2 = binding.etVal2;
        services.blockKeyboardInput(editText1)
        services.blockKeyboardInput(editText2)

        spinnerFrom = binding.spinnerFrom
        spinnerTo = binding.spinnerTo

        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                Toast.makeText(this@LengthFragment.context,"You Selected " +
                        "${adapterView?.getItemAtPosition(position)}", Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // NO WAY
            }

        }

        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                Toast.makeText(this@LengthFragment.context,"You Selected " +
                        "${adapterView?.getItemAtPosition(position)}", Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // NO WAY
            }

        }

        return binding.root
    }

}

