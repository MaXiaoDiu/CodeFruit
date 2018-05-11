package com.kotlindemo.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kotlindemo.Activity.MainActivity
import com.kotlindemo.R
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

class WelcomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        //开启3S跳转到主页面线程
        StartThread();

    }

    private fun StartThread() {

        val timer: TextView = findViewById(R.id.vt_time) //这里的 timer 就是你要控制显示倒计时效果的 TextView
        var mSubscription: Subscription? = null // Subscription 对象，用于取消订阅关系，防止内存泄露
        var count:Long = 4;
        Flowable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .onBackpressureBuffer()//加上背压策略
                .take(count+1) //设置循环次数
                .map{ aLong ->
                    count- aLong
                }
                .observeOn(AndroidSchedulers.mainThread())//操作UI主要在UI线程
                .subscribe(object : Subscriber<Long> {
                    override fun onSubscribe(s: Subscription?) {
                        timer.isEnabled = false//在发送数据的时候设置为不能点击
                        mSubscription = s
                        s?.request(Long.MAX_VALUE)//设置请求事件的数量，重要，必须调用
                    }
                    override fun onNext(aLong: Long?) {
                        timer.text = "${aLong}s" //接受到一条就是会操作一次UI
                    }
                    override fun onComplete() {
                        val intent = Intent()
                        //获取intent对象
                        intent.setClass(baseContext, MainActivity::class.java)
                        // 获取class是使用::反射
                        startActivity(intent)
                        mSubscription?.cancel()//取消订阅，防止内存泄漏
                        finish()
                    }
                    override fun onError(t: Throwable?) {
                        t?.printStackTrace()
                    }
                })
    }
}
