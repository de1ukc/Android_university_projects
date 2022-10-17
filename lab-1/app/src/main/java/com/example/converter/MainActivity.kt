package com.example.converter

import  android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.converter.adapters.ViewPagerAdapter
import com.example.converter.databinding.ActivityMainBinding
import com.example.converter.models.MetricsViewModel
import com.example.converter.services.services
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainBinding
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    private val viewModel: MetricsViewModel by viewModels()

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

    }
}



