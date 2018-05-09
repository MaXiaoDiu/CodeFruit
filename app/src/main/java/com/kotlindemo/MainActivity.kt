package com.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import com.kotlindemo.Adapter.MainViewPagerAdapter
import com.kotlindemo.Fragment.BroadCastFragment
import com.kotlindemo.Fragment.HomeFragment
import com.kotlindemo.Fragment.TopicFragment
import kotlinx.android.synthetic.main.activity_main.*
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
        fl_content.adapter = MainViewPagerAdapter(supportFragmentManager,fragmentList)
        fl_content.currentItem = 0
        fl_content.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {

                when(position)
                {
                    0-> nav_bottom.menu.getItem(0).setChecked(true)
                    1-> nav_bottom.menu.getItem(1).setChecked(true)
                    2-> nav_bottom.menu.getItem(2).setChecked(true)
                }
            }
        })
    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.img_more->
                drawer_layout.openDrawer(GravityCompat.START)
        }
    }
}
