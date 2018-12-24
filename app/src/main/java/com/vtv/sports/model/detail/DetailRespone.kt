package com.vtv.sports.model.detail

import com.google.gson.annotations.SerializedName
import com.vtv.sports.model.common.LastUpdated
import com.vtv.sports.model.news.News

/**
 * Created by Giang Long on 12/24/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */


class DetailRespone(
    @SerializedName("LastUpdated")
    val lastUpdated: LastUpdated,
    @SerializedName("LastestNews")
    val lastestNews: List<News>,
    @SerializedName("News")
    val news: News
)