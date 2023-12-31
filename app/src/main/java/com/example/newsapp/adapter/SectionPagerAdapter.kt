package com.example.newsapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.ui.home.AboutAlQuranFragment
import com.example.newsapp.ui.home.AlJazeeraFragment
import com.example.newsapp.ui.home.CommonFragment
import com.example.newsapp.ui.home.WarningForMuslimFragment

class SectionsPagerAdapter(activity: AppCompatActivity):FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        fragment = when(position){
            0-> CommonFragment()
            1-> AlJazeeraFragment()
            2-> AboutAlQuranFragment()
            3-> WarningForMuslimFragment()
            else-> CommonFragment()
        }
        return fragment
    }

}