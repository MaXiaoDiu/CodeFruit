package com.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.view.View.OnClickListener
import com.kotlindemo.Adapter.MainViewPagerAdapter
import com.kotlindemo.Fragment.BroadCastFragment
import com.kotlindemo.Fragment.HomeFragment
import com.kotlindemo.Fragment.TopicFragment
import com.kotlindemo.Fragment.UserFragemnt
import com.kotlindemo.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class MainActivity : AppCompatActivity(),OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        toolbar.img_more.setOnClickListener(this)

    }

    private fun init() {

        nav_view.itemIconTintList = null
        nav_bottom.itemIconTintList = null
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(HomeFragment())
        fragmentList.add(TopicFragment())
        fragmentList.add(BroadCastFragment())
        fragmentList.add(UserFragemnt())
        fl_content.adapter = MainViewPagerAdapter(supportFragmentManager,fragmentList)
        fl_content.currentItem = 0
    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.img_more->
                drawer_layout.openDrawer(GravityCompat.START)
        }
    }
}
