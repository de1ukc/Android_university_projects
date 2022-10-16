package com.example.converter.models

import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.tabs.TabLayout

open class MetricsViewModel : ViewModel() {
    val stringMoneyMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val stringVolumeMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val stringLengthMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val tabLayoutMessage: MutableLiveData<TabLayout> by lazy {
        MutableLiveData<TabLayout>()
    }
}