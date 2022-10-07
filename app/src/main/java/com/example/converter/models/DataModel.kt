package com.example.converter.models

import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val editTextMessage: MutableLiveData<EditText> by lazy {
        MutableLiveData<EditText>()
    }

    val spinnerFromMessage: MutableLiveData<Spinner> by lazy {
        MutableLiveData<Spinner>()
    }
    val spinnerToMessage: MutableLiveData<Spinner> by lazy {
        MutableLiveData<Spinner>()
    }
}