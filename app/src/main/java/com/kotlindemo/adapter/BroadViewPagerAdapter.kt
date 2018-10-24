package com.kotlindemo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Cyy513 on 2018/5/10.
 */
class BroadViewPagerAdapter : FragmentStatePagerAdapter {

    private val fragments: ArrayList<Fragment>

    constructor(fragmentManager: FragmentManager, fragments: ArrayList<Fragment>) : super(fragmentManager) {
        this.fragments = fragments
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}