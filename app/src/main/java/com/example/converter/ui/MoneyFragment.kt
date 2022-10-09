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
import com.example.converter.databinding.FragmentMoneyBinding
import com.example.converter.models.AllFragmentsViewModel
import com.example.converter.services.services
import com.example.converter.services.services.Companion.convert
import com.example.converter.services.services.Companion.copyText
import com.example.converter.services.services.Companion.period
import com.google.android.material.tabs.TabLayout


class MoneyFragment : Fragment() {
    lateinit var binding: FragmentMoneyBinding
    private val viewModel: AllFragmentsViewModel by activityViewModels<AllFragmentsViewModel>()
    lateinit var tabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        services.blockKeyboardInput(binding.editText1)
        services.blockKeyboardInput(binding.editText2)

        binding.editText2.setOnClickListener {
            binding.editText2.setCursorVisible(false);
//            editText2.isLongClickable = false
        }

        binding.editText2.customSelectionActionModeCallback = object : ActionMode.Callback {
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

        viewModel.tabLayoutMessage.observe(viewLifecycleOwner) {
            tabLayout = it
        }

        binding.btnSwap.setOnClickListener {
            val position_from = binding.spinnerFrom.selectedItemPosition
            val position_to = binding.spinnerTo.selectedItemPosition

            binding.spinnerFrom.setSelection(position_to)
            binding.spinnerTo.setSelection(position_from)

        }

        binding.bntCopy1.setOnClickListener {

            copyText(it, binding.editText1)

            Toast.makeText(
                this@MoneyFragment.context,
                "Text copied ",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.bntCopy2.setOnClickListener {
            copyText(it, binding.editText2)

            Toast.makeText(
                this@MoneyFragment.context,
                "Text copied ",
                Toast.LENGTH_LONG
            ).show()

        }


        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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


        viewModel.editTextMessage.value = binding.editText1

        viewModel.StringMessageMoney.observe(viewLifecycleOwner) {

            if (it.equals("-1")) {
                binding.editText1?.setText("")
            } else if (it.equals("-0")) {
                val cursorPose: Int = binding.editText1.selectionStart
                val textLen: Int = binding.editText1.text.length

                if (cursorPose != 0 && textLen != 0) {
                    val selection: SpannableStringBuilder =
                        binding.editText1.text as SpannableStringBuilder
                    selection.replace(cursorPose - 1, cursorPose, "")
                    binding.editText1.setText(selection)
                    binding.editText1.setSelection(cursorPose - 1)
                }
            } else {
                updateString(it)
            }

            callConverter()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoneyBinding.inflate(inflater)
        return binding.root
    }


    fun updateString(strToAdd: String) {
        var oldStr: String = binding.editText1?.text.toString()
        val cursorPos: Int = binding.editText1?.selectionStart
        var leftString: String = oldStr.substring(0, cursorPos)
        var rightStr: String = oldStr.substring(cursorPos)

        if (binding.editText1.text.toString().equals("")) {
            binding.editText1.setText(strToAdd)
        }
        else {
            binding.editText1.setText(String.format("%s%s%s", leftString, strToAdd, rightStr))
        }
        binding.editText1.setSelection(cursorPos + 1)
    }

    fun callConverter() {
        val type: String = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text.toString()
        val from: String = binding.spinnerFrom.selectedItem.toString()

        val to: String = binding.spinnerTo.selectedItem.toString()
        val number: String = binding.editText1.text.toString()
        var answer = convert(type, from, to, number)

        var response = answer
        if (answer != "")
            response = period(answer)

        binding.editText2.setText(response)
    }
//    private fun pasteTextFromClipboard() {
//        val clipboardManager = it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//        tvTextToPaste.text = clipboardManager.primaryClip?.getItemAt(0)?.text
//    }
}



