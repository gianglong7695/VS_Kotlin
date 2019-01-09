package com.vtv.sports.model.news

import com.google.gson.annotations.SerializedName


/**
 * Created by Giang Long on 12/23/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class News(
        @SerializedName("Author")
        val author: String,
        @SerializedName("Avatar")
        val avatar: String,
        @SerializedName("Avatar1")
        val avatar1: String,
        @SerializedName("Avatar2")
        val avatar2: String,
        @SerializedName("Avatar3")
        val avatar3: String,
        @SerializedName("Avatar4")
        val avatar4: String,
        @SerializedName("Avatar5")
        val avatar5: String,
        @SerializedName("DistributionDate")
        val distributionDate: String,
        @SerializedName("MatchFinished")
        val matchFinished: Int,
        @SerializedName("NewsId")
        val newsId: String,
        @SerializedName("NewsType")
        val newsType: Int,
        @SerializedName("Order")
        val order: Int,
        @SerializedName("RowNum")
        val rowNum: String,
        @SerializedName("Sapo")
        val sapo: String,
        @SerializedName("Subtitle")
        val subtitle: String,
        @SerializedName("Title")
        val title: String,
        @SerializedName("Type")
        val type: Int,
        @SerializedName("Url")
        val url: String,
        @SerializedName("ZoneId")
        val zoneId: Int,
        @SerializedName("ZoneName")
        val zoneName: String,
        @SerializedName("ZoneShortURL")
        val zoneShortURL: String,
        @SerializedName("CommentCount")
        val commentCount: Int,
        @SerializedName("ShareCount")
        val shareCount: Int,
        @SerializedName("ChannelId")
        val channelId: Int,
        @SerializedName("PublishDate")
        val publishDate: String,
        @SerializedName("PushDate")
        val pushDate: String
) {
    var isLoading: Boolean = false
}