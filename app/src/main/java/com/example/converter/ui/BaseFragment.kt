package com.example.converter.ui

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.converter.R
import com.example.converter.databinding.FragmentBaseBinding
import com.example.converter.models.MetricsViewModel
import com.example.converter.services.services
import com.google.android.material.tabs.TabLayout


class BaseFragment : Fragment() {
    lateinit var binding: FragmentBaseBinding
//    lateinit var  tabLayout: TabLayout

    private val viewModel: MetricsViewModel by activityViewModels<MetricsViewModel>()
    private var convertType: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            convertType = it.getString("Type")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinnerValues(binding.spinnerFrom, binding.spinnerTo)

        services.blockKeyboardInput(binding.editText1)
        services.blockKeyboardInput(binding.editText2)

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


        binding.btnSwap.setOnClickListener {
            val position_from = binding.spinnerFrom.selectedItemPosition
            val position_to = binding.spinnerTo.selectedItemPosition

            binding.spinnerFrom.setSelection(position_to)
            binding.spinnerTo.setSelection(position_from)

        }

        binding.bntCopy1.setOnClickListener {

            services.copyText(it, binding.editText1)

            Toast.makeText(
                this@BaseFragment.context,
                "Text copied ",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.bntCopy2.setOnClickListener {
            services.copyText(it, binding.editText2)

            Toast.makeText(
                this@BaseFragment.context,
                "Text copied ",
                Toast.LENGTH_LONG
            ).show()

        }

//        viewModel.tabLayoutMessage.observe(viewLifecycleOwner){
//            tabLayout = it
//        }

        binding.spinnerFrom.onItemSelectedListener = spinnerSetOnClickListener

        binding.spinnerTo.onItemSelectedListener = spinnerSetOnClickListener

        when(convertType){
            "Money" -> {
                viewModel.stringMoneyMessage.observe(viewLifecycleOwner) {
                    observer(it)
                }
            }
            "Length" -> {
                viewModel.stringLengthMessage.observe(viewLifecycleOwner) {
                    observer(it)
                }
            }
           "Volume" -> {
                viewModel.stringVolumeMessage.observe(viewLifecycleOwner) {
                    observer(it)
                }
            }

        }



    }

    override fun onStart() {
        super.onStart()
        callConverter()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        when(convertType){
            "Money" -> {
                viewModel.stringMoneyMessage.value = ""
            }
            "Length" -> {
                viewModel.stringLengthMessage.value = ""
            }
            "Volume" -> {
                viewModel.stringVolumeMessage.value = ""
            }
        }
//        viewModel.stringMessage.value =
//            "" // очищаю, шоб после переворота не добавился лишний символ
    }

    fun updateString(strToAdd: String) {
        if (strToAdd.isEmpty())
            return

        var oldStr: String = binding.editText1?.text.toString()
        val cursorPos: Int = binding.editText1?.selectionStart
        var leftString: String = oldStr.substring(0, cursorPos)
        var rightStr: String = oldStr.substring(cursorPos)

        if (binding.editText1.text.toString().equals("")) {
            binding.editText1.setText(strToAdd)
        } else {
            binding.editText1.setText(String.format("%s%s%s", leftString, strToAdd, rightStr))
        }

        if (binding.editText1?.text.length < 16) {  // до 16, чтоб курсор не обнулялся на последнем символе
            binding.editText1.setSelection(cursorPos + 1)
        }
    }

    var spinnerSetOnClickListener = object : AdapterView.OnItemSelectedListener {
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

    fun setSpinnerValues(spinnerFrom: Spinner, spinnerTo: Spinner) {

        val values = when (convertType) {
            "Money" -> resources.getStringArray(R.array.moneyValues)
            "Length" -> resources.getStringArray(R.array.lenghtValues)
            "Volume" -> resources.getStringArray(R.array.volumeValues)
            else -> {
                arrayOf()
            }
        }

        spinnerFrom.adapter =
            this@BaseFragment.context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    values
                )
            }
        spinnerTo.adapter =
            this@BaseFragment.context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    values
                )
            }
    }

    fun callConverter() {
        val type: String = convertType!!
        val from: String = binding.spinnerFrom.selectedItem.toString()

        val to: String = binding.spinnerTo.selectedItem.toString()
        val number: String = binding.editText1.text.toString()
        var answer = services.convert(type, from, to, number)

        var response = answer
        if (answer != "")
            response = services.period(answer)

        binding.editText2.setText(response)
    }

    fun observer(it: String){
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
            if (binding.editText1.text.length < 15) {
                if (it.equals(".")) {
                    if (!binding.editText1.text.contains(".") && binding.editText1.text.length != 14) {
                        updateString(it)
                    }
                    if (binding.editText1.text.length == 14){
                        Toast.makeText(
                            this@BaseFragment.context,
                            "Last symbol can not be . ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else if (it.equals("00")) {
                    if (binding.editText1.text.length < 14) {
                        updateString(it)
                    } else {
                        Toast.makeText(
                            this@BaseFragment.context,
                            "Only 1 symbol can be added ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    updateString(it)
                }
            } else {
                Toast.makeText(
                    this@BaseFragment.context,
                    "Naaah! Too many numbers ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        callConverter()
    }

}



