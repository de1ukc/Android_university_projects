package com.example.converter.services

import android.widget.EditText

class services {


   companion object{

       fun blockKeyboardInput(editText: EditText){
            editText.setShowSoftInputOnFocus(false)
        }

   }
}