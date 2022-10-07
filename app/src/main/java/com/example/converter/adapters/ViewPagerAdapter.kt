package com.example.converter.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.converter.ui.LengthFragment
import com.example.converter.ui.MoneyFragment
import com.example.converter.ui.VolumeFragment

//class ViewPagerAdapter(val items: ArrayList<Fragment>, activity: AppCompatActivity)
//    :FragmentStateAdapter(activity) {
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//
//    override fun createFragment(position: Int): Fragment {
//        return items[position]
//    }
//
//}

class ViewPagerAdapter: FragmentStateAdapter{

    constructor(activity: AppCompatActivity
    ) : super(activity){

    }


    override fun getItemCount(): Int {
        return items.size
    }


    override fun createFragment(position: Int): Fragment {
        return items[position]
    }

    val items: ArrayList<Fragment> = arrayListOf(
        MoneyFragment(),
        LengthFragment(),
        VolumeFragment()
    )

}