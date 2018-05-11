package com.kotlindemo.Fragment

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlindemo.Adapter.BroadViewPagerAdapter
import com.kotlindemo.BroadFragment.CommunityFragment
import com.kotlindemo.BroadFragment.FruitPicFragment
import com.kotlindemo.BroadFragment.LeaveFragment
import com.kotlindemo.BroadFragment.NewsFragment
import com.kotlindemo.R
import kotlinx.android.synthetic.main.fragment_broadcast.*
import kotlinx.android.synthetic.main.fragment_broadcast.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.view.ViewTreeObserver
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import java.lang.reflect.Field
import android.util.TypedValue
import java.io.File


/**
 * Created by Cyy513 on 2018/5/7.
 */

class BroadCastFragment : Fragment(),ViewPager.OnPageChangeListener
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_broadcast,null)
        init(view)
        return view
    }

    /**
     * 初始化
     */
    private fun init(view : View) {

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(FruitPicFragment())
        fragmentList.add(NewsFragment())
        fragmentList.add(CommunityFragment())
        fragmentList.add(LeaveFragment())
        view.vp_pager.adapter = BroadViewPagerAdapter(getChildFragmentManager(),fragmentList)
        view.tl_tab.setupWithViewPager(vp_pager)
        view.tl_tab.addTab(view.tl_tab.newTab().setText("果图"))
        view.tl_tab.addTab(view.tl_tab.newTab().setText("新闻"))
        view.tl_tab.addTab(view.tl_tab.newTab().setText("社区"))
        view.tl_tab.addTab(view.tl_tab.newTab().setText("闲置"))
        view.vp_pager.addOnPageChangeListener(this)
    }


    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {

        when(position)
        {
            0->view!!.tl_tab.getTabAt(0)!!.select()
            1-> view!!.tl_tab.getTabAt(1)!!.select()
            2-> view!!.tl_tab.getTabAt(2)!!.select()
            3-> view!!.tl_tab.getTabAt(3)!!.select()
        }
    }
}