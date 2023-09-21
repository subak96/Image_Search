package com.example.image_search

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.image_search.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    val likebox: ArrayList<Search_Item> = ArrayList()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val tabbar = listOf("검색" , "내 보관함")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //뷰페이저와 뷰페이저 어댑터 연결
        binding.viewPager.adapter = ViewPagerAdapter(this)

        //탭바 클릭시 이벤트 구성
        TabLayoutMediator(binding.tabLayout , binding.viewPager)
        { tab, position ->
            tab.text = tabbar[position]
        }.attach()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            var currentState = 0
            var currentPosition = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (currentState == ViewPager2.SCROLL_STATE_DRAGGING && currentPosition == position) {
                    if (currentPosition == 0) binding.viewPager.currentItem = 1
                    else if (currentPosition == 1) binding.viewPager.currentItem = 0
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state
                super.onPageScrollStateChanged(state)
            }
        }
        )
    }
    fun Onliked(item: Search_Item){
        if (!likebox.contains(item)){
            likebox.add(item)
        }
    }
    fun Noliked(item: Search_Item){
        likebox.remove(item)
    }
    fun savesearch (context: Context, query: String){
        val save = context.getSharedPreferences(Constants.NAME, Context.MODE_PRIVATE)
        save.edit().putString(Constants.KEY, query).apply()
    }
    fun getsearch (context: Context): String? {
        val get = context.getSharedPreferences(Constants.NAME,Context.MODE_PRIVATE )
        return get.getString(Constants.KEY, null)
    }
}