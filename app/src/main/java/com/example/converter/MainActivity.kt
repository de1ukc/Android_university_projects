package com.example.converter

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TableLayout
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.converter.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.concurrent.Exchanger
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainBinding
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    private var dataModel: DataModel by viewModels() {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        viewPager = bindingClass.exchanger!!
        tabLayout = bindingClass.tabLayoutType!!

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab,index ->
            tab.text = when(index){
                0 -> getString(R.string.money)
                1 -> getString(R.string.length)
                2 -> getString(R.string.volume)
                else -> {throw Resources.NotFoundException("Caption Not Found lol")}
            }
        }.attach()

//        spinnerFrom.

    }

    fun numButtonsOnClick(view: View) {

//        val value1 = editTextValue1?.text.toString()
//        var inputTex = editTextValue1?.text.toString()
//        val inputEditText = editTextValue1

//        when (view.id) {
//            bindingClass.btnZero?.id -> inputTex += getString(R.string.zero)
//            bindingClass.btn1?.id -> inputTex += getString(R.string.one)
//            bindingClass.btn2?.id -> inputTex += getString(R.string.two)
//            bindingClass.btn3?.id -> inputTex += getString(R.string.three)
//            bindingClass.btn4?.id -> inputTex += getString(R.string.four)
//            bindingClass.btn5?.id -> inputTex += getString(R.string.five)
//            bindingClass.btn6?.id -> inputTex += getString(R.string.six)
//            bindingClass.btn7?.id -> inputTex += getString(R.string.seven)
//            bindingClass.btn8?.id -> inputTex += getString(R.string.eight)
//            bindingClass.btn9?.id -> inputTex += getString(R.string.nine)
//            bindingClass.btnDot?.id -> {
//                if (!inputText.containt("."))
//                    inputText += getString(R.string.dot)
//            }
//        }


//        inputEditText?.setText(inputTex)

    }

    fun clearButtonsOnClick(view: View) {
//        val editTextValue1 = bindingClass.etVal1
//
//        when(view.id){
//            bindingClass.btnAc?.id -> editTextValue1?.setText("")
//        }

    }
}


