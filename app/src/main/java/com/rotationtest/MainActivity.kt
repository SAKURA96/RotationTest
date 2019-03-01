package com.rotationtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar()
        //默认加载第一个fragment
        changeIndex(0)
    }

    private val fragments = listOf<Fragment>(AFragment(), BFragment(), AFragment(), AFragment(), AFragment())

    private fun bottomBar() {
        for (i in 0 until bottom_bar.childCount) {
            bottom_bar.getChildAt(i).setOnClickListener { changeIndex(i) }
        }
    }

    private fun changeIndex(index: Int) {
        for (i in 0 until bottom_bar.childCount) {
            val view = bottom_bar.getChildAt(i)
            if (i == index) {
                //选中 禁用,此tab不可再被点击
                setEnable(view, false)
            } else {
                //没选中 enable = true，监听点击
                setEnable(view, true)
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.main_content, fragments[index]).commit()
    }

    private fun setEnable(view: View, isEnable: Boolean) {
        view.isEnabled = isEnable
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                view.getChildAt(i).isEnabled = isEnable
            }

        }
    }
}