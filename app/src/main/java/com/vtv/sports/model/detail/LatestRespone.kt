package com.vtv.sports.model.detail
import com.google.gson.annotations.SerializedName
import com.vtv.sports.model.common.LastUpdated
import com.vtv.sports.model.news.News


/**
 * Created by Giang Long on 1/9/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class LatestRespone(
    @SerializedName("LastUpdated")
    val lastUpdated: LastUpdated,
    @SerializedName("News")
    val news: List<News>
)
