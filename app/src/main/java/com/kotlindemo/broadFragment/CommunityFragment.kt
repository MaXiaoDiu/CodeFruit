package com.kotlindemo.broadFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlindemo.R

/**
 * Created by Cyy513 on 2018/5/10.
 */
class CommunityFragment :Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_community,null)
        return view
    }
}