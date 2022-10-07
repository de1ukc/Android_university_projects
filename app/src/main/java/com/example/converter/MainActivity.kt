package com.example.converter

import  android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.Display
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.converter.adapters.ViewPagerAdapter
import com.example.converter.databinding.ActivityMainBinding
import com.example.converter.models.DataModel
import com.example.converter.services.services
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainBinding
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    private var dataModel: DataModel by viewModels()
    lateinit var editText: EditText
    lateinit var spinnerFrom: Spinner
    lateinit var spinnerTo: Spinner



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

        dataModel.editTextMessage.observe(this) {
            editText = it
        }

        dataModel.spinnerFromMessage.observe(this){
            spinnerFrom = it
        }
        dataModel.spinnerToMessage.observe(this){
            spinnerTo = it
        }


    }

    fun numButtonsOnClick(view: View) {


        when (view.id) {
            bindingClass.btnZero?.id -> updateString(getString(R.string.zero))
            bindingClass.btn1?.id ->  updateString(getString(R.string.one))
            bindingClass.btn2?.id -> updateString(getString(R.string.two))
            bindingClass.btn3?.id -> updateString(getString(R.string.three))
            bindingClass.btn4?.id -> updateString(getString(R.string.four))
            bindingClass.btn5?.id -> updateString(getString(R.string.five))
            bindingClass.btn6?.id -> updateString(getString(R.string.six))
            bindingClass.btn7?.id -> updateString(getString(R.string.seven))
            bindingClass.btn8?.id -> updateString(getString(R.string.eight))
            bindingClass.btn9?.id -> updateString(getString(R.string.nine))
            bindingClass.btnDot?.id -> {
               val txt =  editText.text.toString()
                if (!txt.contains("."))
                    updateString(getString(R.string.dot))
            }

            bindingClass.btnAc?.id -> editText?.setText("")
            bindingClass.btnClear?.id ->{
                val cursorPose: Int = editText.selectionStart
                val textLen: Int = editText.text.length

                if (cursorPose != 0 && textLen != 0){
                    val selection: SpannableStringBuilder = editText.text as SpannableStringBuilder
                    selection.replace(cursorPose - 1, cursorPose, "")
                    editText.setText(selection)
                    editText.setSelection(cursorPose - 1)
                }
            }
        }

        val a = editText.text.equals("")
        val b = editText.text.length
        if (!editText.text.equals(""))
        {Convert()}
    }

    fun updateString(strToAdd: String){
        var oldStr: String = editText?.text.toString()
        val cursorPos: Int = editText?.selectionStart
        var leftString: String = oldStr.substring(0, cursorPos)
        var rightStr: String = oldStr.substring(cursorPos)

        if (editText.text.toString().equals("")){
            editText.setText(strToAdd)
        }else{
            editText.setText(String.format("%s%s%s", leftString,strToAdd, rightStr))
        }
        editText.setSelection(cursorPos + 1)
    }

    fun Convert(){

        val type: String = tabLayout.getTabAt(tabLayout.selectedTabPosition)?.text.toString()
        val from: String = spinnerFrom.selectedItem.toString()

        val to: String = spinnerTo.selectedItem.toString()
        val number:Double = editText.text.toString().toDouble()
        val ans = services.converter(type, from, to, number)

        Toast.makeText(this@MainActivity,"ANS " +
                ans, Toast.LENGTH_SHORT).show()
    }


}

private operator fun Any.setValue(mainActivity: MainActivity, property: KProperty<*>, dataModel: DataModel) {

}




