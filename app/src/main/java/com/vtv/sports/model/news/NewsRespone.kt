package com.vtv.sports.model.news

import com.google.gson.annotations.SerializedName
import com.vtv.sports.model.common.LastUpdated

/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class NewsRespone(
    @SerializedName("ChildZone")
    val childZone: List<Zone>,
    @SerializedName("LastUpdated")
    val lastUpdated: LastUpdated,
    @SerializedName("News")
    val news: List<News>,
    @SerializedName("Zone")
    val zone: Zone
)
