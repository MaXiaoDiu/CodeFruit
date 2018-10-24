package com.kotlindemo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlindemo.R
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by Cyy513 on 2018/5/7.
 */

class HomeFragment : Fragment()
{

    var mImages : IntArray? = intArrayOf(R.mipmap.pic_one, R.mipmap.pic_two, R.mipmap.pic_three,R.mipmap.pic_two)
    var mtitles : Array<String>  = arrayOf("尼康单反","索尼单反","4D对焦,快盛一筹","李易峰哦")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, null)
        init(view)
        return view
    }

    fun init(view : View) {

        view.mySliderViewPager.mImages = mImages
        view.mySliderViewPager.mTitles = mtitles
        view.mySliderViewPager.startSlider()
    }
}