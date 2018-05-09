package com.kotlindemo.Adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout



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
        return imageviews.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //用ImageView装这个图片
        val view = imageviews.get(position)
        container.addView(view)
        return view
    }
}