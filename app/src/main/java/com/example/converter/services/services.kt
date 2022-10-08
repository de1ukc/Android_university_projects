package com.example.converter.services

import android.widget.EditText
import java.math.BigDecimal

class services {


    companion object {

        fun blockKeyboardInput(editText: EditText) {
            editText.setShowSoftInputOnFocus(false)
        }


        val unitsMap = mapOf(
            "Length" to mapOf(
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

        private fun convert_from_map(type: String,from: String, to: String, number: Double): String{
            var answer = unitsMap?.get(type.lowercase())?.get(from)?.get(to)?.invoke(number)
            return answer.toString()
        }



        fun convert(type: String, from: String, to:String, number: String): String{

            if (number.equals(""))
                return ""

            val numb = number.toDouble()
            val ans = convert_from_map(type, from, to, numb)

            return ans.toString()

        }
    }


}