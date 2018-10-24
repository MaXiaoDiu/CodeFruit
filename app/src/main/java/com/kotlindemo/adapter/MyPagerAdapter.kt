package com.kotlindemo.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso


/**
 * Created by Cyy513 on 2018/5/9.
 */
class MyPagerAdapter : PagerAdapter{

    val imageviews: IntArray ?
    val  context :Context
    constructor(imageviews: IntArray?, context: Context) : super() {
        this.imageviews = imageviews
        this.context = context
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return Int.MAX_VALUE

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //用ImageView装这个图
        var imageView = ImageView(context)
        imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        Picasso.with(context).load(imageviews!!.get(position % imageviews.size)).fit().into(imageView)
        container.addView(imageView)
        return imageView
    }
}