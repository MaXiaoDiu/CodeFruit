package com.kotlindemo.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import com.kotlindemo.Adapter.MainViewPagerAdapter
import com.kotlindemo.Fragment.BroadCastFragment
import com.kotlindemo.Fragment.HomeFragment
import com.kotlindemo.Fragment.TopicFragment
import com.kotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class MainActivity : FragmentActivity(),OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init() {

        nav_view.itemIconTintList = null
        nav_bottom.itemIconTintList = null
        nav_bottom.setOnNavigationItemSelectedListener(this)
        toolbar.ral_more.setOnClickListener(this)
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(HomeFragment())
        fragmentList.add(TopicFragment())
        fragmentList.add(BroadCastFragment())
        fl_content_ral.adapter = MainViewPagerAdapter(supportFragmentManager,fragmentList)
        fl_content_ral.currentItem = 0
        fl_content_ral.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {

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
            R.id.ral_more ->
                drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.menu_main ->
                fl_content_ral.currentItem = 0
            R.id.menu_topic ->
                fl_content_ral.currentItem = 1
            R.id.menu_voice ->
                fl_content_ral.currentItem = 2
        }
        return true

    }
}
