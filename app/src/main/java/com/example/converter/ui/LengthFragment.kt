//package com.example.converter.ui
//
//import android.os.Bundle
//import android.text.SpannableStringBuilder
//import android.view.*
//import android.widget.AdapterView
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
//import com.example.converter.databinding.FragmentLengthBinding
//import com.example.converter.models.MetricsViewModel
//import com.example.converter.services.services
//import com.example.converter.services.services.Companion.convert
//import com.example.converter.services.services.Companion.copyText
//
//
//class LengthFragment : Fragment() {
//    lateinit var binding: FragmentLengthBinding
//    private val viewModel: MetricsViewModel by activityViewModels<MetricsViewModel>()
//    var Type: String = "Length"
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        services.blockKeyboardInput(binding.editText1)
//        services.blockKeyboardInput(binding.editText2)
//
//        binding.editText2.setOnClickListener {
//            binding.editText2.setCursorVisible(false);
//        }
//
//        binding.editText2.customSelectionActionModeCallback = object : ActionMode.Callback {
//            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
//                return false
//            }
//
//            override fun onDestroyActionMode(mode: ActionMode) {}
//            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
//                return false
//            }
//
//            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
//                return false
//            }
//        }
//
//        binding.btnSwap.setOnClickListener {
//            val position_from = binding.spinnerFrom.selectedItemPosition
//            val position_to = binding.spinnerTo.selectedItemPosition
//
//            binding.spinnerFrom.setSelection(position_to)
//            binding.spinnerTo.setSelection(position_from)
//
//        }
//
//        binding.bntCopy1.setOnClickListener {
//
//            copyText(it, binding.editText1)
//
//            Toast.makeText(
//                this@LengthFragment.context,
//                "Text copied ",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//
//        binding.bntCopy2.setOnClickListener {
//            copyText(it, binding.editText2)
//
//            Toast.makeText(
//                this@LengthFragment.context,
//                "Text copied ",
//                Toast.LENGTH_LONG
//            ).show()
//
//        }
//
//
//        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                adapterView: AdapterView<*>?, view: View?,
//                position: Int, id: Long
//            ) {
//                callConverter()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                // NO WAY
//            }
//
//        }
//
//        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                adapterView: AdapterView<*>?, view: View?,
//                position: Int, id: Long
//            ) {
//                callConverter()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                // NO WAY
//            }
//
//        }
//
//
//        viewModel.editTextMessage.value = binding.editText1
//
//        viewModel.StringMessageLength.observe(viewLifecycleOwner) {
//
//            if (it.equals("-1")) {
//                binding.editText1?.setText("")
//            } else if (it.equals("-0")) {
//                val cursorPose: Int = binding.editText1.selectionStart
//                val textLen: Int = binding.editText1.text.length
//
//                if (cursorPose != 0 && textLen != 0) {
//                    val selection: SpannableStringBuilder =
//                        binding.editText1.text as SpannableStringBuilder
//                    selection.replace(cursorPose - 1, cursorPose, "")
//                    binding.editText1.setText(selection)
//                    binding.editText1.setSelection(cursorPose - 1)
//                }
//            } else {
//                updateString(it)
//            }
//
//            callConverter()
//        }
//
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentLengthBinding.inflate(inflater)
//
//        return binding.root
//    }
//
//    override fun onStart() {
//        super.onStart()
//        callConverter()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        viewModel.StringMessageLength.value =
//            "" // очищаю, шоб после переворота не добавился лишний символ
//    }
//
//    fun updateString(strToAdd: String) {
//        if (strToAdd.isEmpty())
//            return
//
//        var oldStr: String = binding.editText1?.text.toString()
//        val cursorPos: Int = binding.editText1?.selectionStart
//        var leftString: String = oldStr.substring(0, cursorPos)
//        var rightStr: String = oldStr.substring(cursorPos)
//
//        if (binding.editText1.text.toString().equals("")) {
//            binding.editText1.setText(strToAdd)
//        } else {
//            binding.editText1.setText(String.format("%s%s%s", leftString, strToAdd, rightStr))
//        }
//
//        if (binding.editText1?.text.length <= 13){
//            binding.editText1.setSelection(cursorPos + 1)
//
//        }
//    }
//
//    fun callConverter() {
//        val type: String = Type
//        val from: String = binding.spinnerFrom.selectedItem.toString()
//
//        val to: String = binding.spinnerTo.selectedItem.toString()
//        val number: String = binding.editText1.text.toString()
//        var answer = convert(type, from, to, number)
//
//        var response = answer
//        if (answer != "")
//            response = services.period(answer)
//
//
//        binding.editText2.setText(response)
//    }
//}
//
