package com.vtv.sports.model.breakingnews

import com.google.gson.annotations.SerializedName

/**
 * Created by Giang Long on 1/8/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class Notify(
    @SerializedName("Author")
    val author: String,
    @SerializedName("Avatar")
    val avatar: String,
    @SerializedName("Avatar5")
    val avatar5: String,
    @SerializedName("ChannelId")
    val channelId: Int,
    @SerializedName("Id")
    val id: String,
    @SerializedName("IsProcess")
    val isProcess: Boolean,
    @SerializedName("NewsId")
    val newsId: String,
    @SerializedName("PublishDate")
    val publishDate: String,
    @SerializedName("PushDate")
    val pushDate: String,
    @SerializedName("Sapo")
    val sapo: String,
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
    val zoneShortURL: String
)