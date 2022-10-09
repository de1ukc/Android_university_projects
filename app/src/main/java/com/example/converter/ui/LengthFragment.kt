package com.example.converter.ui

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.*
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.converter.databinding.FragmentLengthBinding
import com.example.converter.models.AllFragmentsViewModel
import com.example.converter.services.services
import com.example.converter.services.services.Companion.convert
import com.example.converter.services.services.Companion.copyText
import com.google.android.material.tabs.TabLayout


class LengthFragment : Fragment() {
    lateinit var binding: FragmentLengthBinding
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
        binding = FragmentLengthBinding.inflate(inflater)

        editText1 = binding.etVal1;
        editText2 = binding.etVal2;
        services.blockKeyboardInput(editText1)
        services.blockKeyboardInput(editText2)

        editText2.setOnClickListener {
            editText2.setCursorVisible(false);
        }

        editText2.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode) {}
            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                return false
            }
        }

        spinnerFrom = binding.spinnerFrom
        spinnerTo = binding.spinnerTo

        viewModel.tabLayoutMessage.observe(viewLifecycleOwner) {
            tabLayout = it
        }

        binding.btnSwap.setOnClickListener {
            val positionFrom = spinnerFrom.selectedItemPosition
            val positionTo = spinnerTo.selectedItemPosition

            spinnerFrom.setSelection(positionTo)
            spinnerTo.setSelection(positionFrom)

        }

        binding.bntCopy1.setOnClickListener {

            copyText(it, editText1)

            Toast.makeText(
                this@LengthFragment.context,
                "Text copied ",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.bntCopy2.setOnClickListener {
            copyText(it, editText2)

            Toast.makeText(
                this@LengthFragment.context,
                "Text copied ",
                Toast.LENGTH_LONG
            ).show()

        }

        var spinAdapter = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                callConverter()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // NO WAY
            }

        }
        spinnerFrom.onItemSelectedListener = spinAdapter
        spinnerTo.onItemSelectedListener = spinAdapter

        viewModel.editTextMessage.value = editText1

        viewModel.StringMessageLength.observe(viewLifecycleOwner) {

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

            callConverter()
        }

        return binding.root
    }


    fun updateString(strToAdd: String) {
        var oldStr: String = editText1?.text.toString()
        val cursorPos: Int = editText1?.selectionStart
        var leftString: String = oldStr.substring(0, cursorPos)
        var rightStr: String = oldStr.substring(cursorPos)

        if (editText1.text.toString().equals("")) {
            editText1.setText(strToAdd)
        }
        else {
            editText1.setText(String.format("%s%s%s", leftString, strToAdd, rightStr))
        }

        editText1.setSelection(cursorPos + 1)
    }

    fun callConverter() {
        val type: String = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text.toString()
        val from: String = spinnerFrom.selectedItem.toString()

        val to: String = spinnerTo.selectedItem.toString()
        val number: String = editText1.text.toString()
        var answer = convert(type, from, to, number)

        answer = services.period(answer)

        editText2.setText(answer)
    }
}

