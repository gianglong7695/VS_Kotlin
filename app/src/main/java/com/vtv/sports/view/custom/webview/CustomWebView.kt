package com.vtv.sports.view.custom.webview

import android.content.Context
import android.graphics.Canvas
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Created by Giang Long on 05/01/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class CustomWebView(context: Context?) : WebView(context) {
    //    lateinit var defaultHttpHeaders: Map<String, String>
    var webViewListener: WebViewListener? = null



    init {
        initSetting()
    }

    fun setListener(listner: WebViewListener) {
        this.webViewListener = listner
    }

    fun initSetting() {
//        defaultHttpHeaders = HashMap()
        CookieManager.getInstance().setAcceptCookie(true)

//        settings.defaultZoom = WebSettings.ZoomDensity.FAR
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.domStorageEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = false
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.databaseEnabled = true
        settings.allowFileAccess = true
        settings.allowFileAccessFromFileURLs = true
        settings.allowUniversalAccessFromFileURLs = true
    }


    override fun onDraw(canvas: Canvas?) {
        if (webViewListener != null) webViewListener?.getLoadingProgress(progress)
        super.onDraw(canvas)
    }

}