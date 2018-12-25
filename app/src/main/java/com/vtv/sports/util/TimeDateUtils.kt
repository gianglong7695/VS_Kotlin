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
                var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                var date: Date = dateFormat.parse(s)
                val prettyTime = PrettyTime(Locale("vi"))
                result = prettyTime.formatDuration(date)
                if (result == "") {
                    result = "vừa xong"
                } else {
                    result += " trước"
                }
                return result
            }
            return result
        }


        fun getScheduleSport(str: String): String {
            val ts = str.split("T".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            val split0 = ts[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val format0 = String.format("%s/%s", split0[2], split0[1])

            val split = ts[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val format = String.format("%sh%s", split[0], split[1])
            return String.format("%s | %s", format0, format)
        }

        fun convertToDuration(time: String): String {
            val split = time.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return String.format("%s", split[0])
        }
    }
}