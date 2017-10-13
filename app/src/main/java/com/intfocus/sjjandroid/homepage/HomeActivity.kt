package com.intfocus.sjjandroid.homepage

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.intfocus.sjjandroid.R
import com.intfoucs.sjjandroid.BaseActivity

/**
 * Created by liuruilin on 2017/9/10.
 */
class HomeActivity: BaseActivity() {
    private var mWebFrameLayout: FrameLayout? = null
    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mWebFrameLayout = findViewById(R.id.browser) as FrameLayout
        mWebView = WebView(applicationContext)
        mWebFrameLayout!!.addView(mWebView, 0)
        initWebView()
        mWebView!!.setWebViewClient(object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return true
            }
        })
        mWebView!!.loadUrl("http://yonghui-dev.idata.mobi/websites/shujujia/html/index/index.html")

    }

    private fun initWebView() {
        //声明WebSettings子类
        val webSettings = mWebView!!.settings

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true

        //支持插件
        //        webSettings.setPluginsEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false) //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = false //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false //隐藏原生的缩放控件

        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存
        webSettings.allowFileAccess = true //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8"//设置编码格式

    }
}