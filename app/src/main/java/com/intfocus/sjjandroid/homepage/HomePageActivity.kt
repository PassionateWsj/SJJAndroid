package com.intfocus.sjjandroid.homepage

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.intfocus.sjjandroid.R
import com.intfocus.sjjandroid.SJJApplication
import com.intfocus.sjjandroid.view.NoScrollViewPager
import com.intfocus.sjjandroid.view.TabView
import com.intfoucs.sjjandroid.BaseActivity
import org.greenrobot.eventbus.EventBus

class HomePageActivity : BaseActivity(), ViewPager.OnPageChangeListener {
    private var mDashboardFragmentAdapter: DashboardFragmentAdapter? = null
    private var mSharedPreferences: SharedPreferences? = null
    private var mTabView: Array<TabView>? = null
    private var mApp: SJJApplication? = null
    private var mViewPager: NoScrollViewPager? = null
    private var mTabHome: TabView? = null
    private var mTabStudy: TabView? = null
    private var mTabFind: TabView? = null
    private var mTabMine: TabView? = null
    private var mContext: Context? = null
    private var mAppContext: Context? = null

    companion object {
        val PAGE_HOME = 0
        val PAGE_STUDY = 1
        val PAGE_FIND = 2
        val PAGE_MINE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        EventBus.getDefault().register(this)
        mApp = this.application as SJJApplication
        mAppContext = mApp!!.applicationContext
        mContext = this
        mDashboardFragmentAdapter = DashboardFragmentAdapter(supportFragmentManager)
        mViewPager = findViewById(R.id.content_view) as NoScrollViewPager
        initTabView()
        initViewPaper(mDashboardFragmentAdapter!!)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("温馨提示")
                .setMessage(String.format("确认退出【%s】？", resources.getString(R.string.app_name)))
                .setPositiveButton("确认") { _, _ ->
                    finish()
                    System.exit(0)
                }
                .setNegativeButton("取消") { _, _ ->
                    // 返回DashboardActivity
                }
        builder.show()
    }

    private fun initTabView() {
        mTabHome = findViewById(R.id.tab_home) as TabView
        mTabStudy = findViewById(R.id.tab_study) as TabView
        mTabFind = findViewById(R.id.tab_find) as TabView
        mTabMine = findViewById(R.id.tab_mine) as TabView
        mTabView = arrayOf<TabView>(mTabHome!!, mTabStudy!!, mTabFind!!, mTabMine!!)

        mTabHome!!.setOnClickListener(mTabChangeListener)
        mTabStudy!!.setOnClickListener(mTabChangeListener)
        mTabFind!!.setOnClickListener(mTabChangeListener)
        mTabMine!!.setOnClickListener(mTabChangeListener)
    }

    /**
     * @param dashboardFragmentAdapter
     */
    private fun initViewPaper(dashboardFragmentAdapter: DashboardFragmentAdapter) {
        mViewPager!!.adapter = dashboardFragmentAdapter
        mViewPager!!.offscreenPageLimit = 4
        mViewPager!!.currentItem = mSharedPreferences!!.getInt("LastTab", 0)
        mTabView!![mViewPager!!.currentItem].setActive(true)
        mViewPager!!.addOnPageChangeListener(this)
    }

    /*
     * Tab 栏按钮监听事件
     */
    private val mTabChangeListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.tab_home -> mViewPager!!.currentItem = PAGE_HOME
            R.id.tab_study -> mViewPager!!.currentItem = PAGE_STUDY
            R.id.tab_find -> mViewPager!!.currentItem = PAGE_FIND
            R.id.tab_mine -> mViewPager!!.currentItem = PAGE_MINE
            else -> {
            }
        }
        refreshTabView()
    }

    //重写ViewPager页面切换的处理方法
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {}

    override fun onPageScrollStateChanged(state: Int) {
        if (state == 2) {
            when (mViewPager!!.currentItem) {
                PAGE_HOME -> mTabHome!!.setActive(true)

                PAGE_STUDY -> mTabStudy!!.setActive(true)

                PAGE_FIND -> mTabFind!!.setActive(true)

                PAGE_MINE -> mTabMine!!.setActive(true)
            }
        }
        refreshTabView()
        mSharedPreferences!!.edit().putInt("LastTab", mViewPager!!.currentItem).commit()
    }

    /*
     * 刷新 TabView 高亮状态
     */
    private fun refreshTabView() {
        mTabView!![mViewPager!!.currentItem].setActive(true)
        for (i in mTabView!!.indices) {
            if (i != mViewPager!!.currentItem) {
                mTabView!![i].setActive(false)
            }
        }
    }
}
