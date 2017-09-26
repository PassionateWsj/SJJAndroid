package com.intfocus.sjjandroid.homepage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * Created by liuruilin on 2017/3/23.
 */

class DashboardFragmentAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val PAGER_COUNT = 4
//    private var mKpiFragment = KpiFragment()
    private var mMeterFragment = WebViewFragment()
    private var mAnalysisFragment = WebViewFragment()
    private var mAppFragment = WebViewFragment()
    private var mMessageFragment = WebViewFragment()

    override fun getCount(): Int {
        return PAGER_COUNT
    }

    override fun instantiateItem(vg: ViewGroup, position: Int): Any {
        return super.instantiateItem(vg, position)
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            HomePageActivity.PAGE_HOME -> return mMeterFragment
            HomePageActivity.PAGE_STUDY -> return mAnalysisFragment
            HomePageActivity.PAGE_FIND -> return mAppFragment
            HomePageActivity.PAGE_MINE -> return mMessageFragment
        }
        return mMeterFragment
    }
}
