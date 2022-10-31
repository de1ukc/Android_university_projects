package com.example.calculator.services

import android.widget.EditText

class service {

    companion object {
        fun blockKeyboardInput(editText: EditText) {
            editText.setShowSoftInputOnFocus(false)
        }
    }
}