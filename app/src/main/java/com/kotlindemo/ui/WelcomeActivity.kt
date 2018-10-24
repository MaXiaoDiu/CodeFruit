package com.kotlindemo.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlindemo.activity.MainActivity
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
        StartThread()
    }

    private fun StartThread() {

        var mSubscription: Subscription? = null // Subscription 对象，用于取消订阅关系，防止内存泄露
        var count:Long = 4
        Flowable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .onBackpressureBuffer()//加上背压策略
                .take(count+1) //设置循环次数
                .map{ aLong ->
                    count- aLong
                }
                .observeOn(AndroidSchedulers.mainThread())//操作UI主要在UI线程
                .subscribe(object : Subscriber<Long> {
                    override fun onSubscribe(s: Subscription?) {
                        mSubscription = s
                        s?.request(Long.MAX_VALUE)//设置请求事件的数量，重要，必须调用
                    }
                    override fun onNext(aLong: Long?) {
                    }
                    override fun onComplete() {
                        val intent = Intent()
                        intent.setClass(baseContext, MainActivity::class.java)
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
