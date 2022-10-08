package com.example.converter.ui

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.converter.databinding.FragmentMoneyBinding
import com.example.converter.models.AllFragmentsViewModel
import com.example.converter.services.services
import com.example.converter.services.services.Companion.convert
import com.google.android.material.tabs.TabLayout


class MoneyFragment : Fragment() {
    lateinit var binding: FragmentMoneyBinding
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var spinnerFrom: Spinner
    lateinit var spinnerTo: Spinner
    lateinit var tabLayout: TabLayout
    private val viewModel: AllFragmentsViewModel by activityViewModels<AllFragmentsViewModel>()


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

        viewModel.tabLayoutMessage.observe(viewLifecycleOwner) {
            tabLayout = it
        }

        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                Toast.makeText(
                    this@MoneyFragment.context, "You Selected " +
                            "${adapterView?.getItemAtPosition(position)}", Toast.LENGTH_SHORT
                ).show()

//                val type: String = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text.toString()
//                val from: String = spinnerFrom.selectedItem.toString()
//
//                val to: String = spinnerTo.selectedItem.toString()
//                val number: String = editText1.text.toString()
//                val answer = convert(type, from, to, number)
//
//                editText2.setText(answer)
                call_converter()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // NO WAY
            }

        }

        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                Toast.makeText(
                    this@MoneyFragment.context, "You Selected " +
                            "${adapterView?.getItemAtPosition(position)}", Toast.LENGTH_SHORT
                ).show()


//                val type: String = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text.toString()
//                val from: String = spinnerFrom.selectedItem.toString()
//
//                val to: String = spinnerTo.selectedItem.toString()
//                val number: String = editText1.text.toString()
//                val answer = convert(type, from, to, number)
//
//                editText2.setText(answer)
                call_converter()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // NO WAY
            }

        }

        viewModel.editTextMessage.value = editText1

        viewModel.StringMessage.observe(viewLifecycleOwner) {

            if (it.equals("-1")) {
                editText1?.setText("")
            } else if (it.equals("-0")) {
                val cursorPose: Int = editText1.selectionStart
                val textLen: Int = editText1.text.length

                if (cursorPose != 0 && textLen != 0) {
                    val selection: SpannableStringBuilder =
                        editText1.text as SpannableStringBuilder
                    selection.replace(cursorPose - 1, cursorPose, "")
                    editText1.setText(selection)
                    editText1.setSelection(cursorPose - 1)
                }
            } else {
                updateString(it)
            }

//            val type: String = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text.toString()
//            val from: String = spinnerFrom.selectedItem.toString()
//
//            val to: String = spinnerTo.selectedItem.toString()
//            val number: String = editText1.text.toString()
//            val answer = convert(type, from, to, number)
//
//            editText2.setText(answer)
            call_converter()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }

    fun updateString(strToAdd: String) {
        var oldStr: String = editText1?.text.toString()
        val cursorPos: Int = editText1?.selectionStart
        var leftString: String = oldStr.substring(0, cursorPos)
        var rightStr: String = oldStr.substring(cursorPos)

        if (editText1.text.toString().equals("")) {
            editText1.setText(strToAdd)
        } else {
            editText1.setText(String.format("%s%s%s", leftString, strToAdd, rightStr))
        }
        editText1.setSelection(cursorPos + 1)
    }

    fun call_converter() {
        val type: String = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text.toString()
        val from: String = spinnerFrom.selectedItem.toString()

        val to: String = spinnerTo.selectedItem.toString()
        val number: String = editText1.text.toString()
        val answer = convert(type, from, to, number)

        editText2.setText(answer)
    }


}



