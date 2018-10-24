package com.kotlindemo.view.slider

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import com.kotlindemo.adapter.MyPagerAdapter

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Cyy513 on 2018/5/9.
 */
class MyViewPager : ViewPager
{
    var mCurrentPage: Int = 1
    var mDisposable: Disposable? = null

    var images : IntArray? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


     fun start() {

        this.adapter = MyPagerAdapter(images,context)
        mDisposable = Observable.interval(3, 3, TimeUnit.SECONDS)  // 3s的延迟，5s的循环时间
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    currentItem = mCurrentPage//设置显示的页面

                }

        addOnPageChangeListener(mOnPageChangeListener)
    }



    private val mOnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


        }

        override fun onPageSelected(position: Int) {

            mCurrentPage = position +1
            selectItem!!.position(mCurrentPage)

        }
        override fun onPageScrollStateChanged(state: Int) {

            when(state)
            {
                ViewPager.SCROLL_STATE_DRAGGING->
                {
                    //停止之前的计时,重新开始计时
                    mDisposable!!.dispose()
                    mDisposable = Observable.interval(3, 3, TimeUnit.SECONDS)  // 5s的延迟，5s的循环时间
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                currentItem = mCurrentPage
                            }
                }
            }
        }
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