package com.vtv.sports.view.custom.webview

/**
 * Created by Giang Long on 1/6/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
interface WebViewListener {
    fun onPrepare()
    fun onCompleted()
    fun onError()
    fun getLoadingProgress(percent: Int)
}