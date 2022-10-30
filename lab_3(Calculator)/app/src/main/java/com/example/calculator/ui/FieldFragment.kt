package com.example.calculator.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.calculator.R
import com.example.calculator.databinding.FragmentFieldBinding
import com.example.calculator.model.SymbViewModel
import com.example.calculator.services.CalcsMath
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

        viewModel.rotateMessage.observe(viewLifecycleOwner){
            if(!it.isEmpty()){
                binding.editTextValue.setText(it)
            }
        }

        viewModel.stringMessage.observe(viewLifecycleOwner) {
            observer(it)
        }

//        binding.btnRotate?.setOnClickListener{
//            onConfigurationChanged(it.context.con)
//        }
//        binding.btnRotate?.setOnClickListener{view -> rotateOnClick(view)}
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
        viewModel.rotateMessage.value = binding.editTextValue.text.toString()
    }

    fun observer(it: String) {
        when (it) {
            "-0" -> {
                val cursorPose: Int = binding.editTextValue.selectionStart
                val textLen: Int = binding.editTextValue.text.length

                if (cursorPose != 0 && textLen != 0) {
                    val selection: SpannableStringBuilder =
                        binding.editTextValue.text as SpannableStringBuilder
                    selection.replace(cursorPose - 1, cursorPose, "")
                    binding.editTextValue.setText(selection)
                    binding.editTextValue.setSelection(cursorPose - 1)
                }
            }
            "-1" -> binding.editTextValue?.setText("")
            "-2" -> {
                val answer = CalcsMath.Calculate(binding.editTextValue?.text.toString())
                if (answer.equals("Wrong Expression")) {
                    Toast.makeText(
                        this@FieldFragment.context,
                        "Wrong Expression",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.editTextValue.setText(answer)
                    binding.editTextValue.setSelection(answer.length)
                }

            }
            else -> {
                updateString(it)
            }
        }

    }

    fun updateString(strToAdd: String) {
        if (strToAdd.isEmpty())
            return

        var oldStr: String = binding?.editTextValue?.text.toString()
        val cursorPos: Int = binding.editTextValue?.selectionStart
        var leftString: String = oldStr.substring(0, cursorPos)
        var rightStr: String = oldStr.substring(cursorPos)

        if (binding.editTextValue.text.toString().equals("")) {
            binding.editTextValue.setText(strToAdd)
        } else {
            binding.editTextValue.setText(String.format("%s%s%s", leftString, strToAdd, rightStr))
        }

        if (strToAdd in listOf(
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "e", "!", "^", "%", "(",
                ")", ".", "*", "/", "+", "-"
            )
        ) {
            binding.editTextValue.setSelection(cursorPos + 1)

        } else if (strToAdd in listOf(
                "ln(", "lg("
            )
        ) {
            binding.editTextValue.setSelection(cursorPos + 3)
        } else if (strToAdd in listOf(
                "pi"
            )
        ) {
            binding.editTextValue.setSelection(cursorPos + 2)
        } else if (strToAdd in listOf(
                "sin(", "cos(", "tan(",
            )
        ) {
            binding.editTextValue.setSelection(cursorPos + 4)

        } else {
            binding.editTextValue.setSelection(cursorPos + 5)
        }
    }

//    fun rotateOnClick(view: View){
//
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//
//        // Checks the orientation of the screen
//        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
//            newConfig.orientation = Configuration.ORIENTATION_PORTRAIT
//        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
//            newConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
//        }
//    }

}