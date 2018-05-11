package com.kotlindemo.View

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.*
import android.view.Gravity.CENTER
import android.widget.RelativeLayout
import com.kotlindemo.Adapter.MainViewPagerAdapter

import com.kotlindemo.R
import com.kotlindemo.R.id.fl_content
import android.widget.ImageView
import android.widget.LinearLayout
import com.kotlindemo.R.id.ral_indicator
import com.kotlindemo.View.MyViewPager.SelectItem
import java.nio.file.Files.size
import java.nio.file.Files.size





/**
 * Created by Cyy513 on 2018/5/10.
 */

class SliderViewPager : RelativeLayout, SelectItem {

    val img1 = R.drawable.val_white
    val img2 = R.drawable.val_dark
    val dotViewLists = ArrayList<ImageView>()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {

        LayoutInflater.from(context).inflate(R.layout.myviewpager, this, true)
        for (i in 0 until getViewPager().getimagesSize()) {
            val imageView = ImageView(context)
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            params.gravity = Gravity.CENTER
            //为小圆点左右添加间距
            params.leftMargin = 10
            params.rightMargin = 10
            //手动给小圆点一个大小
            params.height = 20
            params.width = 20
            if (i == 0) {
                imageView.setBackgroundResource(img1)
            } else {
                imageView.setBackgroundResource(img2)
            }
            //为LinearLayout添加ImageView
            findViewById<LinearLayout>(R.id.lay_point).addView(imageView, params)
            dotViewLists.add(imageView)
        }
        getViewPager().SetSelectItem(this)

    }

    fun getViewPager() : MyViewPager
    {
        return findViewById(R.id.fl_content)
    }

    override fun position(position: Int) {

        for (i in 0 until dotViewLists.size) {
            //选中的页面改变小圆点为选中状态，反之为未选中
            if ((position-1)%dotViewLists.size===i) {
                (dotViewLists.get(i) as View).setBackgroundResource(img1)
            } else {
                (dotViewLists.get(i) as View).setBackgroundResource(img2)
            }
        }
    }
}
