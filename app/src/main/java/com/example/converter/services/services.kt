package com.example.converter.services

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.EditText
import java.math.BigDecimal

class services {


    companion object {

        fun blockKeyboardInput(editText: EditText) {
            editText.setShowSoftInputOnFocus(false)
        }

        enum class TabLayoutPosition(val pos: Int){
            Money(0),
            Length(1),
            Volume(2)
        }

        fun copyText(view: View, editText: EditText) {

            val textToCopy = editText.text.toString()
            val clipboardManager =
                view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", textToCopy)
            clipboardManager.setPrimaryClip(clipData)
        }



        val unitsMap = mapOf(
            "length" to mapOf(
                "meter" to mapOf(
                    "meter" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "foot" to fun(x: Double): BigDecimal = (x * 3.281).toBigDecimal(),
                    "mile" to fun(x: Double): BigDecimal = (x / 1609).toBigDecimal(),
                    "inch" to fun(x: Double): BigDecimal = (x * 39.37).toBigDecimal(),
                    "yard" to fun(x: Double): BigDecimal = (x * 1.094).toBigDecimal()
                ),
                "foot" to mapOf(
                    "moot" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "meter" to fun(x: Double): BigDecimal = (x / 3.281).toBigDecimal(),
                    "mile" to fun(x: Double): BigDecimal = (x / 5280).toBigDecimal(),
                    "inch" to fun(x: Double): BigDecimal = (x * 12).toBigDecimal(),
                    "yard" to fun(x: Double): BigDecimal = (x / 3).toBigDecimal()
                ),
                "mile" to mapOf(
                    "mile" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "meter" to fun(x: Double): BigDecimal = (x * 1609).toBigDecimal(),
                    "foot" to fun(x: Double): BigDecimal = (x * 5280).toBigDecimal(),
                    "inch" to fun(x: Double): BigDecimal = (x * 63360).toBigDecimal(),
                    "yard" to fun(x: Double): BigDecimal = (x * 1760).toBigDecimal()
                ),
                "inch" to mapOf(
                    "inch" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "meter" to fun(x: Double): BigDecimal = (x / 39.37).toBigDecimal(),
                    "foot" to fun(x: Double): BigDecimal = (x / 12).toBigDecimal(),
                    "mile" to fun(x: Double): BigDecimal = (x / 63360).toBigDecimal(),
                    "yard" to fun(x: Double): BigDecimal = (x / 36).toBigDecimal()
                ),
                "yard" to mapOf(
                    "yard" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "meter" to fun(x: Double): BigDecimal = (x / 1.094).toBigDecimal(),
                    "foot" to fun(x: Double): BigDecimal = (x * 3).toBigDecimal(),
                    "mile" to fun(x: Double): BigDecimal = (x / 1760).toBigDecimal(),
                    "inch" to fun(x: Double): BigDecimal = (x * 36).toBigDecimal()
                )
            ),
            "volume" to mapOf(
                "liter" to mapOf(
                    "liter" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "milliliter" to fun(x: Double): BigDecimal = (x * 1000).toBigDecimal(),
                    "gallon" to fun(x: Double): BigDecimal = (x / 3.785).toBigDecimal(),
                    "pint" to fun(x: Double): BigDecimal = (x * 2.113).toBigDecimal()
                ),
                "milliliter" to mapOf(
                    "milliliter" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "liter" to fun(x: Double): BigDecimal = (x / 1000).toBigDecimal(),
                    "gallon" to fun(x: Double): BigDecimal = (x / 3785).toBigDecimal(),
                    "pint" to fun(x: Double): BigDecimal = (x / 473.2).toBigDecimal()
                ),
                "gallon" to mapOf(
                    "gallon" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "liter" to fun(x: Double): BigDecimal = (x * 3.785).toBigDecimal(),
                    "milliliter" to fun(x: Double): BigDecimal = (x * 3785).toBigDecimal(),
                    "pint" to fun(x: Double): BigDecimal = (x * 8).toBigDecimal()
                ),
                "pint" to mapOf(
                    "pint" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "liter" to fun(x: Double): BigDecimal = (x / 2.113).toBigDecimal(),
                    "milliliter" to fun(x: Double): BigDecimal = (x * 473.2).toBigDecimal(),
                    "gallon" to fun(x: Double): BigDecimal = (x / 8).toBigDecimal()
                ),
            ),
            "money" to mapOf(
                "USD" to mapOf(
                    "USD" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "EUR" to fun(x: Double): BigDecimal = (x * 1.0279).toBigDecimal(),
                    "BYN" to fun(x: Double): BigDecimal = (x * 2.51).toBigDecimal(),
                    "GBP" to fun(x: Double): BigDecimal = (x * 0.8868).toBigDecimal(),
                ),
                "EUR" to mapOf(
                    "EUR" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "USD" to fun(x: Double): BigDecimal = (x * 0.9728).toBigDecimal(),
                    "BYN" to fun(x: Double): BigDecimal = (x * 2.4391).toBigDecimal(),
                    "GBP" to fun(x: Double): BigDecimal = (x * 0.8627).toBigDecimal(),
                ),
                "GBP" to mapOf(
                    "GBP" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "USD" to fun(x: Double): BigDecimal = (x * 1.1277).toBigDecimal(),
                    "BYN" to fun(x: Double): BigDecimal = (x * 2.8274).toBigDecimal(),
                    "EUR" to fun(x: Double): BigDecimal = (x * 1.1592).toBigDecimal(),
                ),
                "BYN" to mapOf(
                    "BYN" to fun(x: Double): BigDecimal = x.toBigDecimal(),
                    "USD" to fun(x: Double): BigDecimal = (x * 0.3989).toBigDecimal(),
                    "GBP" to fun(x: Double): BigDecimal = (x * 0.3537).toBigDecimal(),
                    "EUR" to fun(x: Double): BigDecimal = (x * 0.41).toBigDecimal(),
                ),
            )
        )

        private fun convertFromMap(
            type: String,
            from: String,
            to: String,
            number: Double
        ): String {
            var answer = unitsMap?.get(type.lowercase())?.get(from)?.get(to)?.invoke(number)
            return answer?.toPlainString()!!
        }


        fun convert(type: String, from: String, to: String, number: String): String {

            if (number.equals(""))
                return ""

            val numb = number.toDouble()
            val ans = convertFromMap(type, from, to, numb)

            return ans.toString()

        }

        fun period(number: String): String{
            var answer: String = number
            
            if (!number.contains("."))
                return number

            if (number.endsWith(".0") || number.endsWith(".")){
                answer = number.toDouble().toInt().toString()

                return answer
            }

            val pattern: Regex = Regex("")

            // доделать регулярку
            return answer
        }

//        var spinnerSetOnClickListener = object : AdapterView.OnItemSelectedListener {
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
    }

//    fun callConverter(binding: DataBindingComponent, metric_type: String) {
//        val type: String = metric_type
//        val from: String = binding.spinnerFrom.selectedItem.toString()
//
//        val to: String = binding.spinnerTo.selectedItem.toString()
//        val number: String = binding.editText1.text.toString()
//        var answer = convert(type, from, to, number)
//
//        var response = answer
//        if (answer != "")
//            response = period(answer)
//
//        binding.editText2.setText(response)
////        binding.editText2.setText()
//    }
}