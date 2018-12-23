package com.vtv.sports.util

import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */

class TimeDateUtils {


    companion object {
        fun convertToDate(s: String): String {
            var result = ""

            if (s != null) {
                var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                var date: Date = dateFormat.parse(s)
                val prettyTime: PrettyTime = PrettyTime(Locale("vi"))
                result = prettyTime.formatDuration(date)
                if (result.equals("")) {
                    result = "vừa xong"
                } else {
                    result += " trước"
                }
                return result
            }
            return result
        }
    }
}