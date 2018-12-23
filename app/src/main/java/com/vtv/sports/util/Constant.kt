package com.vtv.sports.util

import com.vtv.sports.R

/**
 * Created by Giang Long on 12/21/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class Constant {
    companion object {
        val TAB_SIZE = 4
        val TAB_DEFAULT = 1

        val arrTabIconsDefault = intArrayOf(
            R.drawable.ic_video,
            R.drawable.ic_news,
            R.drawable.ic_schedule,
            R.drawable.ic_chart
        )

        val arrTabIconsSelected = intArrayOf(
            R.drawable.ic_video_selected,
            R.drawable.ic_news_selected,
            R.drawable.ic_schedule_selected,
            R.drawable.ic_chart_selected
        )

        // Menu items
        val MENU_ID_BREAKING_NEWS = 101
        val MENU_ID_SAVING_NEWS = 102
        val MENU_ID_CONTACT = 103
        val MENU_ID_WEBSITE = 104
        val MENU_NAME_BREAKING_NEWS = "Breaking News"
        val MENU_NAME_SAVING_NEWS = "Tin đã lưu"
        val MENU_NAME_CONTACT = "Liên hệ"
        val MENU_NAME_WEBSITE = "Thethao.vtv.vn"

        /* Alert message */
        val NO_CONNECT = "Không có kết nối mạng"
        val TRY_AGAIN = "Xin vui lòng thử lại"


        /* KEY Objects */
        val KEY_ID = "id"

    }
}