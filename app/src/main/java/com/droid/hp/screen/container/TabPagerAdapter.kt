package com.droid.hp.screen.container

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.droid.hp.screen.tab.TabFragment
import java.text.SimpleDateFormat

class TabPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = mutableListOf<Fragment>(
            TabFragment(),
            TabFragment())

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        TAB1 -> "Open Jobs"
        else -> "Closed Jobs"
    }

    companion object {
        val PAGE_COUNT = 2
        val TAB1 = 0
        val TAB2 = 1
    }
}
