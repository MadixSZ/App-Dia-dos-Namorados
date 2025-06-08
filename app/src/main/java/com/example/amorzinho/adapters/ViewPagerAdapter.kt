package com.example.amorzinho.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.amorzinho.fragments.GalleryFragment
import com.example.amorzinho.fragments.LoveFragment
import com.example.amorzinho.fragments.QuizFragment
import com.example.amorzinho.fragments.TimelineFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuizFragment()
            1 -> TimelineFragment()
            2 -> GalleryFragment()
            3 -> LoveFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        } as Fragment
    }
}