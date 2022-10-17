package com.example.converter.adapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.converter.ui.BaseFragment

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
    newInstance("Money"),
    newInstance("Length"),
    newInstance("Volume"),
    )

    fun newInstance(convertType: String): BaseFragment {
        val myFragment = BaseFragment()
        val args = Bundle()
        args.putString("Type", convertType)
        myFragment.arguments = args
        return myFragment
    }
}