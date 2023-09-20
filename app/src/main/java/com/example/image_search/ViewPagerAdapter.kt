package com.example.image_search

import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment)

{
    override fun getItemCount(): Int = 2

    override fun createFragment(position: kotlin.Int): androidx.fragment.app.Fragment {
        return when(position){
            0 -> Search_Fragment()
            else -> Box_Fragment()

        }
    }
}