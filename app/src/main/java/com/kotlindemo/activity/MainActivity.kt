package com.kotlindemo.activity

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.View.OnClickListener
import com.kotlindemo.adapter.MainViewPagerAdapter
import com.kotlindemo.fragment.BroadCastFragment
import com.kotlindemo.fragment.HomeFragment
import com.kotlindemo.fragment.TopicFragment
import com.kotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import android.view.*


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MainActivity : FragmentActivity(),OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        toolbar.inflateMenu(R.menu.tool_menu)
        toolbar.title= resources.getString(R.string.app_name)
        toolbar.navigationIcon = resources.getDrawable(R.mipmap.user)
        toolbar.setNavigationOnClickListener { drawer_layout.openDrawer(Gravity.START) }
        nav_view.itemIconTintList = null
        nav_bottom.itemIconTintList = null
        nav_bottom.setOnNavigationItemSelectedListener(this)
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


    override fun onMenuItemClick(item: MenuItem?): Boolean {

        when(item?.itemId)
        {


        }
        return true
    }
}

