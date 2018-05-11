package com.kotlindemo.Adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import java.nio.file.Files.size
import java.nio.file.Files.size
import android.support.v4.view.ViewPager









/**
 * Created by Cyy513 on 2018/5/9.
 */
class MyPagerAdapter : PagerAdapter {

    val imageviews: ArrayList<ImageView>
    val  context :Context
    constructor(imageviews: ArrayList<ImageView>,context: Context) : super() {
        this.imageviews = imageviews
        this.context = context
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return 1000

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(imageviews.get(position % imageviews.size))
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //用ImageView装这个图
        container.addView(imageviews.get(position % imageviews.size))
        return imageviews.get(position % imageviews.size)
    }
}