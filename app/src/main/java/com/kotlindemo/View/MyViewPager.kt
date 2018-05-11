package com.kotlindemo.View

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.kotlindemo.Adapter.MyPagerAdapter
import com.kotlindemo.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import io.reactivex.disposables.Disposable


/**
 * Created by Cyy513 on 2018/5/9.
 */
class MyViewPager : ViewPager
{
    var mCurrentPage: Int = 1
    var imageviews=ArrayList<ImageView>()
    var mDisposable: Disposable? = null

    private val images = intArrayOf(R.mipmap.pic_one, R.mipmap.pic_two, R.mipmap.pic_three)
    constructor(context: Context) : super(context){
        init()
    }
    private fun init() {

        for (i in 0 until 2000) {
            val imageView = ImageView(context)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.setImageResource(images[i%images.size])
            imageView.setOnClickListener(object : OnClickListener{
                override fun onClick(v: View?) {
                    Toast.makeText(context,"点击了第"+(i%3+1)+"张图片",Toast.LENGTH_SHORT).show()
                }
            })
            imageviews.add(imageView)
        }
        this.adapter = MyPagerAdapter(imageviews,context)
        mDisposable = Observable.interval(5, 5, TimeUnit.SECONDS)  // 5s的延迟，5s的循环时间
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    currentItem = mCurrentPage % imageviews.size
                }
        addOnPageChangeListener(mOnPageChangeListener)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    {
        init()
    }

    private val mOnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


        }

        override fun onPageSelected(position: Int) {

            if (mCurrentPage==1)
            {
                mCurrentPage = 0
            }
            if (mCurrentPage==imageviews.size-1)
            {
                mCurrentPage =-1
            }
            mCurrentPage = position +1

           if (selectItem!=null)
           {
               selectItem!!.position(mCurrentPage)
           }
        }
        override fun onPageScrollStateChanged(state: Int) {

            when(state)
            {
                ViewPager.SCROLL_STATE_DRAGGING->
                {
                    //停止之前的计时,重新开始计时
                    mDisposable!!.dispose()
                    mDisposable = Observable.interval(5, 5, TimeUnit.SECONDS)  // 5s的延迟，5s的循环时间
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                currentItem = mCurrentPage % imageviews.size
                            }
                }
            }
        }
    }

    fun getimagesSize() :Int
    {
        return images.size
    }

    private var selectItem : SelectItem? = null

    fun SetSelectItem(selectItem : SelectItem)
    {
        this.selectItem = selectItem
    }

    interface SelectItem
    {
        fun position(position : Int)
    }
}