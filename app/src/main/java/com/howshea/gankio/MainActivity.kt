package com.howshea.gankio

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.howshea.baseutils.setStatusBarTransAndDark
import com.howshea.gankio.utils.disableShiftMode
import com.howshea.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBarTransAndDark()
        bottom_nav.disableShiftMode()
        view_pager.adapter = MainPagerAdapter(supportFragmentManager)
    }

    inner class MainPagerAdapter(fm:FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return HomeFragment()
        }
        override fun getCount() = 4
    }
}
