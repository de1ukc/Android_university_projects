package com.example.calculator.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SymbViewModel : ViewModel() {
    val stringMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val rotateMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}