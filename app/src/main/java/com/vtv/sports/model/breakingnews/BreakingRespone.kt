package com.vtv.sports.model.breakingnews
import com.google.gson.annotations.SerializedName
import com.vtv.sports.model.news.News


/**
 * Created by Giang Long on 1/8/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class BreakingRespone(
    @SerializedName("LastUpdated")
    val lastUpdated: String,
    @SerializedName("Notify")
    val notify: List<News>
)