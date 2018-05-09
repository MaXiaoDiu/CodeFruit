package com.kotlindemo.View

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import com.kotlindemo.Adapter.MyPagerAdapter
import com.kotlindemo.MainActivity
import com.kotlindemo.R
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
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
    constructor(context: Context) : super(context){
        init()
    }
    private fun init() {
        val mImageViewOne = ImageView(context)
        mImageViewOne.setBackgroundResource(R.mipmap.pic_one)
        val mImageViewTwo = ImageView(context)
        mImageViewTwo.setBackgroundResource(R.mipmap.pic_two)
        val mImageViewThree = ImageView(context)
        mImageViewThree.setBackgroundResource(R.mipmap.pic_three)
        imageviews.add(mImageViewOne)
        imageviews.add(mImageViewTwo)
        imageviews.add(mImageViewThree)
        this.adapter = MyPagerAdapter(imageviews,context)
        mDisposable = Observable.interval(5, 5, TimeUnit.SECONDS)  // 5s的延迟，5s的循环时间
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    if (mCurrentPage==imageviews.size)
                    {
                        mCurrentPage = 0
                    }
                    currentItem = mCurrentPage
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

            mCurrentPage = position +1
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }
}