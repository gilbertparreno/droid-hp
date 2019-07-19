package com.droid.hp.screen.container

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.droid.hp.screen.tab.TabFragment

class TabPagerAdapter(fragmentManager: FragmentManager, tabPagerListener: TabPagerListener?) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = mutableListOf<Fragment>(
            TabFragment(tabPagerListener),
            TabFragment(tabPagerListener))

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as TabFragment
        fragments[position] = fragment
        return fragment
    }

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
