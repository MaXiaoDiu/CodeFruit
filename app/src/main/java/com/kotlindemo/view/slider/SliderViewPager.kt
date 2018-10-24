package com.kotlindemo.view.slider

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.RelativeLayout

import com.kotlindemo.R
import android.widget.ImageView
import android.widget.LinearLayout
import com.kotlindemo.view.slider.MyViewPager.SelectItem
import kotlinx.android.synthetic.main.myviewpager.view.*


/**
 * Created by Cyy513 on 2018/5/10.
 */

class SliderViewPager : RelativeLayout, SelectItem {

    private val img1 = R.drawable.val_white
    private val img2 = R.drawable.val_dark
    private val dotViewLists = ArrayList<ImageView>()

    var mTitles : Array<String>?=null
    var mImages : IntArray? = null

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    fun startSlider() {

        LayoutInflater.from(context).inflate(R.layout.myviewpager, this, true)
        getViewPager().images = mImages
        getViewPager().start()
        for (i in 0 until mImages!!.size) {
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
            lay_point.addView(imageView, params)
            dotViewLists.add(imageView)
        }
        vt_title.text = mTitles!![0]
        getViewPager().SetSelectItem(this)
    }

    fun getViewPager() : MyViewPager
    {
        return fl_content
    }

    override fun position(position: Int) {

        for (i in 0 until dotViewLists.size) {

            //选中的页面改变小圆点为选中状态，反之为未选中
            vt_title.text = mTitles!![(position) % dotViewLists.size]

            if ((position-1) % dotViewLists.size===i) {

                (dotViewLists.get(i) as View).setBackgroundResource(img1)

            } else {

                (dotViewLists.get(i) as View).setBackgroundResource(img2)
            }
        }
    }
}
