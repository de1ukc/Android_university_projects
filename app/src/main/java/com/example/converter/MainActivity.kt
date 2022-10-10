package com.example.converter

import  android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.converter.adapters.ViewPagerAdapter
import com.example.converter.databinding.ActivityMainBinding
import com.example.converter.models.AllFragmentsViewModel
import com.example.converter.services.services
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainBinding
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    private val viewModel: AllFragmentsViewModel by viewModels()
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        viewPager = bindingClass.exchanger!!
        tabLayout = bindingClass.tabLayoutType!!

        viewModel.tabLayoutMessage.value = tabLayout

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> getString(R.string.money)
                1 -> getString(R.string.length)
                2 -> getString(R.string.volume)
                else -> {
                    throw Resources.NotFoundException("Caption Not Found lol")
                }
            }
        }.attach()

        viewModel.editTextMessage.observe(this) {
            editText = it
        }

    }

    fun numButtonsOnClick(view: View) {
        var symb: String = ""

        if (editText.text.length <= 12) { // на самом деле количество символов 13

            when (view.id) {
                bindingClass.btnZero?.id -> symb = getString(R.string.zero)
                bindingClass.btn00?.id -> {
                    if (editText.text.length < 12)
                        symb = getString(R.string.doubleZero)
                    else {
                        Toast.makeText(
                            this@MainActivity,
                            "Only 1 symbol can be added ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                bindingClass.btn1?.id -> symb = getString(R.string.one)
                bindingClass.btn2?.id -> symb = getString(R.string.two)
                bindingClass.btn3?.id -> symb = getString(R.string.three)
                bindingClass.btn4?.id -> symb = getString(R.string.four)
                bindingClass.btn5?.id -> symb = getString(R.string.five)
                bindingClass.btn6?.id -> symb = getString(R.string.six)
                bindingClass.btn7?.id -> symb = getString(R.string.seven)
                bindingClass.btn8?.id -> symb = getString(R.string.eight)
                bindingClass.btn9?.id -> symb = getString(R.string.nine)
                bindingClass.btnDot?.id -> {
                    if (!editText.text.contains("."))
                        symb = getString(R.string.dot)
                }
            }
        } else {
            Toast.makeText(this@MainActivity, "Naaah! Too many numbers ", Toast.LENGTH_LONG).show()
        }

        when (view.id) {
            bindingClass.btnAc?.id -> symb = "-1"
            bindingClass.btnClear?.id -> symb = "-0"
        }

        if (symb != "")
            when (bindingClass.tabLayoutType?.selectedTabPosition) {
                services.Companion.TabLayoutPosition.Money.pos ->
                    viewModel.StringMessageMoney.value = symb

                services.Companion.TabLayoutPosition.Length.pos ->
                    viewModel.StringMessageLength.value = symb

                services.Companion.TabLayoutPosition.Volume.pos ->
                    viewModel.StringMessageVolume.value = symb
            }
    }
}



