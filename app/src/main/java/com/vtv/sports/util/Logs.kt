package com.vtv.sports.util

import android.util.Log

/**
 * Created by Giang Long on 12/21/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class Logs {
    companion object {
        private val isDebug = true

        fun d(msg: String) {
            if (isDebug){
                var stackTraceElement: StackTraceElement = Throwable().stackTrace[1]
                Log.d("$stackTraceElement/${stackTraceElement.methodName}/${stackTraceElement.lineNumber}", msg)
            }
        }

        fun e(msg: String) {
            if (isDebug){
                var stackTraceElement: StackTraceElement = Throwable().stackTrace[1]
                Log.e("$stackTraceElement/${stackTraceElement.methodName}/${stackTraceElement.lineNumber}", msg)
            }
        }

    }
}