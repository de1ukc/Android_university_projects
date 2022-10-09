package com.example.converter.models

import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.tabs.TabLayout

open class AllFragmentsViewModel : ViewModel() {
    val StringMessageMoney: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val StringMessageLength: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val StringMessageVolume: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val editTextMessage: MutableLiveData<EditText> by lazy {
        MutableLiveData<EditText>()
    }
    val tabLayoutMessage: MutableLiveData<TabLayout> by lazy {
        MutableLiveData<TabLayout>()
    }
}